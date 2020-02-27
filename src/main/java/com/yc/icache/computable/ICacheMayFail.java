package com.yc.icache.computable;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 利用Future，避免重复计算
 *
 * @version 1.0 create at 2020/2/27
 * @auther yangchuan
 */
public class ICacheMayFail<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();

    private final Computable<A, V> c;

    public ICacheMayFail(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws ExecutionException, InterruptedException {
        while (true){
            Future<V> f = cache.get(arg);
            if (f == null) {
                Callable<V> callable = new Callable<V>() {
                    @Override
                    public V call() throws Exception {
                        return c.compute(arg);
                    }
                };
                FutureTask<V> ft = new FutureTask<>(callable);
                //如果不存在的话，放进去，返回之前的值
                f = cache.putIfAbsent(arg, ft);
                if (f == null) {
                    f = ft;
                    System.out.println("从FutureTask调用了计算函数");
                    ft.run();
                }
                ft.run();
            }
            try {
                return f.get();
            } catch (CancellationException e){
                System.out.println("被取消了");
                cache.remove(arg);
                throw e;
            }catch (InterruptedException e) {
                cache.remove(arg);
                throw e;
            } catch (ExecutionException e) {
                cache.remove(arg);
                System.out.println("计算错误，需要重试");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ICacheMayFail<String, Integer> expensiveComputer = new ICacheMayFail<>(new MayFail());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Integer result = expensiveComputer.compute("666");
                    System.out.println("第一次计算结果：" + result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Integer result = expensiveComputer.compute("667");
                    System.out.println("第二次计算结果：" + result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Integer result = expensiveComputer.compute("666");
                    System.out.println("第三次计算结果：" + result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
//        Integer result = expensiveComputer.compute("666");
//        System.out.println("第一次计算结果："+result);
//        result = expensiveComputer.compute("666");
//        System.out.println("第二次计算结果："+result);
        Thread.sleep(100);
        Future<Integer> future = expensiveComputer.cache.get("666");
        future.cancel(true);
    }
}
