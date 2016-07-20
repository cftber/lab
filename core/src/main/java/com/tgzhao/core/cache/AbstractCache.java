package com.tgzhao.core.cache;

/**
 * Created by tgzhao on 16/7/16.
 */
public abstract class AbstractCache implements Cache {

    public static final Object EMPTY = new Object();

    @Override
    public void set(String key) {
        set(key, EMPTY);
    }

    @Override
    public void set(String key, long expired) {
        set(key, EMPTY, expired);
    }

    @Override
    public void set(String key, Object value) {
        set(key, value, 0);
    }
}
