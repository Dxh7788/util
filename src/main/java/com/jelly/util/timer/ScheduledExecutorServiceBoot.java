package com.jelly.util.timer;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author dongxiaohong
 * @date 2019/1/28 15:19
 */
public class ScheduledExecutorServiceBoot {

    private ScheduledExecutorService scheduledExecutorService =new ScheduledThreadPoolExecutor(5);
    /*public static void main(String[] args) {
        ScheduledExecutorServiceBoot boot = new ScheduledExecutorServiceBoot();
        *//**单次执行*//*
        boot.scheduledExecutorService.schedule(()->{
                System.out.println("######ScheduledExecutorService 执行######");
        },0, TimeUnit.SECONDS);
        *//**周期性执行*//*
        boot.scheduledExecutorService.scheduleAtFixedRate(()->{
            System.out.println("######周期性执行######");
        },0,5,TimeUnit.SECONDS);

        *//***//*
        boot.scheduledExecutorService.scheduleAtFixedRate(()->{
            System.out.println("######周期性执行######");
        },0,5,TimeUnit.SECONDS);
    }*/
}
