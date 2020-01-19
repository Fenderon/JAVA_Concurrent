package com.yc.threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Description：每隔1秒钟输出当前时间，被中断
 * Thread.sleep()
 * TimeUnit.SECONDS.sleep()：更优雅
 *
 * @version 1.0 create at 2020/1/19
 * @auther yangchuan
 */
public class SleepInterupted implements Runnable{

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new SleepInterupted());
        thread.start();
        Thread.sleep(6500);
        thread.interrupt();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10 ; i++) {
            System.out.println(new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("我被中断了!");
                e.printStackTrace();
            }

        }
    }
}
