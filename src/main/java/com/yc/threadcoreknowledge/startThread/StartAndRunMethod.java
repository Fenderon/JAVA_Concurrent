package com.yc.threadcoreknowledge.startThread;

/**
 * 对比start和run两种启动线程的方式
 *
 * @version 1.0 create at 2020/1/18
 * @auther yangchuan
 */
public class StartAndRunMethod {

    public static void main(String[] args) {
        Runnable runnable = ()->{
            System.out.println(Thread
            .currentThread().getName());
        };
        runnable.run();

        new Thread(runnable).start();
    }
}
