package com.yc.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Description TODO
 *
 * @version 1.0 create at 2020/2/4
 * @auther yangchuan
 */
public class GetHoldCount {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        System.out.println(lock.getHoldCount());
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.unlock();
    }
}
