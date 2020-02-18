package com.yc.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 用AQS 实现一个简单的线程协作器
 *
 * @version 1.0 create at 2020/2/18
 * @auther yangchuan
 */
public class OneShotLatch {

    private final Sync sync = new Sync();

    public void signal() {
        sync.releaseShared(0);
    }

    public void await() {
        sync.acquireShared(0);
    }

    private class Sync
            extends AbstractQueuedSynchronizer {

        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(1);
            return true;
        }

        @Override
        protected int tryAcquireShared(int arg) {
            return (getState() == 1) ? 1 : -1;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        OneShotLatch oneShotLatch = new OneShotLatch();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"尝试获取Latch，获取失败就等待");
                    oneShotLatch.await();
                    System.out.println("开闸放行"+Thread.currentThread().getName()+"继续运行");
                }
            }).start();

        }

        Thread.sleep(2000);
        oneShotLatch.signal();
    }

}
