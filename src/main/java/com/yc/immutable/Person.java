package com.yc.immutable;

/**
 * 不可变的对象，演示其他类无法修改这个对象，即使public也不行
 *
 * @version 1.0 create at 2020/2/5
 * @auther yangchuan
 */
public class Person {

    final int age = 18;

    final String name = "Alice";

}
