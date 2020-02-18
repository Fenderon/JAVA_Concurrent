package com.yc.threadpool;

/**
 * Description TODO
 *
 * @version 1.0 create at 2020/2/4
 * @auther yangchuan
 */
public class EveryTaskOneThread {

    public static void main(String[] args) {
        Thread thread = new Thread(new Task());
        thread.start();
    }

    static class Task implements Runnable{

        @Override
        public void run() {
            System.out.println("done.....");
        }
    }
}
