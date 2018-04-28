package com.demo.springboot.security;

import com.demo.springboot.security.model.dao.UserMapper;
import com.demo.springboot.security.model.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityApplicationTests {

	@Resource
	UserMapper mapper;
	@Test
	public void contextLoads() {
		User user = mapper.selectByUserName("");
		System.out.println(user);
	}

}
