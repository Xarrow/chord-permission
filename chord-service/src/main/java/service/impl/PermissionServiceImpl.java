package service.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import model.Role;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import service.PermissionService;
import service.RoleService;

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
