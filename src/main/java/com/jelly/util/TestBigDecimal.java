package com.jelly.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dongxiaohong
 * @date 2019/4/18 14:17
 */
public class TestBigDecimal {
    public static void main(String[] args) {
        Map<String,Object> paramMap = new HashMap<>();
        BigDecimal amount = BigDecimal.ZERO;
        paramMap.put("amount",amount);
        System.out.println(paramMap.get("amount"));
    }
}
