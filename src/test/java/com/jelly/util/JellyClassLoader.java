package com.jelly.util;

/**
 * @author dongxiaohong
 * @date 2019/2/13 14:54
 */
public class JellyClassLoader extends ClassLoader {

    public static void main(String[] args) {
        System.out.println("自定义ClassLoader");
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }

}
