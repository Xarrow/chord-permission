package com.alibaba.chord.service.base.shiro;

import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import com.alibaba.chord.service.base.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import com.alibaba.chord.service.base.service.PermissionService;
import com.alibaba.chord.service.base.service.RoleService;
import com.alibaba.chord.service.base.service.UserService;
import com.alibaba.chord.commons.CommonsValue;

/**
 * @Author zhangjian5
 * @Time 2016/10/18
 */
public class ShiroDbRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;

    @Resource
    private CacheManager cacheManager;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            return info;
        }
        Cache cache = cacheManager.getCache(CommonsValue.CACHE_NAME);
        Map map = cache.get(SecurityUtils.getSubject().getPrincipal().toString(), Map.class);

        if (null != map) {
            Set<String> permissionSet = (Set<String>)map.get("permissionSet");
            Set<String> roleNameSet = (Set<String>)map.get("roleNameSet");
            if (null == permissionSet || permissionSet.size() == 0) {
                return info;
            } else if (null == roleNameSet || roleNameSet.size() == 0) {
                return info;
            }
            info.setRoles(roleNameSet);
            info.setStringPermissions(permissionSet);

        } else {
            Object principal = principalCollection.getPrimaryPrincipal();
            if (null == principal) {
                return info;
            }
            String userKey = principal.toString();
            //角色名
            Set<String> roles = roleService.findRoleNameByUserKey(userKey);
            //权限码
            Set<String> permissions = permissionService.findPermissionByUserKey(userKey);
            if (null == roles || roles.isEmpty()) {
                return info;
            }
            if (null == permissions || permissions.isEmpty()) {
                return info;
            }
            info.setRoles(roles);
            info.setStringPermissions(permissions);
        }
        return info;

    }

    /**
     * 登录
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
        throws AuthenticationException {
        User user = new User();
        //userKey
        user.setUniqueKey(authenticationToken.getPrincipal().toString());
        //pwd
        user.setPassword(new String((char[])authenticationToken.getCredentials()));
        if (null != userService.loginAuth(user)) {
            return new SimpleAuthenticationInfo(authenticationToken.getPrincipal(),
                authenticationToken.getCredentials(), getName());
        } else {
            return null;
        }
    }
}
