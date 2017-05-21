package com.alibaba.chord.controller;

import java.util.Collection;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.Resource;

import com.alibaba.chord.commons.CommonsValue;
import com.alibaba.chord.utils.ResponseUtil;
import com.alibaba.chord.vo.ResponseVO;
import com.alibaba.fastjson.JSONObject;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhang
 * @Time:2017/5/21
 */
@RestController
@RequestMapping("api/cache")
public class ApiCacheController {
    @Resource
    private CacheManager cacheManager;

    @RequestMapping("showAllCache")
    public ResponseVO showAllCache() {
        ResponseVO responseVO = ResponseUtil.buildVoByFailResult("");
        Collection<String> cacheNames = cacheManager.getCacheNames();
        if (cacheNames.isEmpty()) {
            responseVO = ResponseUtil.buildVoBySuccessResult("cacheNames is Empty");
            return responseVO;
        }
        JSONObject jsonObject = new JSONObject();
        for (String cacheName : cacheNames) {
            jsonObject.put(cacheName, cacheManager.getCache(cacheName));
        }
        responseVO = ResponseUtil.buildVoBySuccessResult(jsonObject);
        return responseVO;
    }

    @RequestMapping("testCache")
    public ResponseVO testCache() {
        ResponseVO responseVO = ResponseUtil.buildVoByFailResult("");
        Cache cache = cacheManager.getCache(CommonsValue.STU_QUERY_CACHENAME);
        ConcurrentMapCache concurrentMapCache = (ConcurrentMapCache)cache;
        concurrentMapCache.clear();
        return responseVO;

    }
}
