package com.yc.threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * ID从1开始，JVM运行起来后，我们自己创建的线程ID早已不是0
 *
 * @version 1.0 create at 2020/1/19
 * @auther yangchuan
 */
public class Id {

    public static void main(String[] args) {
        Thread thread = new Thread();
        System.out.println("主线程ID：" + Thread.currentThread().getId());
        //JVM在背后创建了很多线程
        System.out.println("子线程ID：" + thread.getId());
    }
}
