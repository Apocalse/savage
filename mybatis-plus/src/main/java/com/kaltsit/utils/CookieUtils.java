package com.kaltsit.utils;

import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

    /**
     * 非法请求，删除cookie
     */
    public static void delCookieByName(ServletResponse response, String cookieName) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        //删除cookie中的token
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        httpServletResponse.addCookie(cookie);
    }

}
