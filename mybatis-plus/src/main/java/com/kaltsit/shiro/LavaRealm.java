package com.kaltsit.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kaltsit.entity.UserEntity;
import com.kaltsit.service.impl.UserServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

public class LavaRealm extends AuthorizingRealm {
    @Autowired
    private UserServiceImpl userService;

    // 简单重写获取授权信息方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return new SimpleAuthorizationInfo();
    }

    // 获取认证信息，即根据 token 中的用户名从数据库中获取密码、盐等并返回
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = token.getPrincipal().toString();
        QueryWrapper<UserEntity> qw = new QueryWrapper<>();
        qw.eq("username", username);
        UserEntity user = userService.getOne(qw);
        if (ObjectUtils.isEmpty(user)) {
            throw new UnknownAccountException();
        }
        String passwordInDB = user.getPassword();
        String salt = user.getSalt();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, passwordInDB, ByteSource.Util.bytes(salt), getName());

        return info;
    }
}
