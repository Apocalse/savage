package com.apocalse.mybatisplus.service.db1;

import com.apocalse.mybatisplus.entity.db1.UserEntity;
import com.apocalse.mybatisplus.mapper.db1.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> {
}
