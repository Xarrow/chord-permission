package com.alibaba.chord.service.base.service.impl;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RunnableFuture;

import com.alibaba.chord.service.base.service.WeiboOauthService;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;

import com.google.common.collect.Maps;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author: zhang
 * @Time:2017/5/8
 */
@Service
public class WeiboOauthServiceImpl extends AbstractOauthService implements WeiboOauthService {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(WeiboOauthService.class);
    private static JSONObject accessJson = new JSONObject();
    @Getter
    private String accessToken;

    @Getter
    private String uId;

    private void setAccessToken() {
        this.accessToken = StringUtils.isBlank(String.valueOf(accessJson.get("access_token"))) ? "" : String.valueOf(
            accessJson.get("access_token"));
    }

    private void setuId() {
        this.uId = StringUtils.isBlank(String.valueOf(accessJson.get("uid"))) ? "" : String.valueOf(
            accessJson.get("uid"));
    }

    @Value("${client_id}")
    private String clientId;
    @Value("${client_secret}")
    private String clientSecret;
    @Value("${grant_type}")
    private String grantType;
    @Value("${redirect_uri}")
    private String redirectUri;

    @Override
    public JSONObject getAccessToken(String api, Map<String, Object> paramMap) {
        if (StringUtils.isBlank(api)) {
            api = "https://api.weibo.com/oauth2/access_token";
        }
        paramMap.put("client_id", clientId);
        paramMap.put("client_secret", clientSecret);
        paramMap.put("grant_type", grantType);
        paramMap.put("redirect_uri", redirectUri);
        accessJson = JSONObject.parseObject(super.postForInfo(api, paramMap));
        setAccessToken();
        setuId();
        return accessJson;
    }

    ExecutorService executorService = Executors.newFixedThreadPool(5);

    /**
     * http://open.weibo.com/wiki/2/users/show
     *
     * @param api
     * @param paramMap
     * @return
     */
    @Override
    public JSONObject usersShow(String api, Map<String, Object> paramMap) {
        JSONObject resultJson = new JSONObject();
        if (StringUtils.isNotBlank(accessToken)) {
            Map<String, Object> tmpMap = Maps.newHashMap();
            tmpMap.put("access_token", accessToken);

            Future<JSONObject> future = executorService.submit(new Callable<JSONObject>() {
                @Override
                public JSONObject call() throws Exception {
                    return JSONObject.parseObject(
                        WeiboOauthServiceImpl.super.getForInfo("https://api.weibo.com/2/account/profile/email.json",
                            tmpMap));
                }
            });

            paramMap.put("access_token", accessToken);
            paramMap.put("screen_name",
                StringUtils.isBlank(String.valueOf(paramMap.get("screen_name"))) || String.valueOf(
                    paramMap.get("screen_name")).equals("null") ? ""
                    : String.valueOf(paramMap.get("screen_name")));

            if (StringUtils.isBlank(String.valueOf(paramMap.get("screen_name")))) {
                paramMap.put("uid", StringUtils.isBlank(String.valueOf(paramMap.get("uid"))) || String.valueOf(
                    paramMap.get("uid")).equals("null") ? uId
                    : String.valueOf(paramMap.get("uid")));
            } else {
                paramMap.remove("uid");
            }
            api = StringUtils.isBlank(api) ? "https://api.weibo.com/2/users/show.json" : api;
            resultJson.put("rq_api", api);
            resultJson.put("rq_param", paramMap);
            resultJson.put("raw_rs", JSONObject.parseObject(super.getForInfo(api, paramMap)));
            try {
                resultJson.put("email", future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return resultJson;
        }
        resultJson.put("warn", "usersShow提示,请先进行验证!");
        return resultJson;
    }

    @Override
    public JSONObject homeTimeline(String api, Map<String, Object> paramMap) {
        JSONObject resultJson = new JSONObject();
        if (StringUtils.isNotBlank(accessToken)) {
            paramMap.put("access_token", accessToken);
            paramMap.put("uid", uId);
            api = StringUtils.isBlank(api) ? "https://api.weibo.com/2/statuses/home_timeline.json" : api;
            resultJson.put("request_api", api);
            resultJson.put("request_param", paramMap);
            resultJson.put("raw_res", JSONObject.parseObject(super.getForInfo(api, paramMap)));
            return resultJson;
        }
        logger.warn("[X] homeTimeline提示,请先进行验证!");
        return resultJson;
    }

    @Override
    public JSONObject statusUpdate(String api, Map<String, Object> paramMap) {
        JSONObject resultJson = new JSONObject();
        if (StringUtils.isNotBlank(accessToken)) {
            paramMap.put("access_token", accessToken);
            api = StringUtils.isBlank(api) ? "https://api.weibo.com/2/statuses/update.json" : api;
            resultJson.put("request_api", api);
            resultJson.put("request_param", paramMap);
            resultJson.put("raw_res", JSONObject.parseObject(super.postForInfo(api, paramMap)));
        }
        return resultJson;
    }

    @Override
    public JSONObject userTimeline(String api, Map<String, Object> paramMap) {
        JSONObject resultJson = new JSONObject();
        if (StringUtils.isNotBlank(accessToken)) {
            paramMap.put("access_token", accessToken);
            api = StringUtils.isBlank(api) ? "https://api.weibo.com/2/statuses/user_timeline.json" : api;
            resultJson.put("request_api", api);
            resultJson.put("request_param", paramMap);
            resultJson.put("raw_res", JSONObject.parseObject(super.getForInfo(api, paramMap)));
        }
        return resultJson;
    }
}
