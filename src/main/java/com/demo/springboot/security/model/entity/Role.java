package com.demo.springboot.security.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Role {
    private Integer id;
    private String name;
    private String desc;
    private List<Source> sources;
}
