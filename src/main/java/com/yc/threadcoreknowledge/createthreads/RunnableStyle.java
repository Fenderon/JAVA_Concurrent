package com.yc.threadcoreknowledge.createthreads;

/**
 * Runnable方式创建多线程
 *
 * ---- Runnable方法更好
 *
 * @version 1.0 create at 2020/1/17
 * @auther yangchuan
 */
public class RunnableStyle implements Runnable{

    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableStyle());
        thread.start();
    }

    public void run() {
        System.out.println("用Runnable方法实现线程");
    }
}
