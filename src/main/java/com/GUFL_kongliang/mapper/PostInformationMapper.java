package com.GUFL_kongliang.mapper;

import com.GUFL_kongliang.entity.PostInformation;
import com.GUFL_kongliang.entity.employmentStatisticsCircular;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 企业信息
 * 
 * @author 孔量
 * @date 2022-12-28 11:11:13
 */
public interface PostInformationMapper extends BaseMapper<PostInformation> {

    List<employmentStatisticsCircular> analysis();
}
