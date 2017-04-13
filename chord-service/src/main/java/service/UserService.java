package service;

import com.alibaba.fastjson.JSONObject;

import model.User;

/**
 * Created by wb-zj268791 on 2017/3/29.
 */
public interface UserService {
    User loginAuth(User user);

    void logOut(String name);

    int addUser(User user);

    JSONObject findUserDetailByUserKey(String userKey);
}
