package com.alibaba.chord.service.base.service;

import java.util.Set;

/**
 * Created by wb-zj268791 on 2017/4/10.
 */

public interface PermissionService {
    Set<String> findPermissionByUserKey(String userKey);

}
