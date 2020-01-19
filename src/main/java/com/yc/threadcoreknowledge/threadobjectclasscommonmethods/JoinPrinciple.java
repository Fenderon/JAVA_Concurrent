package com.yc.threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 通过讲解join原理，分析出join的代替写法
 *
 * @version 1.0 create at 2020/1/19
 * @auther yangchuan
 */
public class JoinPrinciple {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println("Thread-1 finished.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        System.out.println("等待子线程运行完毕");
//        thread.join();
        //等价代码
        synchronized (thread){
            //主线程阻塞掉
            thread.wait();
        }
        System.out.println("子线程运行完毕");
    }
}
