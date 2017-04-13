package service.impl;

import javax.annotation.Resource;

import dao.UserRoleMapper;
import model.UserRole;
import org.springframework.stereotype.Service;
import service.UserRoleService;

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
