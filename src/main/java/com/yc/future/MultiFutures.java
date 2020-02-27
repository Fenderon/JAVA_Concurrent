package com.yc.future;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 演示批量提交任务时，用List来批量接受结果
 *
 * @version 1.0 create at 2020/2/27
 * @auther yangchuan
 */
public class MultiFutures {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(20);

        ArrayList<Future> futures = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Future<Integer> submit = service.submit(new CallableTask());
            futures.add(submit);
        }
        futures.forEach(e->{
            try {
                System.out.println(e.get());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (ExecutionException ex) {
                ex.printStackTrace();
            }
        });
    }

    static class CallableTask implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            Thread.sleep(3000);
            return new Random().nextInt(100);
        }
    }
}
