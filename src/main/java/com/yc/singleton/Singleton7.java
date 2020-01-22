package com.yc.singleton;

/**
 * 静态内部类，可用
 *
 * @version 1.0 create at 2020/1/22
 * @auther yangchuan
 */
public class Singleton7 {


    private Singleton7() {

    }

    private static class SingletonInstance {
        private static final Singleton7 INSTANCE = new Singleton7();
    }

    public static Singleton7 getInstance() {
        return SingletonInstance.INSTANCE;
    }
}
