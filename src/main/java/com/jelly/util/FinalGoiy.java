package com.jelly.util;

/**
 * @author dongxiaohong
 * @date 2019/5/20 14:07
 */
final public class FinalGoiy {

    /**
     * 基本类型只有在作为类属性时才能被系统初始化为0.
     * 而作为入参或者局部变量是不能被初始化为0的.为什么?这个跟Java的wrap/unwrap有关.
     * */
    int k;
    public static void main(String[] args) {
        int a=0;
        Integer aa=null;
        int b = 13;

        /**
         * 实际上传递的是Integer类型所以a要初始化,只是没办法初始化为null
         * */
        int add = new FinalGoiy().add(a, b);
        add = new FinalGoiy().add(aa==null?0:aa, b);
        System.out.println(new FinalGoiy().k);
        System.out.println(add);
    }

    public int add(int a,int b){
        return a+b;
    }
}
