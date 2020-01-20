package com.yc.threadcoreknowledge.uncaughtexception;

/**
 * 使用刚才写的MyUncaughtExceptionHanlder
 *
 * @version 1.0 create at 2020/1/19
 * @auther yangchuan
 */
public class UseOwnUncaughtExceptionHandler implements Runnable {

    public static void main(String[] args) throws InterruptedException {

        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHanlder("捕获器1"));

        new Thread(new UseOwnUncaughtExceptionHandler(), "MyThread-1").start();

        Thread.sleep(300);
        new Thread(new UseOwnUncaughtExceptionHandler(), "MyThread-2").start();

        Thread.sleep(300);
        new Thread(new UseOwnUncaughtExceptionHandler(), "MyThread-3").start();

        Thread.sleep(300);
        new Thread(new UseOwnUncaughtExceptionHandler(), "MyThread-4").start();

    }

    @Override
    public void run() {
        throw new RuntimeException();
    }
}