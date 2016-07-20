package com.tgzhao.core.cache;

/**
 * Created by tgzhao on 16/7/16.
 */
public interface Cache {
    public void set(String key);

    public void set(String key, long expired);

    public void set(String key, Object value);

    public void set(String key, Object value, long expired);

    public Object get(String key);
}
