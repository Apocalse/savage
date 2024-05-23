package com.kaltsit.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class UserUtils {

    /**
     * 获取当前登录人账号
     * @return username
     */
    public static String getCurrentUserName(){
        try{
            Subject subject = SecurityUtils.getSubject();
            String token = subject.getPrincipal().toString();
            return JWTUtil.getUsername(token);
        }catch (Exception e){
            return "未知";
        }
    }

    /**
     * 获取当前登录人id
     * @return userId
     */
    public static String getCurrentUserId(){
        try{
            Subject subject = SecurityUtils.getSubject();
            String token = subject.getPrincipal().toString();
            return JWTUtil.getUserId(token);
        }catch (Exception e){
            return "-1";
        }
    }


}
