package com.tgzhao.core.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by tgzhao on 16/7/16.
 */
public class ConcurrentHashMapCache extends AbstractCache {

    private int maxSize = 1000;


    private Map<String, Object> cache = new ConcurrentHashMap<>();

    private Map<String, Long> expiredCache = new ConcurrentHashMap<>();

    ConcurrentHashMapCache() {
    }

    ConcurrentHashMapCache(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public void set(String key, Object value, long expired) {
        if (cache.size() > maxSize) {
            System.out.println("corrent size is :" + cache.size());
            return;
        }

        cache.put(key, value);
        if (expired > 0) {
            expiredCache.put(key, System.currentTimeMillis() + expired);
        }
    }

    @Override
    public Object get(String key) {
        Long time = expiredCache.get(key);
        if (time == null || time > System.currentTimeMillis()) {
            return cache.get(key);
        } else {
            expiredCache.remove(key);
            cache.remove(key);
            return null;
        }
    }
}
