package com.demo.springboot.security.model.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.demo.springboot.security.model.entity.Source;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SourceMapper extends BaseMapper<Source> {
}
