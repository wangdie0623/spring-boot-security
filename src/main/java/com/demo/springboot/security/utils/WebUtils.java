package com.demo.springboot.security.utils;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author huan.fu
 * @date 2017/9/25 - 14:55
 * 和web相关的工具类
 */
@Slf4j
public final class WebUtils {

    private static final String UNKNOWN = "unknown";
    private static final String LOOPBACK_ADDRESS = "127.0.0.1";

    private WebUtils() {
    }

    /**
     * 获取请求主机IP地址
     *
     * @param request request请求
     * @return 返回实际的ip地址
     */
    public static String getAddressIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        }
        if (LOOPBACK_ADDRESS.equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                log.error("获取本地ip地址出现异常.", e);
            }
        }
        if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = ips[index];
                if (!(UNKNOWN.equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }

    /**
     * 获取当前访问者的ip地址
     *
     * @return 当前访问者的ip地址
     */
    public static String getCurrentRequestAccessIp() {
        return getAddressIp(getCurrentRequest());
    }

    /**
     * 获取当前请求的request对象
     *
     * @return 返回request对象
     */
    public static HttpServletRequest getCurrentRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取当前请求的session对象
     *
     * @return 返回session兑现
     */
    public static HttpSession getCurrentSession() {
        return getCurrentRequest().getSession();
    }

    /**
     * 获取spring当前请求header属性值
     * @param headerName
     * @return
     */
    public static String getHeader(String headerName) {
        return getHeader(getCurrentRequest(), headerName);
    }

    /**
     * 通过header属性名key 获取属性值
     * @param request 请求对象
     * @param headerName 属性名key
     * @return
     */
    public static String getHeader(HttpServletRequest request, String headerName) {
        if (StringUtils.isBlank(headerName)) {
            return null;
        } else {
            return request.getHeader(headerName);
        }
    }

    /**
     * 判断是否是ajax请求
     *
     * @param request 待判断请求对象
     * @return true 是 false 不是
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return Objects.equals(getHeader(request, "X-Requested-With"), "XMLHttpRequest");
    }

    /**
     * 判断当前spring线程请求是否是ajax请求
     *
     * @return true 是 false 不是
     */
    public static boolean isAjaxRequest() {
        return isAjaxRequest(getCurrentRequest());
    }

    /**
     * 获取spring 当前请求参数列表
     *
     * @return
     */
    public static Map<String, Object> getRequestParamMap() {
        Map<String, String[]> parameterMap = getCurrentRequest().getParameterMap();
        Map<String, Object> ret = new HashMap<>();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String key = entry.getKey();
            String[] value = entry.getValue();
            if (null == value) {
                ret.put(key, null);
            } else if (value.length == 1) {
                ret.put(key, value[0]);
            } else {
                ret.put(key, value);
            }
        }
        return ret;
    }

}
