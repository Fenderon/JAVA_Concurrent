package com.yc.jmm;

/**
 * 演示可见性带来的问题
 *
 * @version 1.0 create at 2020/1/21
 * @auther yangchuan
 */
public class FieldVisibility {

    int a = 1;
    int b = 2;
    int abc = 1;
    int abcd = 1;

    private void change() {
        abc = 7;
        abcd = 70;

        a = 3;
        b = 0;
    }

    public static void main(String[] args) {
        while (true) {
            FieldVisibility test = new FieldVisibility();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.change();
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.print();
                }
            }).start();
        }
    }

    private void print() {
        if (b == 0) {
            System.out.println("b = " + b + ", a = " + a);
        }
    }


}
