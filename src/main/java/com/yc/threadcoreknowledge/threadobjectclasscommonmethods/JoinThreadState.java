package com.yc.threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 先join再mainThread.getState()
 * 通过debugger看线程join前后状态的对比
 *
 * @version 1.0 create at 2020/1/19
 * @auther yangchuan
 */
public class JoinThreadState {

    public static void main(String[] args) throws InterruptedException {
        Thread mainThread = Thread.currentThread();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);

                    //WAITING
                    System.out.println(mainThread.getState());
                    System.out.println("Thread-0运行结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        System.out.println("等待子线程运行完毕");

        //子线程结束时会唤醒主线程
        thread.join();
        System.out.println("子线程运行完毕");
    }
}
