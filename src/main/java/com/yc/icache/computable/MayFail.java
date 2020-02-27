package com.yc.icache.computable;

import java.io.IOException;

/**
 * Description TODO
 *
 * @version 1.0 create at 2020/2/27
 * @auther yangchuan
 */
public class MayFail implements Computable<String, Integer> {

    @Override
    public Integer compute(String arg) throws Exception {
        double random = Math.random();
        if (random > 0.5) {
            throw new IOException("读取文件出错");
        }
        Thread.sleep(3000);
        return Integer.valueOf(arg);
    }
}
