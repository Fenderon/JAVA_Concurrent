package com.yc.icache;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * 最简单的缓存形式：HashMap
 *
 * @version 1.0 create at 2020/2/27
 * @auther yangchuan
 */
public class ICache {

    private final HashMap<String, Integer> cache = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        ICache cache = new ICache();
        System.out.println("开始计算了");
        Integer result = cache.computer("13");
        System.out.println("第一次计算结果："+result);
        result = cache.computer("13");
        System.out.println("第二次计算结果："+result);
    }

    public Integer computer(String userId) throws InterruptedException {
        Integer result = cache.get(userId);
        //先检查HashMap里面有木有保存过之前的计算结果
        if (result == null) {
            //如果缓存找不到，那么现在计算一下结果，并且保存到HashMap中
            result = doCompute(userId);
            cache.put(userId,result);
        }
        return result;
    }

    private Integer doCompute(String userId) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return new Integer(userId);
    }
}
