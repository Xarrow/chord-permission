package com.alibaba.chord.service.base.service;

import com.alibaba.chord.service.base.model.User;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by wb-zj268791 on 2017/3/29.
 */
public interface UserService {
    User loginAuth(User user);

    void logOut(String name);

    int addUser(User user);

    JSONObject findUserDetailByUserKey(String userKey);
}
