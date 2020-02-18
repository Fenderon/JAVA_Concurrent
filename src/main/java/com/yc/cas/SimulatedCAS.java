package com.yc.cas;

/**
 * 模拟CAS操作，等价代码
 *
 * @version 1.0 create at 2020/2/4
 * @auther yangchuan
 */
public class SimulatedCAS {

    private volatile int value;

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;

        if (oldValue == expectedValue) {
            value = newValue;
        }
        return oldValue;
    }
}
