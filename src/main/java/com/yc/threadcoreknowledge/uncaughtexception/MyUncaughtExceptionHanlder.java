package com.yc.threadcoreknowledge.uncaughtexception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 自己的MyUncaughtExceptionHanlder
 *
 * @version 1.0 create at 2020/1/19
 * @auther yangchuan
 */
public class MyUncaughtExceptionHanlder implements Thread.UncaughtExceptionHandler {

    private String name;

    public MyUncaughtExceptionHanlder(String name) {
        this.name = name;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.WARNING,
                "线程异常，终止啦" + t.getName(), e);

        System.out.println(name + "捕获了异常" + t.getName() + "异常" + e);
    }
}
