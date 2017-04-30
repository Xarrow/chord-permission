package com.alibaba.chord.service.base.service.impl;

import javax.annotation.Resource;

import com.alibaba.chord.service.base.model.UserRole;
import com.alibaba.chord.service.base.service.UserRoleService;
import com.alibaba.chord.service.base.dao.UserRoleMapper;

import org.springframework.stereotype.Service;

/**
 * Created by wb-zj268791 on 2017/3/30.
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public UserRole findUserRoleByUserKey(String userKey) {
        return userRoleMapper.selectByUserKey(userKey);
    }
}
