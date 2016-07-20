package com.tgzhao.core.cache;

/**
 * Created by tgzhao on 16/7/16.
 */
public abstract class CacheManager {
    private static final ConcurrentHashMapCache CONCURRENT_HASH_MAP_CACHE = new ConcurrentHashMapCache();
    public static ConcurrentHashMapCache getConcurrentHashMapCache() {
        return CONCURRENT_HASH_MAP_CACHE;
    }
}
