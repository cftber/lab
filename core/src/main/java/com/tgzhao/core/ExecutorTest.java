package com.tgzhao.core;

import com.tgzhao.core.thread.Executor;

/**
 * Created by tgzhao on 16/7/16.
 */
public class ExecutorTest {
    public static void main(String[] args) {
        Integer.valueOf("123");

        Executor.executeTask(new Runnable() {
            @Override
            public void run() {
                System.out.println("executor test");
            }
        });
    }
}
