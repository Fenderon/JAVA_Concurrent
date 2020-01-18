package com.yc.threadcoreknowledge.startThread;

/**
 * 演示不能两次调用start方法，否则会报错
 *
 * @version 1.0 create at 2020/1/18
 * @auther yangchuan
 */
public class CantStartTwice {

    public static void main(String[] args) {
        Thread thread  = new Thread();
        thread.start();
        thread.start();
    }
}
