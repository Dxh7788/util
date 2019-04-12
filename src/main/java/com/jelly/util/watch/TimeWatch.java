package com.jelly.util.watch;

/**
 * @author dongxiaohong
 * @date 2019/4/12 11:23
 */
public class TimeWatch {
    private static long startTime;
    private static long endTime;
    public static void start(){
        startTime = System.currentTimeMillis();
    }

    public static void end(){
        endTime = System.currentTimeMillis();
        System.out.println("执行时长为:"+(endTime-startTime));
    }
}
