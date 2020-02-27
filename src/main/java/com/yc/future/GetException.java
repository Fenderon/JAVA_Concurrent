package com.yc.future;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 演示get方法过程中抛出异常，for循环为了演示抛出
 * Exception的时机：并不是说一产生异常就抛出，而是get方法执行的时候，才抛出
 *
 * @version 1.0 create at 2020/2/27
 * @auther yangchuan
 */
public class GetException {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);

        Future<Integer> future = service.submit(new CallableTask());

        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(i);
            }
            System.out.println(future.isDone());
            System.out.println(future.get());
        } catch (InterruptedException e) {
            System.out.println("InterruptedException...");
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println("ExecutionException...");
            e.printStackTrace();
        }
        service.shutdown();
    }

    static class CallableTask implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            throw new IllegalArgumentException("error");
        }
    }
}
