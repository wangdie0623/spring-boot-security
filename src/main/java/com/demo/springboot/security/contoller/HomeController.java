package com.demo.springboot.security.contoller;

import com.alibaba.fastjson.JSON;
import com.demo.springboot.security.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

    @RequestMapping("home")
    public String home() {
        log.info("进入首页");
        return "home";
    }

}
