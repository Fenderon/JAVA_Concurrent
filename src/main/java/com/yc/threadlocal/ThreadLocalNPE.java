package com.yc.threadlocal;

/**
 * Description TODO
 *
 * @version 1.0 create at 2020/2/3
 * @auther yangchuan
 */
public class ThreadLocalNPE {

    ThreadLocal<Long> longThreadLocal =new ThreadLocal<>();

    public void set(){
        longThreadLocal.set(Thread.currentThread().getId());
    }

    //注意返回类型，包装型，不能写成基础类型，否则抛出NPE；
    public Long get(){
        return longThreadLocal.get();
    }

    public static void main(String[] args) {
        ThreadLocalNPE threadLocalNPE = new ThreadLocalNPE();

        System.out.println(threadLocalNPE.get());

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocalNPE.set();
                System.out.println(threadLocalNPE.get());
            }
        });
        thread1.start();
    }
}
