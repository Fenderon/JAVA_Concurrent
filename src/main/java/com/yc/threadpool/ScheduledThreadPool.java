package com.yc.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Description TODO
 *
 * @version 1.0 create at 2020/2/4
 * @auther yangchuan
 */
public class ScheduledThreadPool {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(10);

        scheduledExecutorService.schedule(new Task(),5, TimeUnit.SECONDS);

        scheduledExecutorService.scheduleAtFixedRate(new Task(),1,3,TimeUnit.SECONDS);
    }
}
