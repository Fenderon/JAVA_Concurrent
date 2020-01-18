package com.yc.threadcoreknowledge.stopThread;

/**
 * run 方法内没有sleep或者wait方式时，停止线程
 *
 * @version 1.0 create at 2020/1/18
 * @auther yangchuan
 */
public class RightWayStopThreadWithoutSleep implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadWithoutSleep());
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }

    @Override
    public void run() {
        int num = 0;

        //如果没有中断
        while (!Thread.currentThread().isInterrupted()
        && num <= Integer.MAX_VALUE / 2) {
            if (num % 10000 == 0) {
                System.out.println(num + "是10000的倍数");
            }
            num++;
        }
        System.out.println("运行结束了");
    }
}
