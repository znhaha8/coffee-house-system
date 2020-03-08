package com.wyz.coffee.config;

import com.wyz.coffee.dao.PermissionDao;
import com.wyz.coffee.dao.RoleDao;
import com.wyz.coffee.dao.UserDao;
import com.wyz.coffee.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/12 17:34
 **/
public class UserRealm extends AuthorizingRealm {
    @Autowired
    @Lazy
    RoleDao roleDao;
    @Autowired
    @Lazy
    PermissionDao permissionDao;
    @Autowired
    @Lazy
    UserDao userDao;
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roleDao.selectRoleStringSetByUsername(username));
        authorizationInfo.setStringPermissions(permissionDao.selectPermissionStringSetByUsername(username));
        return authorizationInfo;
    }
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();
        User user = userDao.selectByUsernameAccurately(username);
        if(user == null) {
            throw new UnknownAccountException();//没找到帐号
        }
/*        if(Boolean.TRUE.equals(user.getLocked())) {
            throw new LockedAccountException(); //帐号锁定
        }*/
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以在此判断或自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUsername(), //用户名
                user.getPassword(), //密码
                MyByteSourceUtils.bytes(user.getSalt()), //salt
                getName()  //realm name
        );
        return authenticationInfo;
    }

 /*   *//**
     * 重写方法,清除当前用户的的 授权缓存
     * @param principals
     *//*
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    *//**
     * 重写方法，清除当前用户的 认证缓存
     * @param principals
     *//*
    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    *//**
     * 自定义方法：清除所有 授权缓存
     *//*
    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    *//**
     * 自定义方法：清除所有 认证缓存
     *//*
    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    *//**
     * 自定义方法：清除所有的  认证缓存  和 授权缓存
     *//*
    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }*/
}
