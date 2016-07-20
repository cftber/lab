package com.tgzhao.core;

import com.tgzhao.core.cache.Cache;
import com.tgzhao.core.cache.CacheManager;

/**
 * Created by tgzhao on 16/7/16.
 */
public class CacheTest {

    public static void main(String[] args) throws InterruptedException {
        CacheManager.getConcurrentHashMapCache().set("key", "value", 2000);

        for (int i = 0; i < 1020; i++) {
            CacheManager.getConcurrentHashMapCache().set(String.valueOf(i), i, 200);
        }

        System.out.println(CacheManager.getConcurrentHashMapCache().get("23"));
        System.out.println("key is:" + CacheManager.getConcurrentHashMapCache().get("key"));
        Thread.sleep(2000);
        System.out.println("key is:" + CacheManager.getConcurrentHashMapCache().get("key"));
    }

}
