package com.yc.immutable;

/**
 * 演示final变量
 *
 * @version 1.0 create at 2020/2/5
 * @auther yangchuan
 */
public class FinalVariableDemo {

    private static final int a;

    public FinalVariableDemo() {

    }

    static {
        a = 7;
    }

    void testFinal() {
        final int b;
        b = 5;
        System.out.println(b);
    }

}
