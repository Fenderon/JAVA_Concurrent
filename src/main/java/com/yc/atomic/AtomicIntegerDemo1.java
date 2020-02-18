package com.yc.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 演示AtomicInteger的基本用法，对比非原子类的安全问题，使用了原子类之后，
 * 不要加锁，也可以保证线程安全
 *
 * @version 1.0 create at 2020/2/4
 * @auther yangchuan
 */
public class AtomicIntegerDemo1 implements Runnable{

    private static final AtomicInteger atomicInteger = new AtomicInteger();

    public void incrementAtomicc(){
        atomicInteger.getAndIncrement();
    }

    private static volatile int basicCount = 0;

    public void incrementBasic(){
        basicCount++;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            incrementAtomicc();
            incrementBasic();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerDemo1 r = new AtomicIntegerDemo1();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(AtomicIntegerDemo1.atomicInteger);
        System.out.println(AtomicIntegerDemo1.basicCount);

    }
}
