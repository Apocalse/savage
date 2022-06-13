package com.kaltsit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kaltsit.entity.UserEntity;
import com.kaltsit.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaltsit.utils.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> {

    public boolean isExist(String username){
        LambdaQueryWrapper<UserEntity> lqw = Wrappers.<UserEntity>lambdaQuery().eq(UserEntity::getUsername, username);
        long flag = this.count(lqw);
        return flag > 0;
    }

    public String getUserName(){
        Subject subject = SecurityUtils.getSubject();
        String token = subject.getPrincipal().toString();
        String username = JWTUtil.getUsername(token);
        return username;
    }

    public String getUserId(){
        Subject subject = SecurityUtils.getSubject();
        String token = subject.getPrincipal().toString();
        String userId = JWTUtil.getUserId(token);
        return userId;
    }

}
