package com.tgzhao.core.dal.orm;

import com.tgzhao.core.config.Configuration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tgzhao on 16/7/17.
 */
public abstract class DaoFactory {

    private static Map<Class<?> , BaseDao> daoMap = new HashMap<>();

    static {
        File[] files = Configuration.getClasspathFile("com/zuoxiaolong/dao").listFiles();
        for (int i = 0; i < files.length; i++) {
            String fileName = files[i].getName();
            if (fileName.endsWith(".class")) {
                fileName = fileName.substring(0, fileName.lastIndexOf(".class"));
            }
            try {
                Class<?> clazz = Configuration.getClassLoader().loadClass("com.zuoxiaolong.dao." + fileName);
                if (BaseDao.class.isAssignableFrom(clazz)) {
                    daoMap.put(clazz, (BaseDao) clazz.newInstance());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static <T> T getDao(Class<T> clazz) {
        return (T)daoMap.get(clazz);
    }

}
