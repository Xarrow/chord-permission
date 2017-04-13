package service.impl;

import java.util.Arrays;

import javax.annotation.Resource;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import service.CacheService;
import utils.StaticValue;

/**
 * Created by wb-zj268791 on 2017/3/31.
 */
@Service
public class CacheServiceImpl implements CacheService {
    @Resource
    private CacheManager cacheManager;

    @Override
    public String flushCacheByName(String name) {
        try {
            Cache cache = cacheManager.getCache(StaticValue.CACHE_NAME);
            if (null != cache.get(name)) {
                cache.evict(name);
                return "已经成功清除缓存";
            } else {
                return "缓存为空";
            }
        } catch (Exception ex) {
            return "[err:" + Arrays.toString(ex.getStackTrace()) + "]";
        }
    }

    @Override
    public String flushAllCache() {
        try {
            Cache cache = cacheManager.getCache(StaticValue.CACHE_NAME);
            cache.clear();
            return "已经成功清除缓存";
        } catch (Exception ex) {
            return "[err:" + Arrays.toString(ex.getStackTrace()) + "]";
        }
    }

    @Override
    public Object showCache(String name) {
        Cache cache = cacheManager.getCache(StaticValue.CACHE_NAME);
        return cache.get(name, Object.class);
    }

    @Override
    public Object showAllCache() {
        Cache cache = cacheManager.getCache(StaticValue.CACHE_NAME);
        return cache.getNativeCache();
    }
}
