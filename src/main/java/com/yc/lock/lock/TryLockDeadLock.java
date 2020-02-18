package com.yc.lock.lock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用Trylock避免死锁
 *
 * @version 1.0 create at 2020/2/4
 * @auther yangchuan
 */
public class TryLockDeadLock implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        TryLockDeadLock r1 = new TryLockDeadLock();
        TryLockDeadLock r2 = new TryLockDeadLock();
        r1.flag = 1;
        r2.flag = 2;
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    int flag = 1;
    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();

    @Override
    public void run() {
        if (flag == 1) {
            try {
                if (lock1.tryLock(800, TimeUnit.MILLISECONDS)) {
                    try {
                        System.out.println("线程1获取到了锁1");
                        Thread.sleep(new Random().nextInt(1000));

                        if (lock2.tryLock(800, TimeUnit.MILLISECONDS)) {
                            try {
                                System.out.println("线程1获取到了锁2");
                                System.out.println("线程1成功获取两把锁");
                            } finally {
                                lock2.unlock();
                            }
                        } else {
                            System.out.println("线程1获取锁2失败，已重试");
                        }
                    } finally {
                        lock1.unlock();
                        Thread.sleep(new Random().nextInt(1000));
                    }
                } else {
                    System.out.println("线程1获取锁1失败，已重试");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (flag == 2) {
            try {
                if (lock2.tryLock(800, TimeUnit.MILLISECONDS)) {
                    try {
                        System.out.println("线程2获取到了锁2");
                        Thread.sleep(new Random().nextInt(1000));

                        if (lock1.tryLock(800, TimeUnit.MILLISECONDS)) {
                            try {
                                System.out.println("线程2获取到了锁1");
                                System.out.println("线程2成功获取两把锁");
                            } finally {
                                lock1.unlock();
                            }
                        } else {
                            System.out.println("线程2获取锁1失败，已重试");
                        }
                    } finally {
                        lock2.unlock();
                        Thread.sleep(new Random().nextInt(1000));
                    }
                } else {
                    System.out.println("线程2获取锁2失败，已重试");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
