package com.yc.deadLock;

/**
 * 必定发生死锁的情况
 *
 * @version 1.0 create at 2020/1/31
 * @auther yangchuan
 */
public class MustDeadLock implements Runnable {

    int flag = 1;

    static Object o1 = new Object();
    static Object o2 = new Object();

    public static void main(String[] args) {
        MustDeadLock r1 = new MustDeadLock();
        MustDeadLock r2 = new MustDeadLock();
        r1.flag = 1;
        r2.flag = 0;
        new Thread(r1).start();
        new Thread(r2).start();

    }

    @Override
    public void run() {
        System.out.println("flag = " + flag);

        if (flag == 1) {
            synchronized (o1){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2){
                    System.out.println("线程1成功拿到锁");
                }
            }
        }

        if (flag == 0) {
            synchronized (o2){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1){
                    System.out.println("线程2成功拿到锁");
                }
            }
        }
    }
}
