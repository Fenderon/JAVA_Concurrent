package com.yc.threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示sleep不释放lock（lock需要手动释放）
 *
 * @version 1.0 create at 2020/1/19
 * @auther yangchuan
 */
public class SleepDontReleaseLock implements Runnable{

    private static final Lock lock = new ReentrantLock();


    @Override
    public void run() {
        lock.lock();

        System.out.println("线程"+Thread.currentThread().getName()+"获取到了锁");

        try {
            Thread.sleep(5000);

            System.out.println("线程"+Thread.currentThread().getName()+"已经苏醒");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(new SleepDontReleaseLock()).start();
        new Thread(new SleepDontReleaseLock()).start();
    }
}
