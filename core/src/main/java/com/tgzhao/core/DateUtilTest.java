package com.tgzhao.core;

import com.tgzhao.core.cache.CacheManager;
import com.tgzhao.core.util.DateUtil;

/**
 * Created by tgzhao on 16/7/16.
 */
public class DateUtilTest {

    public static void main(String[] args) {
        String result = DateUtil.longToDate(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");
        System.out.println(result);
    }

}
