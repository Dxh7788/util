package com.jelly.util;


/**
 * @author dongxiaohong
 * @date 2019/1/14 14:37
 */
public class ShortParserUtil {

    public static void main(String[] args) {

        if (getPrefixBooleanValue() &&
                getSuffixBooleanValue()){
            System.out.println(">>>执行语句内逻辑...");
        }
    }

    private static Boolean getPrefixBooleanValue(){
        System.out.println("短路语法,前缀语法为false,导致&&后不执行");
        return false;
    }

    private static Boolean getSuffixBooleanValue(){
        System.out.println("短路语法,不执行&&后逻辑");
        return true;
    }
}
