package com.yc.flowcontrol.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition基本用法
 *
 * @version 1.0 create at 2020/2/17
 * @auther yangchuan
 */
public class ConditionDemo1 {

    private ReentrantLock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    void method1() throws InterruptedException {
        lock.lock();
        try {
            System.out.println("条件不满足，开始await()");
            condition.await();
            System.out.println("条件满足了，开始执行后续操作");
        }finally {
            lock.unlock();
        }
    }
    void method2(){
        lock.lock();;
        try {
            System.out.println("准备工作完成，开始唤醒其他线程");
            condition.signal();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionDemo1 conditionDemo1 = new ConditionDemo1();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    conditionDemo1.method2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        conditionDemo1.method1();
    }
}
