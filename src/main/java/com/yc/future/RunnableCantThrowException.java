package com.yc.future;

/**
 * 在run方法中无法抛出checked exception
 *
 * @version 1.0 create at 2020/2/18
 * @auther yangchuan
 */
public class RunnableCantThrowException {

    public void ddd() throws Exception{

    }
    public static void main (String[] args) {
        Runnable runnable = ()->{
            throw new Exception();
        }
    }
}
