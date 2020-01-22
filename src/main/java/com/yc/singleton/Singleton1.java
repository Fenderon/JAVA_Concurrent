package com.yc.singleton;

/**
 * 饿汉式（静态常量）可用
 *
 * @version 1.0 create at 2020/1/22
 * @auther yangchuan
 */
public class Singleton1 {

    private final static Singleton1 INSTANCE = new Singleton1();

    private Singleton1(){

    }

    public static Singleton1 getInstance(){
        return INSTANCE;
    }
}
