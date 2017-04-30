package com.alibaba.chord.service.base.dao;

import com.alibaba.chord.service.base.model.User;

import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectBySelective(User user);

    User selectByUniqueKey(String key);
}