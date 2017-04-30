package com.alibaba.chord.service.base.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.alibaba.chord.service.base.dto.PermissionResDto;
import com.alibaba.chord.service.base.model.Permission;
import com.alibaba.chord.service.base.service.RoleService;
import com.alibaba.fastjson.JSONObject;

import com.alibaba.chord.service.base.dao.PermissionMapper;
import com.alibaba.chord.service.base.dao.UserMapper;
import com.alibaba.chord.service.base.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import com.alibaba.chord.service.base.service.PermissionService;
import com.alibaba.chord.service.base.service.UserService;
import com.alibaba.chord.utils.EncipherUtil;
import com.alibaba.chord.commons.CommonsValue;

/**
 * Created by wb-zj268791 on 2017/3/29.
 */
@Service
@EnableCaching(proxyTargetClass = true)
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Resource
    private CacheManager cacheManager;

    @Resource
    private PermissionService permissionService;

    @Resource
    private RoleService roleService;

    /**
     * 登录验证 userKey 和 md(password+salt)
     *
     * @param user
     * @return
     */
    @Override
    public User loginAuth(User user) {
        User retUser = userMapper.selectByUniqueKey(user.getUniqueKey());
        if (null == retUser) {
            return null;
        }
        user.setPassword(EncipherUtil.encryptedPwd(user.getPassword(), retUser.getSalt()));
        user.setSalt(retUser.getSalt());
        return userMapper.selectBySelective(user);
    }

    @Override
    public int addUser(User user) {
        user.setMark(1);
        return userMapper.insertSelective(user);
    }

    @Override
    public void logOut(String userKey) {
        //干掉缓存
        Cache cache = cacheManager.getCache(CommonsValue.CACHE_NAME);
        if (null != cache.get(userKey)) {
            cache.evict(userKey);
        }
    }

    /**
     * 通过姓名查询详细信息2
     *
     * @param userKey
     * @return
     */
    @Override
    @Cacheable(value = CommonsValue.CACHE_NAME, condition = "#userKey!=''", key = "#userKey")
    public JSONObject findUserDetailByUserKey(String userKey) {
        JSONObject jsonObject = new JSONObject();
        List<PermissionResDto> parentPermissionResDtoList = new ArrayList<>();
        //查询所有的角色
        Set<String> roleNameSet = roleService.findRoleNameByUserKey(userKey);
        //查询所有权限码
        Set<String> permissionSet = permissionService.findPermissionByUserKey(userKey);
        //全部权限码
        Set<String> permissionSetTmp = new HashSet<>();
        permissionSetTmp.addAll(permissionSet);

        if (null == permissionSet || permissionSet.size() == 0) {
            return jsonObject;
        }
        Iterator<String> iterator = permissionSet.iterator();
        while (iterator.hasNext()) {
            Permission permission = permissionMapper.selectByPrimaryKey(Integer.valueOf(iterator.next()));
            //先查询父节点
            if (permission.getNode().equals(0)) {
                PermissionResDto permissionResDto = new PermissionResDto();
                BeanUtils.copyProperties(permission, permissionResDto);
                parentPermissionResDtoList.add(permissionResDto);
                iterator.remove();
            }
        }
        //二级菜单的node值和父节点id相匹配
        for (PermissionResDto permissionResDto : parentPermissionResDtoList) {
            List<Permission> permissionList = new ArrayList<>();
            for (String s : permissionSet) {
                Permission permission = permissionMapper.selectByPrimaryKey(Integer.valueOf(s));
                if (permission.getNode().equals(permissionResDto.getId())) {
                    permissionList.add(permission);
                }
            }
            permissionResDto.setChildNodes(permissionList);
        }
        //这里存放二级菜单权限码，而不是一级菜单权限码
        jsonObject.put("permissionSet", permissionSetTmp);
        jsonObject.put("roleNameSet", roleNameSet);
        jsonObject.put("permissionList", parentPermissionResDtoList);
        return jsonObject;
    }
}
