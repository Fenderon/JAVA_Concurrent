package com.yc.threadcoreknowledge.uncaughtexception;

/**
 * 单线程，抛出，处理，有异常堆栈
 * 多线程、子线程发生异常，会有什么不同？
 *
 * @version 1.0 create at 2020/1/19
 * @auther yangchuan
 */
public class ExceptionInChildThread implements Runnable{

    public static void main(String[] args) {
        new Thread(new ExceptionInChildThread()).start();;
        System.out.println(".......");
    }

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
