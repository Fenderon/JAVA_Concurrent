package com.yc.singleton;

/**
 * 饿汉式（线程不安全） 不可用
 *
 * @version 1.0 create at 2020/1/22
 * @auther yangchuan
 */
public class Singleton3 {

    private static Singleton3 INSTANCE;

    private Singleton3() {

    }

    public static Singleton3 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton3();
        }
        return INSTANCE;
    }
}
