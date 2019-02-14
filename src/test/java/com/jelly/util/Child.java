package com.jelly.util;

import java.lang.reflect.Method;

/**
 * 重载的意思的是带的东西不一样了,实现的东西自然不同.带的东西不一样意思是带的返回值、带的参数列表不一样了,但是车还是那个车,名称都一样.
 * 重写的意思简单点儿,车还是那个车,名称一样.进去的东西一样,要去的目的地也一样.只是实现方式变了,子类觉得父类的方式太土了,我要用一样的东西输出一样的结果,但是方式不同了.这个就叫做重写
 * @author dongxiaohong
 * @date 2019/2/13 18:56
 */
public class Child  extends Parent{

    /**重载父类同名方法*/
    public void print(Object s) {
        System.out.println("Child Object execution");
    }
    public void print(String s) {
        System.out.println("String execution");
    }

    public static void main(String[] args) {
        Child child = new Child();
        child.print(new Object());
        Child.okl("ss");

        Class clz = Child.class;
        Method[] methods = clz.getDeclaredMethods();
        for (Method m:methods){
            System.out.println(m.getName()+":"+"出现");
        }
    }

    /**
     * 藏父类同名方法
     * 隐藏的意思是存在但是子类看不到,只能看到自己的静态类方法
     * 那怎么显示出来呢
     * */
    public static void okl(Object s){
        System.out.println("Child Object deal....");
    }
}
