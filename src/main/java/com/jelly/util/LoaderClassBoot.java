package com.jelly.util;

/**
 * @author dongxiaohong
 * @date 2019/4/1 16:49
 */
public class LoaderClassBoot {

    public static void main(String[] args) {
        try {
            LoaderClassBoot.class.getClassLoader().loadClass("com.jelly.util.LoaderClassBoot");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
