package com.jelly.util.intern;

import com.jelly.util.base.UrlAsk;
import com.jelly.util.watch.TimeWatch;
import net.sf.json.JSONObject;

import javax.swing.*;

/**
 * @author dongxiaohong
 * @date 2019/6/10 15:47
 */
public class StringIntern {
    public static void main(String[] args) throws Exception {
        String s = new String("1");
        String s2 = "1";
        /*s的引用会保存在常量池中*/
        s.intern();
        System.out.println(s == s2);

        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";

        System.out.println(s3 == s4);
        ClassLoader objC = String.class.getClassLoader();
        try {
            Class clazz = Class.forName("java.lang.String");
            objC = clazz.getClassLoader();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Object o =new Object();

        JSONObject jsonObject = new JSONObject();
        ClassLoader c = StringIntern.class.getClassLoader();
        ClassLoader c0 = jsonObject.getClass().getClassLoader();
        System.out.println(objC);
        System.out.println(c);
        System.out.println(c0);

        Class clazz = Class.forName("[I");
        System.out.println(clazz.getClassLoader());

        /**
         * 打印出APPClassLoader、ExtendedClassLoader、BootstrapClassLoader
         * */
        ClassLoader appClassLoader = StringIntern.class.getClassLoader();
        ClassLoader extendedClassLoader = appClassLoader.getParent();
        ClassLoader bootstrapClassLoader = extendedClassLoader.getParent();
        System.out.println(appClassLoader);
        System.out.println(extendedClassLoader);
        System.out.println(bootstrapClassLoader);

        System.out.println(sun.launcher.LauncherHelper.class.getClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader());

    }
}
