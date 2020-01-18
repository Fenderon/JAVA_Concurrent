package com.yc.threadcoreknowledge.sixstates;

/**
 * Description:
 * 展示Blocked，Waiting，TimedWaiting
 *
 * @version 1.0 create at 2020/1/18
 * @auther yangchuan
 */
public class BlockedWaitingTimedWaiting implements Runnable{

    public static void main(String[] args) throws InterruptedException {
        BlockedWaitingTimedWaiting runnable = new BlockedWaitingTimedWaiting();

        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.start();

        Thread.sleep(100);
        //打印出Timed_Waiting状态，因为正在执行Thread.sleep(2000)
        System.out.println(thread1.getState());
        //打印出BLOCKED，因为拿不到锁
        System.out.println(thread2.getState());

        Thread.sleep(1300);
        //打印出WAITING，
        System.out.println(thread1.getState());

    }
    @Override
    public void run() {
        syn();
    }

    private synchronized void syn(){
        try {
            Thread.sleep(1000);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
