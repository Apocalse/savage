package com.kaltsit.service;

import com.kaltsit.entity.UserEntity;
import com.kaltsit.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, UserEntity> {

}
