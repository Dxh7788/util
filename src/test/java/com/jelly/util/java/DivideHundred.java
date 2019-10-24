package com.jelly.util.java;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author dongxiaohong
 * @date 2019/2/19 14:05
 */
public class DivideHundred {

    public static void main(String[] args) {
        Integer ak = 12300;
        Integer result = (int)(ak * 0.01);
        System.out.println(result);

        System.out.println(BigDecimal.valueOf(10).divide(BigDecimal.valueOf(20), 2, RoundingMode.HALF_DOWN).intValue());
    }
}
