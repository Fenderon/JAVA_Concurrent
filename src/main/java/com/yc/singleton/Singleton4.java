package com.yc.singleton;

/**
 * 懒汉式（线程安全） 不推荐
 *
 * @version 1.0 create at 2020/1/22
 * @auther yangchuan
 */
public class Singleton4 {

    private static Singleton4 INSTANCE;

    private Singleton4() {

    }

    public synchronized static Singleton4 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton4();
        }
        return INSTANCE;
    }
}
