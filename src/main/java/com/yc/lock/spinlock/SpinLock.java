package com.yc.lock.spinlock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 演示自旋锁
 *
 * @version 1.0 create at 2020/2/5
 * @auther yangchuan
 */
public class SpinLock {

    private AtomicReference<Thread> sign = new AtomicReference<>();

    public void lock(){
        Thread current = Thread.currentThread();
        while (!sign.compareAndSet(null,current)){
            System.out.println("自旋获取失败，再次尝试");
        }
    }
    public void unlock(){
        Thread current = Thread.currentThread();
        sign.compareAndSet(current,null);
    }

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();
        Runnable r = new Runnable(){

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"尝试获取自旋锁");
                spinLock.lock();
                System.out.println(Thread.currentThread().getName()+"获取到了自旋锁");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    spinLock.unlock();
                    System.out.println(Thread.currentThread().getName()+"释放了自旋锁");
                }
            }
        };
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);

        thread1.start();
        thread2.start();
    }
}
