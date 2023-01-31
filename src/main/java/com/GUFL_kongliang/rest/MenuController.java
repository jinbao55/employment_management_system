package com.GUFL_kongliang.rest;

import com.GUFL_kongliang.biz.MenuBiz;
import com.GUFL_kongliang.entity.Menu;
import com.GUFL_kongliang.entity.Recruit;
import com.GUFL_kongliang.entity.User;
import com.GUFL_kongliang.entity.dto.MenuDto;
import com.GUFL_kongliang.utils.BaseResponse;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 菜单控制表
 *
 * @author 孔量
 * @date 2023-01-31 09:02:37
 */
@RestController
@RequestMapping("menu")
public class MenuController {



    @Autowired
    MenuBiz menuBiz;


    /**
     * @Desc:  列表查询
     * @Auther: 孔量
     * @Date: 2023/1/31 9:14
     * @param: dto
     * @Return: BaseResponse
    */
    @PostMapping("getPageList")
    public BaseResponse getPageList(@RequestBody Menu dto) {
        Page<Object> result = PageHelper.startPage(dto.getPage() == null ? 1 : dto.getPage(), dto.getLimit() == null ? 10 : dto.getLimit());
        List<Menu> page = menuBiz.getPageList(dto);
        return  new BaseResponse<>((int) result.getTotal(), String.valueOf(result.getTotal()), page);
    }



    /**
     * @Desc:  获取菜单
     * @Auther: 孔量
     * @Date: 2023/1/31 10:40
     * @param: token
     * @Return: BaseResponse
    */
    @PostMapping("getMenu")
    public BaseResponse getMenu(@RequestBody User user) {
        List<MenuDto> menu = menuBiz.getMenu(user);
        return new BaseResponse<>(200, "success", menu);
    }



    /**
     * @Desc:  增加或修改
     * @Auther: 孔量
     * @Date: 2023/1/31 14:48
     * @param: entity
     * @Return: BaseResponse<List<Recruit>>
    */
    @PostMapping("editSave")
    public BaseResponse editSave(@RequestBody Menu entity) {
        menuBiz.editSave(entity);
        return  new BaseResponse<>(200, "保存成功", null);
    }


    /**
     * @Desc:  删除
     * @Auther: 孔量
     * @Date: 2023/1/31 14:54
     * @param: id
     * @Return: Integer
    */
    @PostMapping("delete")
    public BaseResponse<List<Recruit>> delete(@RequestBody String id) {
        Integer i = menuBiz.delete(id);
        if(i>0){
            return  new BaseResponse<>(200, "删除成功"+i+"条数据", null);
        }else {
            return  new BaseResponse<>(200, "删除失败", null);
        }
    }
}