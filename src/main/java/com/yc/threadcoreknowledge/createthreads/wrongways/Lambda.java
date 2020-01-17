package com.yc.threadcoreknowledge.createthreads.wrongways;

/**
 * lambda表达式实现线程
 *
 * @version 1.0 create at 2020/1/17
 * @auther yangchuan
 */
public class Lambda {
    public static void main(String[] args) {
        new Thread(()-> System.out.println(Thread.currentThread().getName())).start();
    }
}
