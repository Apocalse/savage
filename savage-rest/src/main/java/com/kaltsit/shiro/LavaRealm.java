package com.kaltsit.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kaltsit.entity.UserEntity;
import com.kaltsit.service.impl.UserServiceImpl;
import com.kaltsit.utils.JWTUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class LavaRealm extends AuthorizingRealm {
    @Resource
    private UserServiceImpl userService;

    /**
     * 根据token判断此Authenticator是否使用该realm
     * 必须重写不然shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如@RequiresRoles,@RequiresPermissions之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("授权~~~~~");
        String token = principals.toString();
        String username = JWTUtil.getUsername(token);
        QueryWrapper<UserEntity> qw = new QueryWrapper<>();
        qw.eq("username", username);
        UserEntity user = userService.getOne(qw);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //TODO
        //查询数据库来获取用户的角色
//        info.addRole(user.getRoles());
        //查询数据库来获取用户的权限
//        info.addStringPermission(user.getPermission());
        return info;
    }


    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可，在需要用户认证和鉴权的时候才会调用
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //System.out.println("认证~~~~~~~");
        String jwt = (String) token.getCredentials();
        String username = null;
        try {
            username = JWTUtil.getUsername(jwt);
        } catch (Exception e) {
            throw new AuthenticationException("token非法，不是规范的token，可能被篡改了，或者过期了");
        }
        if (!JWTUtil.verify(jwt) || username == null) {
            throw new AuthenticationException("token认证失效，token错误或者过期，重新登陆");
        }
        QueryWrapper<UserEntity> qw = new QueryWrapper<>();
        qw.eq("username", username);
        UserEntity user = userService.getOne(qw);
        if (user == null) {
            throw new AuthenticationException("[doGetAuthenticationInfo] 该用户不存在");
        }
        return new SimpleAuthenticationInfo(jwt, jwt, "LavaRealm");
    }
}
