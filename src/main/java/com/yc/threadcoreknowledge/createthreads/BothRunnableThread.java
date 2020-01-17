package com.yc.threadcoreknowledge.createthreads;

/**
 * 同时使用Runnable和Thread两种线程
 *
 * @version 1.0 create at 2020/1/17
 * @auther yangchuan
 */
public class BothRunnableThread {


    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                System.out.println("我来自Runnable");
            }
        }){
            @Override
            public void run() {
                System.out.println("我来自Thread");
            }
        }.start();
    }
}
