package com.demo.springboot.security.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JSONResult {
    private String msg;
    private Object detail;
    private boolean success;
    private Integer code;

    public static JSONResult ok(String msg) {
        JSONResult jsonResult = new JSONResult();
        jsonResult.setMsg(msg);
        jsonResult.setSuccess(true);
        return jsonResult;
    }

    public static JSONResult fail(String msg) {
        JSONResult jsonResult = new JSONResult();
        jsonResult.setMsg(msg);
        jsonResult.setSuccess(false);
        return jsonResult;
    }
}
