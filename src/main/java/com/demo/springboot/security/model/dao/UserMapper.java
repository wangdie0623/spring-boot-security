package com.demo.springboot.security.model.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.demo.springboot.security.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {


    User selectByUserName(String userName);
}
