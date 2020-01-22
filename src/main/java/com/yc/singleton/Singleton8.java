package com.yc.singleton;

/**
 * 枚举单例，推荐，最好
 * 优点：
 *      写法简单，
 *      线程安全有保障，
 *      懒加载，
 *      避免反序列化破坏单例
 *
 * @version 1.0 create at 2020/1/22
 * @auther yangchuan
 */
public enum Singleton8 {
    INSTANCE;

    public void whatever() {
        System.out.println("枚举单例----");
    }

    public static void main(String[] args) {
        Singleton8.INSTANCE.whatever();
    }
}

