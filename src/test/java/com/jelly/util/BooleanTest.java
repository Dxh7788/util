package com.jelly.util;

/**
 * @author dongxiaohong
 * @date 2019/2/13 09:55
 */
public class BooleanTest {

    /**
     * boolean会被转换为int,值的范围为0和1.
     * true/false是不能被虚拟机直接使用的.
     * true映射的是1,false映射的是0.
     * if(isEat)会被映射为字节码 ifeq ,
     * 而if (true == isEat)会被映射为 if_icmpne 1,
     * 也就是说不存在boolean的痕迹.
     * java中正无穷和负无穷都有确切的值
     * */
    public static void main(String[] args) {
        boolean isEat =(2==2);
        char i = (char)65536;
        char i2 = (char)0;
        if(isEat){
            System.out.println("吃了");
        }
        if (true == isEat){
            System.out.println("真吃了");
        }
        System.out.println(i);
        System.out.println(i2);
        float v = 1 / -0.0f;
        if (v== Float.NEGATIVE_INFINITY){
            System.out.println("负无穷");
        }
        /**
         * NaN 的特性就是!=始终返回true,其他均返回false
         * */
        System.out.println(+0.0f/-0.0f == Float.NaN);
        System.out.println(+0.0f/-0.0f != Float.NaN);
        System.out.println(1 != Float.NaN);
        System.out.println(0 != Float.NaN);
        System.out.println(Float.NaN != Float.NaN);
        /**
         * 应用类加载器
         * */
        System.out.println(BooleanTest.class.getClassLoader());
        /**
         * 扩展类加载器
         * */
        System.out.println(BooleanTest.class.getClassLoader().getParent());
        /**
         * 启动类加载器,null
         * */
        System.out.println(BooleanTest.class.getClassLoader().getParent().getParent());
    }
}
