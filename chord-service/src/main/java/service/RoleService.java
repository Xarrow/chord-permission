package service;

import java.util.Set;

import model.Role;

/**
 * Created by wb-zj268791 on 2017/3/30.
 */
public interface RoleService {

    Role findRoleById(String rid);

    Set<Role> findRoleByUserKey(String userKey);

    Set<String> findRoleNameByUserKey(String userKey);
}
