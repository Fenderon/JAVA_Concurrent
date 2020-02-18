package com.yc.collections.predecessor;

import java.util.HashMap;
import java.util.Map;

/**
 * 演示map的基本用法
 *
 * @version 1.0 create at 2020/2/6
 * @auther yangchuan
 */
public class MapDemo {

    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<>();
        System.out.println(map.isEmpty());

        map.put("东哥",38);
        map.put("西哥",31);
        System.out.println(map.keySet());
        System.out.println(map.get("东哥"));
        System.out.println(map.size());
        System.out.println(map.containsKey("东哥"));

    }
}
