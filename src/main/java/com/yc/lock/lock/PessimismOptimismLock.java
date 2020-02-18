package com.yc.lock.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description TODO
 *
 * @version 1.0 create at 2020/2/4
 * @auther yangchuan
 */
public class PessimismOptimismLock {

    int a;

    public static void main(String[] args) {
        //乐观锁
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();
    }

    //悲观锁
    public synchronized void testMethod() {
        a++;
    }
}
