package com.demo.springboot.security.config;

import com.alibaba.fastjson.JSON;
import com.demo.springboot.security.model.entity.JSONResult;
import com.demo.springboot.security.model.entity.User;
import com.demo.springboot.security.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class Http403Handler extends AccessDeniedHandlerImpl {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info(user.getName() + "无权限访问" + request.getRequestURI());
        if (WebUtils.isAjaxRequest()) {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print(JSON.toJSONString(JSONResult.fail("403没有权限访问")));
            out.close();
            return;
        }
        super.handle(request, response, accessDeniedException);
    }
}
