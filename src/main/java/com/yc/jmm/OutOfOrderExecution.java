package com.yc.jmm;

import java.util.concurrent.CountDownLatch;

/**
 * 演示重排序的现象
 * “直到达到某个条件才停止”，测试小概率事件
 *
 * @version 1.0 create at 2020/1/21
 * @auther yangchuan
 */
public class OutOfOrderExecution {

    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {

        int i = 0;
        for (; ; ) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            CountDownLatch latch = new CountDownLatch(1);

            Thread one = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    a = 1;
                    x = b;
                }
            });
            Thread two = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    b = 1;
                    y = a;
                }
            });
            one.start();
            two.start();
            latch.countDown();
            one.join();
            two.join();

            String result = "第" + i + "次 (" + x + "," + y + ")";

            //重排序
            // y = a
            // a = 1
            // x = b
            // b = 1
            if (x == 0 && y == 0) {
                System.out.println(result);
                break;
            } else {
                System.out.println(result);
            }
        }
    }
}
