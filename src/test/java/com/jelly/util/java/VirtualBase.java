package com.jelly.util.java;

/**
 * @author dongxiaohong
 * @date 2019/2/14 14:17
 */
public class VirtualBase {

    /**
     * 验证C++虚函数=Java普通函数
     * 也就是运行阶段动态绑定
     * 也就是说print是一个虚函数,它与具体实例的绑定关系发生在运行阶段
     * */
    public void print(){
        System.out.println("动态绑定");
    }

    public static void main(String[] args) {
        VirtualBase base = new VirtualBase();
        base.print();
    }
}
