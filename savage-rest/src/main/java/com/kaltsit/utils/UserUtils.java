package com.kaltsit.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class UserUtils {

    public static String getCurrentUserName(){
        try{
            Subject subject = SecurityUtils.getSubject();
            String token = subject.getPrincipal().toString();
            return JWTUtil.getUsername(token);
        }catch (Exception e){
            return "未知";
        }
    }

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
