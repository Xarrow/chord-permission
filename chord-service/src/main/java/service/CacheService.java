package service;

/**
 * Created by wb-zj268791 on 2017/3/31.
 */
public interface CacheService {

    String flushCacheByName(String name);

    String flushAllCache();

    Object showCache(String name);

    Object showAllCache();
}
