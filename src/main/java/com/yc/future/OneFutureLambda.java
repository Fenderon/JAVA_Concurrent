package com.yc.future;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 演示一个Future的使用方法 - lambda
 *
 * @version 1.0 create at 2020/2/27
 * @auther yangchuan
 */
public class OneFutureLambda {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);

        Callable<Integer> callable = ()->{
            Thread.sleep(3000);
            return new Random().nextInt(100);
        };

        Future<Integer> future = service.submit(callable);

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        service.shutdown();

    }
}
