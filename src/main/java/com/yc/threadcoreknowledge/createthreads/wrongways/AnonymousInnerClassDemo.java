package com.yc.threadcoreknowledge.createthreads.wrongways;

/**
 * 匿名内部类创建线程
 *
 * @version 1.0 create at 2020/1/17
 * @auther yangchuan
 */
public class AnonymousInnerClassDemo {

    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                System.out.println(
                        Thread.currentThread().getName()
                );
            }
        }.start();

        new Thread(new Runnable() {
            public void run() {
                System.out.println(
                        Thread.currentThread().getName()
                );
            }
        }).start();
    }
}
