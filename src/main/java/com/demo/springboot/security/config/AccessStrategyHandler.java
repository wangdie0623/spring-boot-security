package com.demo.springboot.security.config;

import com.demo.springboot.security.model.dao.SourceMapper;
import com.demo.springboot.security.model.entity.Role;
import com.demo.springboot.security.model.entity.Source;
import com.demo.springboot.security.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限策略钩子  核心方法decision
 */
@Slf4j
@Component
public class AccessStrategyHandler implements InitializingBean {
    private List<String> uris;//需权限访问的地址清单

    @Resource
    private SourceMapper sourceMapper;

    @Override//创建bean执行一次的初始化方法
    public void afterPropertiesSet() throws Exception {
        List<Source> sources = sourceMapper.selectList(null);
        if (null == sources || sources.isEmpty()) {
            this.uris = new ArrayList<>();
            return;
        }
        this.uris = sources.stream().map(Source::getUrl).collect(Collectors.toList());
    }


    /**
     * 判断当前用户是否具备访问权限
     * true 有权限 false 无权限
     *
     * @param authentication 当前用户
     * @param request        本次请求
     * @return
     */
    public boolean decision(Authentication authentication, HttpServletRequest request) {
        if (!authentication.isAuthenticated() || (authentication.getPrincipal() instanceof String)) {
            return false;
        }
        String uri = request.getRequestURI().replace(request.getContextPath(), "");
        return uriPass(authentication, uri);
    }

    /**
     * 判断当前用户是否具备uri访问权限
     *
     * @param authentication 当前用户
     * @param uri            待判断uri
     * @return true 有权限 false 无权限
     */
    public boolean uriPass(Authentication authentication, String uri) {
        User user = (User) authentication.getPrincipal();
        if (!uris.contains(uri)) {
            return true;
        }
        List<String> userUris = new ArrayList<>();
        user.getRoles().stream().map(Role::getSources).forEach(it -> {
            userUris.addAll(it.stream().map(Source::getUrl).collect(Collectors.toList()));
        });
        if (userUris.contains(uri)) {
            return true;
        }
        return false;
    }

}
