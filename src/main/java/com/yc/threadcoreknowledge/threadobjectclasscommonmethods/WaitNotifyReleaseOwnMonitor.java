package com.yc.threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 证明wait只是放当前的那把锁
 *
 * @version 1.0 create at 2020/1/19
 * @auther yangchuan
 */
public class WaitNotifyReleaseOwnMonitor {

    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();


    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    System.out.println("ThreadA get resourceA lock.");

                    synchronized (resourceB) {
                        System.out.println("ThreadA get resourceB lock.");

                        try {
                            System.out.println("ThreadA releases resourceA lock");
                            resourceA.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (resourceA) {
                    System.out.println("ThreadB get resourceA lock.");

                    System.out.println("ThreadB tries to resourceB lock.");

                    synchronized (resourceB) {
                        System.out.println("ThreadB get resourceB lock.");
                    }
                }
            }
        });

        thread1.start();
        thread2.start();

    }

}
