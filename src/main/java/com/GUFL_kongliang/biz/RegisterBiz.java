package com.GUFL_kongliang.biz;

import com.GUFL_kongliang.entity.Register;
import com.GUFL_kongliang.mapper.RegisterMapper;
import com.GUFL_kongliang.utils.SnowflakeIdWorker;
import com.GUFL_kongliang.utils.UUIDUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.javafaker.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * 就业登记
 *
 * @author 孔量
 * @date 2023-01-14 14:40:11
 */
@Service

public class RegisterBiz extends ServiceImpl<RegisterMapper, Register> {


    @Autowired
    RegisterMapper registerMapper;


    public Register selectByIda(String id) {
        return baseMapper.selectByIda(id);
    }


    /**
     * @Desc: 添加或修改
     * @Auther: 孔量
     * @Date: 2023/1/14 15:33
     * @param: entity
     * @Return: void
     */
    public void editSave(Register entity) {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        entity.setDeleted(0);
        String id = String.valueOf(entity.getId());
        if (StringUtils.isBlank(id)) {
            //添加
            entity.setId(idWorker.nextId());
            baseMapper.insert(entity);
        } else {
            //修改
            baseMapper.updateById(entity);
        }
    }

    /**
     * @Desc: 删除
     * @Auther: 孔量
     * @Date: 2023/1/14 15:34
     * @param: id
     * @Return: Integer
     */
    public Integer delete(String id) {
        Integer i = baseMapper.deleteById(id);
        return i;
    }


    /**
     * @Desc: 列表查询
     * @Auther: 孔量
     * @Date: 2023/1/14 15:37
     * @param: dto
     * @Return: List<Register>
     */
    public List<Register> getPageList(Register entity) {
        String college = entity.getCollege();
        String workType = entity.getWorkType();
        String major = entity.getMajor();
        String workCities = entity.getWorkCities();

        QueryWrapper<Register> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(college)) {
            wrapper.like("college", "%" + college + "%");
        }
        if (StringUtils.isNotBlank(workType)) {
            wrapper.like("work_type", "%" + workType + "%");
        }
        if (StringUtils.isNotBlank(major)) {
            wrapper.like("major", "%" + major + "%");
        }
        if (StringUtils.isNotBlank(workCities)) {
            wrapper.like("work_cities", "%" + workCities + "%");
        }
        wrapper.orderByDesc("graduation_year");
        return this.baseMapper.selectList(wrapper);
    }


    public static void main(String[] args) {
    }


    public void addList() {

            //雪花id
            SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);


            Faker faker = new Faker(Locale.CHINA);
            List<String> major = new ArrayList<>(30);
            Collections.addAll(major,
                    "新闻学",
                    "汉语言文学",
                    "汉语国际教育",
                    "英语",
                    "法语",
                    "泰语",
                    "越南语",
                    "软件工程",
                    "网络工程",
                    "市场营销",
                    "财务管理",
                    "人力资源管理",
                    "审计学",
                    "公共事业管理",
                    "播音与主持艺术",
                    "艺术设计学",
                    "国际经济与贸易",
                    "金融工程",
                    "人力资源管理",
                    "越南语",
                    "柬埔寨语",
                    "印度尼西亚语",
                    "日语",
                    "音乐表演",
                    "舞蹈表演",
                    "酒店管理",
                    "翻译",
                    "审计学",
                    "电子商务"
            );
            Random random = new Random();
            List<Register> insert = new ArrayList<>();
            for (int i = 0; i < 150000; i++) {
                Address address = faker.address();
                Stock stock = faker.stock();
                Job job = faker.job();
                Register register = new Register();

                //id
                register.setId(idWorker.nextId());

                //删除状态
                register.setDeleted(0);

                //姓名
                register.setName(address.streetName());
                //是否工作
                register.setIsWork("否");
                //工资
                //register.setPay(random.nextInt(8823 - 2000 + 1) + 2000);
                register.setPay(0);
                //工作地点
               // register.setWorkCities(address.city());
            register.setWorkCities("无");

                //工作公司
             //   register.setEmploymentCompany(stock.nsdqSymbol());
            register.setEmploymentCompany("无");

                //学院

                int a = random.nextInt(13) + 1;
                switch (a) {
                    case 1:
                        register.setCollege("欧美语言文化学院");
                        break;
                    case 2:
                        register.setCollege("东南亚语言文化学院");
                        break;
                    case 3:
                        register.setCollege("文学院");
                        break;
                    case 4:
                        register.setCollege("国际经济与贸易学院");
                        break;
                    case 5:
                        register.setCollege("工商与公共管理学院");
                        break;
                    case 6:
                        register.setCollege("会计学院");
                        break;
                    case 7:
                        register.setCollege("艺术学院");
                        break;
                    case 8:
                        register.setCollege("教育学院");
                        break;
                    case 9:
                        register.setCollege("信息工程学院");
                        break;
                    case 10:
                        register.setCollege("马克思主义学院");
                        break;
                    case 11:
                        register.setCollege("高等职业学院");
                        break;
                    case 12:
                        register.setCollege("致达教育学院");
                        break;
                    case 13:
                        register.setCollege("东盟法商学院");
                        break;
                    default:
                        register.setCollege("信息工程学院");
                }

                //企业类型
//                int b = random.nextInt(6) + 1;
//                switch (b) {
//                    case 1:
//                        register.setWorkType("国有企业");
//                        break;
//                    case 2:
//                        register.setWorkType("民营企业");
//                        break;
//                    case 3:
//                        register.setWorkType("外资企业");
//                        break;
//                    case 4:
//                        register.setWorkType("政府单位");
//                        break;
//                    case 5:
//                        register.setWorkType("事业单位");
//                        break;
//                    case 6:
//                        register.setWorkType("其他");
//                        break;
//                    default:
//                        register.setCollege("其他");
//                }

                //毕业年份

            register.setWorkType("考研");

                register.setGraduationYear(random.nextInt(2023 - 2013 + 1) + 2013);

                //学号
                register.setSno(random.nextInt(230000000 - 130000000 + 1) + 130000000);

                //班级
                register.setClassNo(random.nextInt(4) + 1);


                //专业
                int c = random.nextInt(29);
                register.setMajor(major.get(c));

                //工作岗位
                //register.setJobs(job.position());
                register.setJobs("无");


                insert.add(register);

            }
            baseMapper.insertList(insert);

        }

}