package com.jelly.util.jvm;

/**
 * @author dongxiaohong
 * @date 2019/5/9 16:43
 */
public class JvmHash {

    /**
     * 计算String的hash值
     * */
    public static void main(String[] args) {
        Integer h=0;
        String s = "Hello";
        int len = s.length();
        char[] chars = s.toCharArray();
        for (int i=0;i<len;i++){
            h = 31*h + chars[i];
        }
        System.out.println(h);
        System.out.println(s.hashCode());
        System.out.println(pow(32,2));
    }

    public static int pow(int a,int b){
        int sum = 1;
        while (b-->0){
            sum*=a;
        }
        return sum;
    }
}
