package com.yc.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

/**
 * Description TODO
 *
 * @version 1.0 create at 2020/2/4
 * @auther yangchuan
 */
public class LongAccumulatorDemo {

    public static void main(String[] args) {
        LongAccumulator ac = new LongAccumulator(
                (x, y) -> x + y, 0);
//        ac.accumulate(1);
//        ac.accumulate(2);

        ExecutorService executorService = Executors.newFixedThreadPool(8);

        IntStream.range(1, 10).forEach(
                i -> executorService.submit(
                        () -> ac.accumulate(i))
        );

        executorService.shutdown();
        while (!executorService.isTerminated()){

        }
        System.out.println(ac.getThenReset());
    }
}
