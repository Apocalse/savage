package com.kaltsit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kaltsit.entity.UserEntity;
import com.kaltsit.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaltsit.utils.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> {

    public boolean isExist(String username){
        LambdaQueryWrapper<UserEntity> lqw = Wrappers.<UserEntity>lambdaQuery().eq(UserEntity::getUsername, username);
        long flag = this.count(lqw);
        return flag > 0;
    }

    public String getIdByUsername(String username){
        LambdaQueryWrapper<UserEntity> lqw = Wrappers.<UserEntity>lambdaQuery().eq(UserEntity::getUsername, username);
        UserEntity user = this.getOne(lqw);
        if(user!=null){
            return user.getId();
        }else {
            return "-1";
        }
    }

    /*public List<UserEntity> getUserList(){
        LambdaQueryWrapper<UserEntity> lqw = Wrappers.<UserEntity>lambdaQuery().select(UserEntity::getId, UserEntity::getUsername, UserEntity::getUserRealName);
        List<UserEntity> list = this.list(lqw);
        return list;
    }*/

}
