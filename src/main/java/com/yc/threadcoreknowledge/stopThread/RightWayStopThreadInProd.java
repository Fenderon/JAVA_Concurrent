package com.yc.threadcoreknowledge.stopThread;

/**
 * 最佳实践：catch了InterruptedException之后的优先选择：
 * 在方法签名中抛出异常
 * 那么在run（）中就会强制try/catch
 *
 * @version 1.0 create at 2020/1/18
 * @auther yangchuan
 */
public class RightWayStopThreadInProd implements Runnable {

    @Override
    public void run() {
        while (true &&
                !Thread.currentThread().isInterrupted()) {
            System.out.println("go");
            try {
                throwInMethod();
            } catch (InterruptedException e) {
                e.printStackTrace();
                //保存日志，停止程序
                System.out.println("保存日志，停止程序");
                break;
            }
        }
    }

    private void throwInMethod() throws InterruptedException {
        Thread.sleep(1000);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProd());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
