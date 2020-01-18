package com.yc.threadcoreknowledge.stopThread;

/**
 * 如果在运行中，每次循环都会调用sleep或wait等方法，
 * 那么不需要每次迭代都检查中断
 *
 * @version 1.0 create at 2020/1/18
 * @auther yangchuan
 */
public class RightWayStopThreadWithSleepEveryLoop {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            try {
                //这里不需要判断中断信号
                while (num <= 10000) {
                    if (num % 100 == 0) {
                        System.out.println(num + "是100的倍数");
                    }
                    num++;
                    //sleep时收到中断信号，抛出异常,清除interrupt标记
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}
