package com.yc.icache.computable;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 出于安全性考虑，缓存需要设置有效期，到期自动失效。
 * 如果缓存一直不失效，会带来缓存不一致的问题
 *
 * @version 1.0 create at 2020/2/27
 * @auther yangchuan
 */
public class ICache1<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();

    private final Computable<A, V> c;

    public ICache1(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws Exception {
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

    public final static ScheduledExecutorService exec =
            Executors.newScheduledThreadPool(5);

    public V compute(A arg, long expire) throws Exception {
        if (expire > 0) {
            exec.schedule(new Runnable() {
                @Override
                public void run() {
                    expire(arg);
                }
            }, expire, TimeUnit.MILLISECONDS);
        }
        return compute(arg);
    }

    public synchronized void expire(A key) {
        Future<V> future = cache.get(key);
        if (future != null) {
            if (!future.isDone()) {
                System.out.println("Future任务被取消");
                future.cancel(true);
            }
            System.out.println("过期时间到，缓存被清除");
            cache.remove(key);
        }
    }

    public V computeRandomExpire(A arg) throws Exception {
        long randomExpire = (long) (Math.random() * 10000);
        return compute(arg, randomExpire);
    }

    public static void main(String[] args) throws Exception {
        ICache1<String, Integer> expensiveComputer = new ICache1<>(new ExpensiveFunction());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Integer result = expensiveComputer.compute("666", 5000);
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
        Thread.sleep(6000);
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
    }
}
