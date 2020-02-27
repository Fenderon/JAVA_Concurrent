package com.yc.future;

import java.util.concurrent.*;

/**
 * 演示get的超时方法，需要注意超时的处理，调用
 * future.cancel()，演示cancel传入true和false的区别，代表是否中断执行的任务
 *
 * @version 1.0 create at 2020/2/27
 * @auther yangchuan
 */
public class Timeout {

    private static final Ad DEFAULT_AD = new Ad("无网络时的默认广告");

    private ExecutorService exec = Executors.newFixedThreadPool(1);


    static class Ad{
        String name;

        public Ad(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Ad{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    static class FetchAdTask implements Callable<Ad>{

        @Override
        public Ad call() throws Exception {
            try{
                Thread.sleep(3000);
            }catch (InterruptedException e){
                System.out.println("被中断了...");
                return new Ad("被中断的时候的默认广告");
            }
            return new Ad("旅游订票哪家强？");
        }
    }

    public void printAd(){

        Future<Ad> future = exec.submit(new FetchAdTask());
        Ad ad;
        try {
            ad = future.get(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            ad = new Ad("被中断时的默认广告");
            e.printStackTrace();
        } catch (ExecutionException e) {
            ad = new Ad("执行失败时的默认广告");
        } catch (TimeoutException e) {
            ad = new Ad("被超时的默认广告");
            System.out.println("超时，未获取到广告");
            //false 不会发出中断信号，，true会发出中断信息
            boolean cancel = future.cancel(true);
            System.out.println("cancel的结果："+cancel);
        }
        exec.shutdown();
        System.out.println(ad);
    }

    public static void main(String[] args) {
        Timeout timeout = new Timeout() ;
        timeout.printAd();
    }
}
