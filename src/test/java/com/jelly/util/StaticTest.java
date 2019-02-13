package com.jelly.util;

/**
 * @author dongxiaohong
 * @date 2019/2/13 15:39
 */
public class StaticTest {
    private StaticTest(){
    }
    private static class LazyHolder{
        static final StaticTest INSTANCE = new StaticTest();
    }
    public static StaticTest getInstance(){
        return LazyHolder.INSTANCE;
    }

    public void print(){
        System.out.println("kl");
    }
    public static void main(String[] args) {
        StaticTest.getInstance().print();
    }
}
