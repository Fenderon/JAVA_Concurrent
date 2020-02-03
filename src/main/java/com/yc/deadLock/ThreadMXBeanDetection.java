package com.yc.deadLock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Description TODO
 *
 * @version 1.0 create at 2020/1/31
 * @auther yangchuan
 */
public class ThreadMXBeanDetection implements Runnable {

    int flag = 1;

    static Object o1 = new Object();
    static Object o2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        ThreadMXBeanDetection r1 = new ThreadMXBeanDetection();
        ThreadMXBeanDetection r2 = new ThreadMXBeanDetection();
        r1.flag = 1;
        r2.flag = 0;
        new Thread(r1).start();
        new Thread(r2).start();

        Thread.sleep(1000);
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] deadLockedThreads = threadMXBean.findDeadlockedThreads();
        if (deadLockedThreads != null && deadLockedThreads.length > 0) {
            System.out.println("发现死锁");
            for (int i = 0; i < deadLockedThreads.length; i++) {
                ThreadInfo threadInfo = threadMXBean.getThreadInfo(deadLockedThreads[i]);
                System.out.println(threadInfo.getThreadName());


            }
        }
    }

    @Override
    public void run() {
        System.out.println("flag = " + flag);

        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("线程1成功拿到锁");
                }
            }
        }

        if (flag == 0) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("线程2成功拿到锁");
                }
            }
        }
    }
}
