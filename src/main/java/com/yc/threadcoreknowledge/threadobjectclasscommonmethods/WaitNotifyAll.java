package com.yc.threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 3个线程，线程1和线程2首先被阻塞，
 * 线程3唤醒他们，notify,notifyAll
 * start先执行不代表线程先启动
 *
 * @version 1.0 create at 2020/1/19
 * @auther yangchuan
 */
public class WaitNotifyAll implements Runnable {

    private static final Object resourced = new Object();

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new WaitNotifyAll();
        Thread threadA = new Thread(r);
        Thread threadB = new Thread(r);
        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourced){
                    resourced.notifyAll();
                    System.out.println("ThreadC notified.");
                }
            }
        });
        threadA.start();
        threadB.start();
        Thread.sleep(200);
        threadC.start();
    }

    @Override
    public void run() {
        synchronized (resourced) {
            System.out.println(
                    Thread.currentThread().getName() + " get resourceA lock"
            );

            try {
                System.out.println(Thread.currentThread().getName() + " waits to start");

                resourced.wait();
                System.out.println(Thread.currentThread().getName() + " is waiting to end");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
