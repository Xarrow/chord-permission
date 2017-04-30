package com.alibaba.chord.service.base.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import com.alibaba.chord.service.base.service.RoleService;
import com.alibaba.chord.service.base.dao.RoleMapper;
import com.alibaba.chord.service.base.model.Role;
import com.alibaba.chord.service.base.model.UserRole;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.chord.service.base.service.UserRoleService;

/**
 * Created by wb-zj268791 on 2017/3/30.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserRoleService userRoleService;

    @Override
    public Role findRoleById(String rid) {
        return roleMapper.selectByPrimaryKey(Integer.valueOf(rid));
    }

    /**
     * 根据userKey 查询全部的role
     *
     * @param userKey
     * @return
     */
    @Override
    public Set<Role> findRoleByUserKey(String userKey) {
        Set<Role> roleSet = new HashSet<>();
        //1.查询user_role
        UserRole userRole = userRoleService.findUserRoleByUserKey(userKey);
        if (null == userRole || StringUtils.isBlank(userRole.getRoleList())) {
            return roleSet;
        }
        //2.查询角色id列表
        Set<String> roleIdSet = new HashSet<>(Arrays.asList(userRole.getRoleList().split(",")));
        if (roleIdSet.isEmpty()) {
            return roleSet;
        }
        //3.查询权限列表
        for (String roleId : roleIdSet) {
            Role role = findRoleById(roleId);
            if (null != role) {
                roleSet.add(role);
            }
        }
        return roleSet;

    }

    /**
     * 根据userKey 查询全部的角色名
     *
     * @param userKey
     * @return
     */
    @Override
    public Set<String> findRoleNameByUserKey(String userKey) {
        Set<String> roleNameSet = new HashSet<>();
        Set<Role> roleSet = findRoleByUserKey(userKey);
        if (null != roleSet && roleSet.size() > 0) {
            for (Role role : roleSet) {
                if (StringUtils.isNotBlank(role.getName())) {
                    roleNameSet.add(role.getName());
                }
            }
        }
        return roleNameSet;
    }

}
