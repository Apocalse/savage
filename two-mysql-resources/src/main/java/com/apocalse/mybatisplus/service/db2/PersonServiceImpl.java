package com.apocalse.mybatisplus.service.db2;

import com.apocalse.mybatisplus.entity.db2.PersonEntity;
import com.apocalse.mybatisplus.mapper.db2.PersonMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, PersonEntity> {
}
