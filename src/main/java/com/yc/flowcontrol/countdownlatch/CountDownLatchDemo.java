package com.yc.flowcontrol.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 工厂中、质检、5个工人检查，所有人认为通过，才通过
 *
 * @version 1.0 create at 2020/2/17
 * @auther yangchuan
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(5);
        ExecutorService service = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            final int index = i+1;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        System.out.println("No:" + index + "通过了");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        latch.countDown();
                    }
                }
            };

            service.submit(runnable);
        }

        System.out.println("等待5个人检查.....");
        latch.await();
        System.out.println("已经检查完.....");
    }
}
