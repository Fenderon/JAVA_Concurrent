package com.yc.immutable;

/**
 * Description TODO
 *
 * @version 1.0 create at 2020/2/5
 * @auther yangchuan
 */
public class FinalStringDemo2 {


    public static void main(String[] args) {
        String a = "wukong2"; // 常量池
        final String b = getDashixiong(); //运行期
        String c = b + 2; //运行期赋值，堆
        System.out.println(a == c);
    }

    private static String getDashixiong() {
        return "wukong";
    }
}
