package com.GUFL_kongliang.mapper;

import com.GUFL_kongliang.entity.Register;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 就业登记
 * 
 * @author 孔量
 * @date 2023-01-14 14:40:11
 */
public interface RegisterMapper extends BaseMapper<Register> {

    void insertList(List<Register> list);

    Register selectByIda(String id);
}
