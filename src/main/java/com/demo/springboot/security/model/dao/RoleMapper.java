package com.demo.springboot.security.model.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.demo.springboot.security.model.entity.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
