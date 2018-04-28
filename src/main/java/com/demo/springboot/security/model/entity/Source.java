package com.demo.springboot.security.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
public class Source implements GrantedAuthority {
    private Integer id;
    private String name;
    private String desc;
    private String url;

    @Override
    public String getAuthority() {
        return name;
    }
}
