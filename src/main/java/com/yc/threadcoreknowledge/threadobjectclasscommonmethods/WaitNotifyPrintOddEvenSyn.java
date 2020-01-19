package com.yc.threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * Description: 两个线程交替打印0-100的奇偶数，
 * 用synchronized关键字实现。
 *
 * @version 1.0 create at 2020/1/19
 * @auther yangchuan
 */
public class WaitNotifyPrintOddEvenSyn {

    private static int count;

    private static final Object lock = new Object();

    //新建两个线程
    //1个只处理偶数，第二个只处理奇数（用位运算）
    //synchronized来通信
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < 100) {
                    synchronized (lock) {
                        if ((count & 1) == 0) {
                            System.out.println(Thread.currentThread().getName()
                                    + ":" + count);
                            count++;
                        }
                    }
                }
            }
        },"偶数").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < 100) {
                    synchronized (lock) {
                        if ((count & 1) == 1) {
                            System.out.println(Thread.currentThread().getName()
                                    + ":" + count);
                            count++;
                        }
                    }
                }
            }
        },"奇数").start();
    }
}
