package com.jelly.util.properties;

import java.util.Properties;
import java.util.Set;

/**
 * @author dongxiaohong
 * @date 2019/3/11 19:16
 */
public class JellyTtProperties {
    public static void main(String[] args) {
        Properties properties = System.getProperties();
        Set<String> names = properties.stringPropertyNames();
        for (String name:names){
            System.out.println("属性key:"+name+",属性值:"+properties.getProperty(name));
        }
        try {

            String flag = "1";
            switch (flag) {
                default:
                    throw new UnsupportedOperationException("111");
            }
        }catch (Exception e){
            System.out.println("捕获异常");
        }
    }
}
