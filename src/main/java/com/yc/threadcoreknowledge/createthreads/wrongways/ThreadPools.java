package com.yc.threadcoreknowledge.createthreads.wrongways;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池创建线程的方法
 *  本质：通过new Thread()
 *
 * @version 1.0 create at 2020/1/17
 * @auther yangchuan
 */
public class ThreadPools {

    public static void main(String[] args) {
        ExecutorService executorService =
                Executors.newCachedThreadPool();

        for(int i=0;i<1000;i++){
            executorService.submit(new Task());
        }
    }


}
class Task implements Runnable {
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}