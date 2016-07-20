package com.tgzhao.core.config;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by tgzhao on 16/7/17.
 */
public class Configuration {

    private static final Properties properties = new Properties();

    private static final String system;

    static {
        system = System.getProperty("os.name").toLowerCase();
        Properties sysPros = System.getProperties();

        File classpath = getClasspathFile("");
        File[] propertyFiles = classpath.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.getName().endsWith(".properties") && !file.getName().startsWith("log4j");
            }
        });
        for (int i = 0; i < propertyFiles.length; i++) {
            try {
                properties.load(new FileInputStream(propertyFiles[i]));
            } catch (IOException e) {
            }
        }
    }

    public static ClassLoader getClassLoader() {
        return Configuration.class.getClassLoader();
    }

    public static File getClasspathFile(String path) {
        return new File(getClassLoader().getResource(path).getFile());
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }


    public static boolean isProductEnv() {
        return system.contains("linux");
    }

    public static String getSiteUrl(){
        return isProductEnv() ? get("context.path.product") : get("context.path");
    }

    public static String getSiteUrl(String path){
        return getSiteUrl() + (path.startsWith("/") ? path : ("/" + path));
    }

}