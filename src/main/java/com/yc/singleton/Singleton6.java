package com.yc.singleton;

/**
 * 懒汉式 双重检查（线程安全） 推荐面试使用
 *
 * @version 1.0 create at 2020/1/22
 * @auther yangchuan
 */
public class Singleton6 {

    //防止重排序
    private volatile static Singleton6 INSTANCE;

    private Singleton6() {

    }

    //线程安全；延迟加载；效率较高
    public static Singleton6 getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton6.class) {
                //synchronized 可见性，可以看到变化
                if (INSTANCE == null) {
                    //创建空对象
                    //初始化
                    //引用指向
                    //可能会重排序，空指针
                    INSTANCE = new Singleton6();
                }
            }
        }
        return INSTANCE;
    }
}
