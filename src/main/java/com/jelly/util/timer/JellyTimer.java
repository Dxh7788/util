package com.jelly.util.timer;

import org.apache.commons.lang.time.DateFormatUtils;
import java.util.Calendar;
import java.util.Date;

/**
 * 定时器
 * @author dongxiaohong
 * @date 2019/1/28 09:57
 */
public class JellyTimer {
    static DateFormatUtils utils = new DateFormatUtils();
    static Date flagDate;

    static {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,2019);
        calendar.set(Calendar.MONTH,0);
        calendar.set(Calendar.DAY_OF_MONTH,28);
        calendar.set(Calendar.HOUR,10);
        calendar.set(Calendar.MINUTE,14);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        flagDate = calendar.getTime();
        System.out.println(flagDate);
    }

    /**
     * 轮训的方式
     * 1.最简单的方式,最耗资源的方式
     * */
    public static void poll(){
        for (;;){
            Date currentDate = new Date();
//            System.out.println(currentDate);
            if (currentDate.compareTo(flagDate)==0){
                System.out.println("######定时执行######");
                break;
            }
        }
    }

    /**主方法*/
    public static void main(String[] args) {
        poll();
    }
}
