package com.yc.threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 演示打印main，Thread-0，Thread-1
 *
 * @version 1.0 create at 2020/1/19
 * @auther yangchuan
 */
public class CurrentThread implements Runnable{

    public static void main(String[] args) {
        new CurrentThread().run();
        new Thread(new CurrentThread()).start();
        new Thread(new CurrentThread()).start();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
