package com.demo.springboot.security.config;

import com.demo.springboot.security.model.dao.UserMapper;
import com.demo.springboot.security.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;

/**
 * 用户认证,资源加载
 */
@Slf4j
public class SecurityUserDetailServiceImpl implements UserDetailsService {
    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) {
        if (StringUtils.isBlank(s)) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        User user = userMapper.selectByUserName(s);
        if(null==user){
            throw new UsernameNotFoundException("用户名不存在");
        }
        return user;
    }
}
