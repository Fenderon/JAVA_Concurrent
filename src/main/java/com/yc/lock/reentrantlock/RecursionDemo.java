package com.yc.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 递归演示可重入锁
 *
 * @version 1.0 create at 2020/2/4
 * @auther yangchuan
 */
public class RecursionDemo {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        accessResource();
    }
    private static void accessResource() {
        lock.lock();
        System.out.println("已经对资源进行了处理");
        try {
            if (lock.getHoldCount() < 5) {
                accessResource();
            }
        } finally {
            lock.unlock();
        }
    }
}
