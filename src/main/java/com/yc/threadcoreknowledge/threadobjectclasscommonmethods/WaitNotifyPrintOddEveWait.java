package com.yc.threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * Description：两个线程交替打印0-100的奇偶数，
 * 用wait和notify实现。
 *
 * @version 1.0 create at 2020/1/19
 * @auther yangchuan
 */
public class WaitNotifyPrintOddEveWait {

    private static int count = 0;

    private static final Object lock = new Object();

    //1.拿到锁，我们就打印
    //2.打印完，唤醒其他线程，自己就休眠
    static class TurningRunner implements Runnable{

        @Override
        public void run() {
            while (count <=100){
                synchronized (lock){
                    System.out.println(Thread.currentThread().getName()
                    +":"+count++);
                    lock.notify();
                    if(count<=100){
                        try {
                            //如果任务还没结束，就让出当前的锁，并且自己休息
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new TurningRunner(),"偶数").start();
        Thread.sleep(100);
        new Thread(new TurningRunner(),"奇数").start();
    }

}
