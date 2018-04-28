package com.demo.springboot.security.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class User implements UserDetails {
    private Integer id;
    private String name;
    private String password;
    private Integer enable;
    private List<Role> roles;
    private Set<String> keys = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (roles == null || roles.isEmpty()) {
            return null;
        }
        Set<Source> result = new HashSet<>();
        roles.stream().map(Role::getSources).forEach(it -> {
            it.forEach(s -> {
                if (!keys.contains(s.getName())) {
                    result.add(s);
                    keys.add(s.getName());
                }
            });
        });
        return result;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable == 1;
    }

}
