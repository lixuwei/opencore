package com.fairyhawk.common.util;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @ClassName CookieUtils
 * @package com.fairyhawk.common.util
 * @description cookie操作类
 * @author liuqinggang
 * @Create Date: 2013-3-1 上午10:48:35
 * 
 */
public class CookieUtils {

    private static String MYDOMAIN = ".baidu.com";

    /**
     * 增加或修改cookie
     * @param response
     * @param key
     * @param value
     * @param days
     */
    public static void setCookie(HttpServletResponse response, String key,
            String value, int days) {
        
        if (key != null && value != null) {
            Cookie cookie = new Cookie(key, value);
            // 设置有效日期
            cookie.setMaxAge(days * 24 * 60 * 60);
            // 设置路径（默认）
            cookie.setPath("/");
            //cookie.setDomain(MYDOMAIN);
            // 把cookie放入响应中
            response.addCookie(cookie);
        } 
        
    }
    
    /**
     * 增加或修改cookie
     * @param response
     * @param key
     * @param value
     * @param days
     */
    public static void setCookie(HttpServletResponse response, String key,
            String value, int days,String domain) {
        
        if (key != null && value != null) {
            Cookie cookie = new Cookie(key, value);
            // 设置有效日期
            cookie.setMaxAge(days * 24 * 60 * 60);
            // 设置路径（默认）
            cookie.setPath("/");
            if(domain!=null){
                cookie.setDomain(MYDOMAIN);
            }
            // 把cookie放入响应中
            response.addCookie(cookie);
        } 
        
    }

    /**
     * 得到指定键的值
     * 
     * @param request
     * @param name
     *            指定的键
     * @return String 值
     */
    public static String getCookie(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        String resValue = "";
        if (cookies != null) {
            if (cookies.length > 0) {
                for (int i = 0; i < cookies.length; i++) {
                    if (key.equalsIgnoreCase(cookies[i].getName())) {
                        resValue = cookies[i].getValue();
                    }
                }
            }
        }
        return resValue;
    }

    /**
     * 根据name销毁cookie
     * 
     * @param request
     * @param response
     */
    public static void deleteCookie(HttpServletRequest request,
            HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            if (cookies.length > 0) {
                for (int i = 0; i < cookies.length; i++) {
                    if (name.equalsIgnoreCase(cookies[i].getName())) {
                        Cookie cookie = cookies[i];
                        // 销毁
                        Cookie ck = new Cookie(cookie.getName(), null);
                        ck.setPath("/");
                        ck.setMaxAge(0);
                        response.addCookie(ck);
                        return;
                    }
                }
            }
        }
    }

    /**
     * 根据域名和name销毁cookie
     * 
     * @param request
     * @param response
     * @param
     */
    public static void deleteCookieDomain(HttpServletRequest request,
            HttpServletResponse response, String name, String domain) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            if (cookies.length > 0) {
                for (int i = 0; i < cookies.length; i++) {
                    if (name.equalsIgnoreCase(cookies[i].getName())) {
                        Cookie cookie = cookies[i];
                        // 销毁
                        Cookie ck = new Cookie(cookie.getName(), null);
                        ck.setPath("/");
                        ck.setDomain(domain);
                        ck.setMaxAge(0);
                        response.addCookie(ck);
                        return;
                    }
                }
            }
        }
    }

    /**
     * 创建cookie
     * 
     * @param response
     *            回应
     * @param nameValues
     *            Hashtable<String, String> 存入cookie的键值对
     * @param days
     *            设置cookie的有效期
     */
    public static void createCookieFromMap(HttpServletResponse response,
            Hashtable<String, String> nameValues, int days) {
        Set<String> set = nameValues.keySet();
        Iterator<String> it = set.iterator();
        for (; it.hasNext();) {
            String name = (String) it.next();
            String value = (String) nameValues.get(name);
            // 生成新的cookie
            Cookie cookie = new Cookie(name, value);
            // 设置有效日期
            cookie.setMaxAge(days * 24 * 60 * 60);
            // 设置路径（默认）
            cookie.setPath("/");
            // 把cookie放入响应中
            response.addCookie(cookie);
        }
    }

    /**
     * 创建cookie
     * 
     * @param response
     *            回应
     * @param nameValues
     *            存入cookie的键值对
     * @param days
     *            设置cookie的有效期
     * @param domain
     *            设置的域名
     */
    public static void createCookieFromMapDomain(HttpServletResponse response,
            Hashtable<String, String> nameValues, int days, String domain) {
        Set<String> set = nameValues.keySet();
        Iterator<String> it = set.iterator();
        for (; it.hasNext();) {
            String name = (String) it.next();
            String value = (String) nameValues.get(name);
            // 生成新的cookie
            Cookie cookie = new Cookie(name, value);
            cookie.setDomain(domain);
            cookie.setSecure(false);
            // 设置有效日期
            cookie.setMaxAge(days * 24 * 60 * 60);
            // 设置路径（默认）
            cookie.setPath("/");
            // 把cookie放入响应中
            response.addCookie(cookie);
        }
    }

    /**
     * 读取Cookie
     * 
     * @param request
     * @return Hashtable 返回cookie的键值对
     */
    public static Hashtable<String, String> getCookiesForMap(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Hashtable<String, String> cookieHt = new Hashtable<String, String>();
        if (cookies.length > 0) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                cookieHt.put(cookie.getName(), cookie.getValue());
            }
        }
        return cookieHt;
    }

    /**
     * 修改cookie中指定键的值
     * 
     * @param request
     * @param name
     *            指定的键
     * @param value
     *            值
     */
    public static void updateCookie(HttpServletRequest request, String name,
            String value) {
        Cookie[] cookies = request.getCookies();
        if (cookies.length > 0) {
            for (int i = 0; i < cookies.length; i++) {
                if (name.equalsIgnoreCase(cookies[i].getName())) {
                    cookies[i].setValue(value);
                    return;
                }
            }
        }
    }

    /**
     * 销毁所有cookie
     * 
     * @param request
     * @param response
     */
    public static void deleteAllCookie(HttpServletRequest request,
            HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                // 销毁
                Cookie ck = new Cookie(cookie.getName(), null);
                ck.setPath("/");
                ck.setMaxAge(0);
                response.addCookie(ck);
            }
        }
    }

}
