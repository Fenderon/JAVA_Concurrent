package com.yc.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 演示非公平和公平的读写锁策略
 *
 * @version 1.0 create at 2020/2/5
 * @auther yangchuan
 */
public class NonfairBargeDemo {

    private final static ReentrantReadWriteLock reentrantReadWriteLock
            = new ReentrantReadWriteLock(true);

    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();

    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    private static void read() {
        System.out.println(Thread.currentThread().getName() + "开始尝试获取读锁");
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()
                    + "得到读锁，正在读取");
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()
                    + "释放读锁");
            readLock.unlock();
        }
    }

    private static void write() {
        System.out.println(Thread.currentThread().getName() + "开始尝试获取写锁");
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()
                    + "得到写锁，正在写入");
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()
                    + "释放写锁");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> write(), "Thread 0 ").start();
        new Thread(() -> read(), "Thread 1 ").start();
        new Thread(() -> read(), "Thread 2 ").start();
        new Thread(() -> write(), "Thread 3 ").start();
        new Thread(() -> read(), "Thread 4 ").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Thread thread[] = new Thread[1000];
                for (int i = 0; i < 1000; i++) {
                    thread[i] = new Thread(() -> read(), "子线程创建的Thread" + i);

                }
                for (int i = 0; i < 1000; i++) {
                    thread[i].start();
                }
            }
        }).start();

    }

}
