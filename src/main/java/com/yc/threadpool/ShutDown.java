package com.yc.threadpool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 演示关闭线程池
 *
 * @version 1.0 create at 2020/2/4
 * @auther yangchuan
 */
public class ShutDown {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new ShutDownTask());
        }
        Thread.sleep(1500);
        System.out.println(executorService.isShutdown());
//        executorService.shutdown();
//        boolean b = executorService.awaitTermination(3, TimeUnit.SECONDS);
//        System.out.println("-----"+ b);
//        executorService.execute(new ShutDownTask());
//        System.out.println(executorService.isShutdown());
//        System.out.println(executorService.isTerminated());
        List<Runnable> runnableList = executorService.shutdownNow();

    }
}
class ShutDownTask implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"：被中断了");

        }
        System.out.println(Thread.currentThread().getName());
    }
}
