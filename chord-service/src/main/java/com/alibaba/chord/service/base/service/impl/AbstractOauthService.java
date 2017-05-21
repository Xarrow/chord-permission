package com.alibaba.chord.service.base.service.impl;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.LoggerFactory;

/**
 * @Author: zhang
 * @Time:2017/5/7
 */
public abstract class AbstractOauthService {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AbstractOauthService.class);

    /**
     * get 请求
     *
     * @param url
     * @param paramMap
     * @return
     */
    public String getForInfo(String url, Map<String, Object> paramMap) {
        LOGGER.info("getForInfo Api:[{}],入参:[{}]", url, paramMap.toString());
        HttpClient httpClient = new HttpClient();
        GetMethod get = new GetMethod(url);
        NameValuePair[] nameValuePairs = new NameValuePair[paramMap.size()];
        String httpResult = "";
        int i = 0;
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            nameValuePairs[i] = new NameValuePair(entry.getKey(),
                entry.getValue() == null ? "" : String.valueOf(entry.getValue()));
            i++;
        }

        get.setQueryString(nameValuePairs);
        try {
            httpClient.executeMethod(get);
            httpResult = get.getResponseBodyAsString();
        } catch (IOException e) {
            LOGGER.error("getForInfo 发生异常:[{}]", e.getMessage());
        }
        LOGGER.info("getForInfo返回:[{}]", httpResult);
        return httpResult;
    }

    /**
     * @param url      请求api
     * @param paramMap 请求参数
     * @return
     */
    public String postForInfo(String url, Map<String, Object> paramMap) {
        LOGGER.info("postToInfo Api:[{}],入参:[{}]", url, paramMap.toString());
        HttpClient httpClient = new HttpClient();
        PostMethod post = new PostMethod(url);

        String httpResult = "";
        NameValuePair[] nameValuePairs = new NameValuePair[paramMap.size()];
        int i = 0;
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            nameValuePairs[i] = new NameValuePair(entry.getKey(),
                entry.getValue() == null ? "" : String.valueOf(entry.getValue()));
            i++;
        }
        post.setRequestBody(nameValuePairs);
        try {
            httpClient.executeMethod(post);
            httpResult = post.getResponseBodyAsString();
        } catch (IOException e) {
            LOGGER.error("postToInfo 发生异常:[{}]", e.getMessage());
        }
        LOGGER.info("postToInfo返回:[{}]", httpResult);
        return httpResult;
    }
}


