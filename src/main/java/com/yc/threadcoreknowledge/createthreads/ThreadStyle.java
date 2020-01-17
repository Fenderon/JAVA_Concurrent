package com.yc.threadcoreknowledge.createthreads;

/**
 * 用Thread方式实现线程
 *
 * @version 1.0 create at 2020/1/17
 * @auther yangchuan
 */
public class ThreadStyle extends Thread {

    @Override
    public void run() {
        System.out.println("用Thread方法实现线程");
    }

    public static void main(String[] args) {
        new ThreadStyle().start();
    }
}
