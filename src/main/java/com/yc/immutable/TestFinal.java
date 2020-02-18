package com.yc.immutable;

/**
 * 测试final能否被修改
 *
 * @version 1.0 create at 2020/2/5
 * @auther yangchuan
 */
public class TestFinal {

    public static void main(String[] args) {
        final Person person = new Person();
//        person = new Person();
//        person.age = 19;
    }
}
