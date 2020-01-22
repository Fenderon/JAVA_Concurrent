package com.yc.singleton;

/**
 * 饿汉式（静态代码块）可用
 *
 * @version 1.0 create at 2020/1/22
 * @auther yangchuan
 */
public class Singleton2 {

    private final static Singleton2 INSTANCE;

    static {
        INSTANCE = new Singleton2();
    }

    private Singleton2() {

    }

    public static Singleton2 getInstance() {
        return INSTANCE;
    }
}
