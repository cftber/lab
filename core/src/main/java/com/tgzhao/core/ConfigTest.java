package com.tgzhao.core;

import com.tgzhao.core.config.Configuration;

/**
 * Created by tgzhao on 2016/7/18.
 */
public class ConfigTest {

    public static void main(String[] args) throws InterruptedException {
        Configuration.get("name");
        System.out.println(Configuration.get("mq.uname"));
    }

}
