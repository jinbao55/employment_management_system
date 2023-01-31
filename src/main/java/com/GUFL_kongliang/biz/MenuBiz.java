package com.GUFL_kongliang.biz;

import com.GUFL_kongliang.entity.Menu;
import com.GUFL_kongliang.entity.User;
import com.GUFL_kongliang.entity.dto.MenuDto;
import com.GUFL_kongliang.mapper.MenuMapper;
import com.GUFL_kongliang.utils.RedisUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 菜单控制表
 *
 * @author 孔量
 * @date 2023-01-31 09:02:37
 */
@Service
public class MenuBiz extends ServiceImpl<MenuMapper, Menu> {



    @Autowired
    RedisUtils redisUtils;

    @Autowired
    MenuMapper menuMapper;

    /**
     * @Desc:  列表查询
     * @Auther: 孔量
     * @Date: 2023/1/31 9:19
     * @param: dto
     * @Return: List<Menu>
    */
    public List<Menu> getPageList(Menu dto) {
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("crt_time");
        return this.baseMapper.selectList(wrapper);
    }




    /**
     * @Desc:  获取菜单
     * @Auther: 孔量
     * @Date: 2023/1/31 10:38
     * @param: token
     * @Return: MenuDto
    */
    public List<MenuDto> getMenu(User user) {
        List<MenuDto> menuDtos = "管理员".equals(user.getType()) ? menuMapper.getAdminMenu() : menuMapper.getMenu();

        List<MenuDto> ones = menuDtos.stream()
                .filter(e -> StringUtils.isBlank(e.getHasThird()))
                .collect(Collectors.toList());
        for (MenuDto one: ones) {
            List<MenuDto> menus=new ArrayList<>();
            for(MenuDto two:menuDtos){
                if(one.getMenuid().equals(two.getParentId())){
                    menus.add(two);
                }
            }
            one.setMenus(menus);
        }

        return ones;
    }


    /**
     * @Desc:  添加或修改
     * @Auther: 孔量
     * @Date: 2023/1/31 14:50
     * @param: entity
     * @Return: void
    */
    public void editSave(Menu entity) {
        saveOrUpdate(entity);
    }


    /**
     * @Desc:  删除
     * @Auther: 孔量
     * @Date: 2023/1/31 14:55
     * @param: id
     * @Return: Integer
    */
    public Integer delete(String id) {
        return  baseMapper.deleteById(id);
    }
}