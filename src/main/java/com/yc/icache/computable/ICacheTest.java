package com.yc.icache.computable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description TODO
 *
 * @version 1.0 create at 2020/2/27
 * @auther yangchuan
 */
public class ICacheTest {

    static ICache1<String, Integer> expensiveComputer =
            new ICache1<>(new ExpensiveFunction());

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(50);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 50; i++) {
            final int x = i;
            service.submit(new Runnable() {
                @Override
                public void run() {
                    Integer result = null;
                    try {
                        result = expensiveComputer.compute("666");
                        System.out.println(x);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(result);
                }
            });
        }
        service.shutdown();

        while (!service.isTerminated()) {

        }
        System.out.println("耗时：" + (System.currentTimeMillis() - start));
    }
}
