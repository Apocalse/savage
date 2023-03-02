package com.kaltsit.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kaltsit.sys.entity.SysUserEntity;
import com.kaltsit.sys.mapper.SysUserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> {

    public boolean isExist(String username){
        LambdaQueryWrapper<SysUserEntity> lqw = Wrappers.<SysUserEntity>lambdaQuery().eq(SysUserEntity::getUsername, username);
        long flag = this.count(lqw);
        return flag > 0;
    }

    public String getIdByUsername(String username){
        LambdaQueryWrapper<SysUserEntity> lqw = Wrappers.<SysUserEntity>lambdaQuery().eq(SysUserEntity::getUsername, username);
        SysUserEntity user = this.getOne(lqw);
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
