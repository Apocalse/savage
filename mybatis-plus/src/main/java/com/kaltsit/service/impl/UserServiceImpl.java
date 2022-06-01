package com.kaltsit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kaltsit.entity.UserEntity;
import com.kaltsit.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> {

    public boolean isExist(String username){
        LambdaQueryWrapper<UserEntity> lqw = Wrappers.<UserEntity>lambdaQuery().eq(UserEntity::getUsername, username);
        long flag = this.count(lqw);
        if(flag > 0){
            return true;
        }else {
            return false;
        }
    }

}
