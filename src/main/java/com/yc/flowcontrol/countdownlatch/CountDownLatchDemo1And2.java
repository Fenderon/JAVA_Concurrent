package com.yc.flowcontrol.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 5名选手都准备好了，等待裁判员一声令下，所有人同时开始跑步
 *
 * @version 1.0 create at 2020/2/17
 * @auther yangchuan
 */
public class CountDownLatchDemo1And2 {

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch begin = new CountDownLatch(1);
        ExecutorService service = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            final int index = i + 1;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("No:" + index + "准备好了，等待发令枪");
                        begin.await();
                        System.out.println("No:"+index+"开始跑步");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        begin.countDown();
                    }
                }
            };

            service.submit(runnable);
        }

        Thread.sleep(1000);
        //裁判员检查发令枪
        System.out.println("裁判员检查发令枪.....");
        System.out.println("发令开始，比赛开始.....");
        begin.countDown();
    }
}
