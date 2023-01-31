package com.GUFL_kongliang.mapper;

import com.GUFL_kongliang.entity.Menu;
import com.GUFL_kongliang.entity.dto.MenuDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 菜单控制表
 * 
 * @author 孔量
 * @date 2023-01-31 09:02:37
 */
public interface MenuMapper extends BaseMapper<Menu> {
    List<MenuDto> getAdminMenu();
    List<MenuDto> getMenu();
}
