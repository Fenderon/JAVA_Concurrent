package com.yc.immutable;

/**
 * Description TODO
 *
 * @version 1.0 create at 2020/2/5
 * @auther yangchuan
 */
public class FinalStringDemo1 {


    public static void main(String[] args) {
        String a = "wukong2"; // 常量池
        final String b = "wukong"; //编译期 常量池
        String d = "wukong";
        String c = b + 2; //编译器赋值，常量值
        String e = d + 2; //运行期赋值，堆
        System.out.println(a == c);
        System.out.println(a == e);
    }
}
