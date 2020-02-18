package com.yc.lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock不会像synchronized一样，异常的时候释放锁，所以最佳实践是，finally中
 * 释放锁，以便保证发生异常时，锁一定被释放。
 *
 * @version 1.0 create at 2020/2/4
 * @auther yangchuan
 */
public class MustUnlock {

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        try {
            //获取本锁保护的资源
            System.out.println(Thread.currentThread().getName()+"开始执行任务");
        }finally {
            lock.unlock();
        }
    }


}
