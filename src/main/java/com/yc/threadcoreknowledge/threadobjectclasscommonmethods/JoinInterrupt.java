package com.yc.threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 演示join期间被中断的结果
 *
 * @version 1.0 create at 2020/1/19
 * @auther yangchuan
 */
public class JoinInterrupt {

    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //中断主线程
                    mainThread.interrupt();
                    Thread.sleep(5000);
                    System.out.println("Thread1 finished.");
                } catch (InterruptedException e) {
                    System.out.println("子线程中断");
//                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        System.out.println("等待子线程运行完毕");
        try {
            thread1.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"主线程中断了");
//            e.printStackTrace();
            //主线程被中断，中断掉子线程
            thread1.interrupt();
        }
        System.out.println("子线程运行完毕");
    }
}
