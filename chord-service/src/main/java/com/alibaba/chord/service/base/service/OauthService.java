package com.alibaba.chord.service.base.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author: zhang
 * @Time:2017/5/7
 */
public interface OauthService {
    /**
     * 获取accessToken
     *
     * @param paramMap
     * @return
     */
    JSONObject getAccessToken(String api, Map<String, Object> paramMap);

    /**
     * 根据用户ID获取用户信息
     *
     * @param paramMap
     * @return
     */
    JSONObject usersShow(String api, Map<String, Object> paramMap);

}
