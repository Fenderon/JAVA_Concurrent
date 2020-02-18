package com.yc.collections.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Description TODO
 *
 * @version 1.0 create at 2020/2/6
 * @auther yangchuan
 */
public class ArrayBlockingQueueDemo {
    private final static ArrayBlockingQueue queue = new ArrayBlockingQueue<String>(3);

    public static void main(String[] args) {
        Intervierer r1 = new Intervierer(queue);

        Consumer r2 = new Consumer(queue);
        new Thread(r1).start();
        new Thread(r2).start();
    }
}

class Intervierer implements Runnable {

    BlockingQueue queue;

    public Intervierer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("10个候选人都来啦");
        for (int i = 0; i < 10; i++) {
            String candidate = "Candidate" + i;
            try {
                queue.put(candidate);
                System.out.println("安排好了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            queue.put("stop");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class Consumer implements Runnable {

    BlockingQueue<String> queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String msg;
        try {
            while (!(msg = queue.take()).equals("stop")) {
                System.out.println(msg + "到了");
            }
            System.out.println("所有候选人都结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}