package com.yc.collections.copyonwrite;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 对比两个迭代器
 *
 * @version 1.0 create at 2020/2/6
 * @auther yangchuan
 */
public class CopyOnWriteArrayListDemo2 {

    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>(new Integer[]{1, 2, 3});

        System.out.println(list);
        Iterator<Integer> itr1 = list.iterator();
        list.add(4);

        System.out.println(list);
        Iterator<Integer> itr2 = list.iterator();

        //还是旧数据
        itr1.forEachRemaining(System.out::println);
        itr2.forEachRemaining(System.out::println);
    }
}
