package com.yc.threadcoreknowledge.stopThread;

/**
 * 最佳实践2：在catch子语句中调用
 * Thread.currentThread().interrupt()来恢复设置中断状态，
 * 以便于后续的执行中，依然能够检查到刚才发生了中断。
 *
 * @version 1.0 create at 2020/1/18
 * @auther yangchuan
 */
public class RightWayStopThreadInProd2 implements Runnable {

    @Override
    public void run() {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupted,程序运行结束");
                break;
            }
            System.out.println("go");
            reInterrupt();
        }
    }

    private void reInterrupt() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProd2());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
