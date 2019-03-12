package com.jelly.util.generic;

/**
 * @author dongxiaohong
 * @date 2019/2/15 14:35
 */
public class GenericExtend implements Base<String> {
    @Override
    public String print() {
        System.out.println("验证桥接方法");
        return "";
    }

    public static void main(String[] args) {
        new GenericExtend().print();
        System.out.println(GenericExtend.class.getClassLoader());
    }

}
