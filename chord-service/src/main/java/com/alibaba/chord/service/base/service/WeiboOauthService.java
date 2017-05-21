package com.alibaba.chord.service.base.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author: zhang
 * @Time:2017/5/14
 */
public interface WeiboOauthService extends OauthService {

    /**
     * 获取当前登录用户及其所关注（授权）用户的最新微博
     *
     * @param paramMap
     * @return
     */
    JSONObject homeTimeline(String api, Map<String, Object> paramMap);

    JSONObject statusUpdate(String api, Map<String, Object> paramMap);

    JSONObject userTimeline(String api, Map<String, Object> paramMap);
}
