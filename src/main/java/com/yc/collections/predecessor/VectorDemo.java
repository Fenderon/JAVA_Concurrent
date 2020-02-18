package com.yc.collections.predecessor;

import java.util.Vector;

/**
 * 演示Vector，主要是看Vector源码
 *
 * @version 1.0 create at 2020/2/6
 * @auther yangchuan
 */
public class VectorDemo {

    public static void main(String[] args) {
        Vector<String> vector = new Vector<>();
        vector.add("test");
        System.out.println(vector.get(0));
    }
}
