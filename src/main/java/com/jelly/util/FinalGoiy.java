package com.jelly.util;

/**
 * @author dongxiaohong
 * @date 2019/5/20 14:07
 */
final public class FinalGoiy {

    public static void main(String[] args) {
        int a = 12;
        int b = 13;

        int add = new FinalGoiy().add(a, b);
        System.out.println(add);
    }

    public int add(int a,int b){
        return a+b;
    }
}
