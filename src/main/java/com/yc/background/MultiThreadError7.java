package com.yc.background;

/**
 * 用工厂模式修复初始化问题
 *
 * @version 1.0 create at 2020/1/21
 * @auther yangchuan
 */
public class MultiThreadError7 {

    private MySource.EventListener eventListener;

    int count;

    private MultiThreadError7(MySource source) {
        eventListener = new MySource.EventListener() {
            @Override
            public void onEvent(MySource.Event e) {
                System.out.println();
                System.out.println("我得到的数字是：" + count);
            }
        };
        for (int i = 0; i < 10000; i++) {
            System.out.print(i);
        }
        count=100;
    }

    public static MultiThreadError7 getInstance(MySource mySource){
        MultiThreadError7 safeListener = new MultiThreadError7(mySource);
        mySource.registerListener(safeListener.eventListener);
        return safeListener;
    }

    public static void main(String[] args) {
       MySource source = new MySource();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                source.eventCome(new MySource.Event() {
                });
            }
        }).start();

        MultiThreadError7 multiThreadsError5
                = MultiThreadError7.getInstance(source);
    }


    static class MySource {
        private EventListener listener;

        void registerListener(EventListener eventListener) {
            this.listener = eventListener;
        }

        void eventCome(Event e) {
            if (listener != null) {
                listener.onEvent(e);
            } else {
                System.out.println("还未初始化完毕");
            }
        }

        interface EventListener {
            void onEvent(Event e);
        }

        interface Event {

        }
    }
}
