package com.jelly.util.timer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dongxiaohong
 * @date 2019/1/28 16:34
 */
public class ExecutorServiceBoot {

    private ExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    public static void main(String[] args) {

        ExecutorServiceBoot boot = new ExecutorServiceBoot();
    }
}
