package com.alibaba.chord.utils;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import lombok.Data;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.util.StringUtils;

/**
 * @Author: zhang
 * @Time:2017/5/21
 */
@Data
public class JianCacheBean implements FactoryBean<ConcurrentMapCache>, BeanNameAware, InitializingBean {

    /**
     * 默认过期时间
     */
    private long expired = 1800;

    private long maximumSize = 1000;

    private boolean isAllowNullValues = false;

    private String name = "";

    private ConcurrentMap<Object, Object> store;

    private boolean allowNullValues = true;

    private ConcurrentMapCache cache;

    public void setName(String name) {
        this.name = name;
    }

    public void setStore(ConcurrentMap<Object, Object> store) {
        this.store = store;
    }

    public void setAllowNullValues(boolean allowNullValues) {
        this.allowNullValues = allowNullValues;
    }

    @Override
    public void setBeanName(String beanName) {
        if (!StringUtils.hasLength(this.name)) {
            setName(beanName);
        }
    }

    @Override
    public ConcurrentMapCache getObject() {
        return this.cache;
    }

    @Override
    public Class<?> getObjectType() {
        return ConcurrentMapCache.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() {
        this.cache = new ConcurrentMapCache(this.name, CacheBuilder.newBuilder().expireAfterWrite(this.expired,
            TimeUnit.SECONDS)
            .maximumSize(this.maximumSize)
            .build()
            .asMap(), isAllowNullValues);
    }
}
