package com.yc.threadcoreknowledge.stopThread;

/**
 * run 无法抛出checked Exception
 * 只能用 try/catch
 *
 * @version 1.0 create at 2020/1/18
 * @auther yangchuan
 */
public class RunThrowException {
    public void aViod() throws Exception {
        throw new Exception();
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //只能try/catch
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
