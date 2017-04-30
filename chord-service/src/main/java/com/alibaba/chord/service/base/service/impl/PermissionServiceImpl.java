package com.alibaba.chord.service.base.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import com.alibaba.chord.service.base.model.Role;
import com.alibaba.chord.service.base.service.PermissionService;
import com.alibaba.chord.service.base.service.RoleService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author zhangjian
 * @date
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private RoleService roleService;

    /**
     * 根据userKey 获取用户全部权限码
     *
     * @param userKey
     * @return
     */
    @Override
    public Set<String> findPermissionByUserKey(String userKey) {
        Set<String> roleIdSet = new HashSet<>();
        Set<Role> roleSet = roleService.findRoleByUserKey(userKey);
        if (null != roleSet && roleSet.size() > 0) {
            for (Role role : roleSet) {
                if (StringUtils.isNotBlank(role.getPermissionList())) {
                    roleIdSet.addAll(Arrays.asList(role.getPermissionList().split(",")));
                }
            }
        }
        return roleIdSet;
    }


}
