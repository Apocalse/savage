package com.kaltsit.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaltsit.exception.SavageException;
import com.kaltsit.sys.entity.SysRoleEntity;
import com.kaltsit.sys.mapper.SysRoleMapper;
import com.kaltsit.utils.MapUtils;
import com.kaltsit.utils.PageUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleEntity> {

    public PageUtils<SysRoleEntity> queryPage(MapUtils params){
        String roleName = params.getString("roleName");
        String roleCode = params.getString("roleCode");
        LambdaQueryWrapper<SysRoleEntity> lqw = new LambdaQueryWrapper<>();
        if(StringUtils.isNotEmpty(roleName)){
            lqw.eq(SysRoleEntity::getRoleName, roleName);
        }
        if(StringUtils.isNotEmpty(roleCode)){
            lqw.eq(SysRoleEntity::getRoleCode, roleCode);
        }
        Page<SysRoleEntity> page = new Page<>(params.getInteger("page", 1), params.getInteger("size"), 10);
        IPage<SysRoleEntity> result = this.page(page, lqw);
        return new PageUtils<>(result);
    }

    public void saveSysRole(SysRoleEntity sysRoleEntity){
        if(StringUtils.isBlank(sysRoleEntity.getRoleCode())){
            throw new SavageException("角色代码不能为空");
        }
        if(StringUtils.isBlank(sysRoleEntity.getRoleName())){
            throw new SavageException("角色名称不能为空");
        }
        this.saveOrUpdate(sysRoleEntity);
    }

    public void updateSysRole(SysRoleEntity sysRoleEntity){
        if(StringUtils.isBlank(sysRoleEntity.getId())){
            throw new SavageException("id不能为空");
        }
        this.updateById(sysRoleEntity);
    }

}
