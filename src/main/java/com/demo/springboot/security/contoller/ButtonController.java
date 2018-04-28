package com.demo.springboot.security.contoller;

import com.demo.springboot.security.model.entity.JSONResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("btn")
@Slf4j
public class ButtonController {

    @GetMapping("testBtn")
    public JSONResult testBtn() {
        log.info("访问按钮权限");
        return JSONResult.ok("按钮权限通过~");
    }
}
