package com.alibaba.chord.service.base.service;

import com.alibaba.chord.service.base.model.UserRole;

/**
 * Created by wb-zj268791 on 2017/3/30.
 */
public interface UserRoleService {
    UserRole findUserRoleByUserKey(String s);
}
