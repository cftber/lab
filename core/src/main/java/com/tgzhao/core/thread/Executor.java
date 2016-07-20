package com.tgzhao.core.thread;

/**
 * Created by tgzhao on 16/7/16.
 */
public abstract class Executor {
    public static void executeTask(Runnable task) {
        Thread thread = new Thread(task);
        thread.run();
    }
}
