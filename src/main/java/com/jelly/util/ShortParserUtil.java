package com.jelly.util;


import java.util.Scanner;

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

        if (getLoopReturnValue()){
            System.out.println("保证成功获取");
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

    private static Boolean getLoopReturnValue(){
        //使用无限循环保证返回值一定为true
        Boolean exit = false;
        for (;;){
            Scanner scanner = new Scanner(System.in);
            int a = scanner.nextInt();
            if (a == 0){
                return exit;
            }
            exit = true;
        }
    }
}
