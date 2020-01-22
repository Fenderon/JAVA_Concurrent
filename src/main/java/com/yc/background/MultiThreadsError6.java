package com.yc.background;

import java.util.HashMap;
import java.util.Map;

/**
 * 构造函数中新建线程
 *
 * @version 1.0 create at 2020/1/21
 * @auther yangchuan
 */
public class MultiThreadsError6 {


    //本来private，不能让外部进行修改
    private Map<String, String> states;

    public MultiThreadsError6() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                states = new HashMap<>();
                states.put("1","周一");
                states.put("2","周二");
                states.put("3","周三");
                states.put("4","周四");
            }
        }).start();
    }

    //这里发布出去，，外部可以修改，，溢出了
    public Map<String,String>getStates(){
        return states;
    }

    public static void main(String[] args) {
        MultiThreadsError6 multiThreadsError3 = new MultiThreadsError6();
        Map<String,String> states = multiThreadsError3.getStates();
        System.out.println(states.get("1"));
        states.remove("1");
        System.out.println(states.get("1"));
    }
}
