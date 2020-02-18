package com.yc.collections.predecessor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * Description TODO
 *
 * @version 1.0 create at 2020/2/6
 * @auther yangchuan
 */
public class HashtableDemo {

    public static void main(String[] args) {
        Hashtable<String,String> hashtable = new Hashtable<>();
        hashtable.put("学习","50%");
        System.out.println(hashtable.get("学习"));

        Collections.synchronizedMap(new HashMap<>());
    }
}
