package com.yc.singleton;

/**
 * 懒汉式（线程不安全） 不推荐
 *
 * @version 1.0 create at 2020/1/22
 * @auther yangchuan
 */
public class Singleton5 {

    private static Singleton5 INSTANCE;

    private Singleton5() {

    }

    public synchronized static Singleton5 getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton5.class){
                INSTANCE = new Singleton5();
            }
        }
        return INSTANCE;
    }
}
