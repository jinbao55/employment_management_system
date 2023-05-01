package com.GUFL_kongliang.biz;

import com.GUFL_kongliang.entity.User;
import com.GUFL_kongliang.handler.NingException;
import com.GUFL_kongliang.mapper.UserMapper;
import com.GUFL_kongliang.utils.Md5Utils;
import com.GUFL_kongliang.utils.RedisUtils;
import com.GUFL_kongliang.utils.UUIDUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;


/**
 * @author 孔量
 * @date 2022-12-10 02:42:20
 */
@Service
public class UserBiz extends ServiceImpl<UserMapper, User> {


    @Autowired
    RedisUtils redisUtils;


    @Autowired
    VerificationBiz verificationBiz;



    @Autowired
    EmploymentStatisticsBiz employmentStatisticsBiz;


    /**
     * @Desc: 添加用户，用户注册
     * @Auther: 孔量
     * @Date: 2022/12/10 11:16
     * @param: user
     * @Return: void
     */
    public boolean addAndUpdUser(User user) {
        String id = user.getId();
        if(StringUtils.isNotBlank(id)){
            user.setPassword(Md5Utils.md5Password(user.getPassword()));
            int i = baseMapper.updateById(user);
            if(i>0){
                return true;
            }else {
                return false;
            }
        }
        user.setId(UUIDUtils.getUUID());
        user.setCrtTime(new Date());
        user.setPassword(Md5Utils.md5Password(user.getPassword()));
        user.setDeleted(0);
        user.setState("1");
        return save(user);
    }


    /**
     * @Desc: 登录
     * @Auther: 孔量
     * @Date: 2022/12/10 16:10
     * @param: user
     * @Return: boolean
     */
    public List<User> toLogin(User user) {
        HashMap<String, Object> loginMap = new HashMap<>();
        loginMap.put("login_Name", user.getLoginName());
        loginMap.put("password", Md5Utils.md5Password(user.getPassword()));
        List<User> users = baseMapper.selectByMap(loginMap);
        if (CollectionUtils.isEmpty(users)) {
            return new ArrayList<>();
        }

        /**
         * @Desc:  登录后异步加载就业统计菜单，提升相应速度
        */
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("redisOperate-%d").build();
        ExecutorService executor = new ThreadPoolExecutor(1, 9, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), threadFactory, new ThreadPoolExecutor.AbortPolicy());
        CompletableFuture.runAsync(() -> {
            HashMap<String, String> map = new HashMap<>();
            map.put("year","2013");
            employmentStatisticsBiz.workNumber(map);
            employmentStatisticsBiz.numberOfGraduates(map);
            employmentStatisticsBiz.noWork(map);
            employmentStatisticsBiz.workTypeStatistics(map);
            employmentStatisticsBiz.cooperativeCompanies();
        }, executor);
        executor.shutdown();
        return users;
    }


    /**
     * @Desc:  忘记密码修改密码  使用 登录名 姓名 学院 班级 电话号码 为验证
     * @Auther: 孔量
     * @Date: 2022/12/11 0:44
     * @param: user
     * @Return: boolean
    */
    public boolean updateUser(User user) {
        String loginName = user.getLoginName();
        String name = user.getName();
        String college = user.getCollege();
        String classNo = user.getClassNo();
        String post = user.getPost();
        String tel = user.getTel();


        HashMap<String, Object> loginMap = new HashMap<>();
        if (StringUtils.isNotBlank(loginName)) {
            loginMap.put("login_Name", loginName);
        }

        if (StringUtils.isNotBlank(college)) {
            loginMap.put("college", college);
        }

        if (StringUtils.isNotBlank(classNo)) {
            loginMap.put("class_no", classNo);
        }
        if (StringUtils.isNotBlank(post)) {
            loginMap.put("post", post);
        }
        if (StringUtils.isNotBlank(tel)) {
            loginMap.put("tel", tel);
        }
        if (StringUtils.isNotBlank(name)) {
            loginMap.put("name", name);
        }
        List<User> users = baseMapper.selectByMap(loginMap);

        if (CollectionUtils.isEmpty(users)) {
            throw new NingException(400, "没有找到用户信息，请核对后再修改");
        }

        user.setId(users.get(0).getId());
        user.setPassword(Md5Utils.md5Password(user.getNewPassword()));
        return updateById(user);
    }


    /**
     * @Desc: 获取用户列表
     * @Auther: 孔量
     * @Date: 2022/12/27 9:06
     * @param: entity
     * @Return: List<User>
     */
    public List<User> getPageList(User entity) {
        String name = entity.getName();
        String tel = entity.getTel();
        String state = entity.getState();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("name", "%" + name + "%");
        }
        if (StringUtils.isNotBlank(state)) {
            wrapper.like("state", "%" + state + "%");
        }
        if (StringUtils.isNotBlank(tel)) {
            wrapper.like("tel", "%" + tel + "%");
        }
        wrapper.orderByDesc("crt_time");
        return this.baseMapper.selectList(wrapper);
    }

    /**
     * @Desc:  编辑用户
     * @Auther: 孔量
     * @Date: 2023/1/29 21:38
     * @param: user
     * @Return: void
    */
    public int updUser(User user) {
        return baseMapper.updateById(user);

    }

    /**
     * @Desc:  删除用户
     * @Auther: 孔量
     * @Date: 2023/1/29 22:07
     * @param: id
     * @Return: int
    */
    public int deleteUser(String id) {
        return baseMapper.deleteById(id);
    }


    /**
     * @Desc:  重置为通用密码
     * @Auther: 孔量
     * @Date: 2023/1/29 22:24
     * @param: id
     * @Return: int
    */
    public int resetPassword(User user) {
        user.setPassword(Md5Utils.md5Password("e10adc3949ba59abbe56e057f20f883e"));
        return baseMapper.updateById(user);
    }

    /**
     * @Desc:  退出登录清楚token
     * @Auther: 孔量
     * @Date: 2023/1/30 16:05
     * @param: token
     * @Return: boolean
    */
    public void loginout(String token) {
        //异步操作Redis
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("redisOperate-%d").build();
        ExecutorService executor = new ThreadPoolExecutor(1, 9, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), threadFactory, new ThreadPoolExecutor.AbortPolicy());
        CompletableFuture.runAsync(() -> {
            redisUtils.deleteKey(token);
        }, executor);
        executor.shutdown();
    }

    /**
     * @Desc: 修改用户状态
     * @Auther: 孔量
     * @Date: 2023/1/30 17:35
     * @param: user
     * @Return: void
    */
    public void updateUserstate(User user) {
        baseMapper.updateById(user);
    }
}