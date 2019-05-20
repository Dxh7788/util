package com.jelly.util.jvm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
        String nul = null;
        String s = "I'm a good boy!!!";
        int len = s.length();
        char[] chars = s.toCharArray();
        for (int i=0;i<len;i++){
            h = 31*h + chars[i];
        }
        JvmHash jvmHash = new JvmHash();
        System.out.println(h);
        System.out.println(s.hashCode());
        System.out.println(Integer.toHexString(jvmHash.hashCode()));
        System.out.println(jvmHash.toString());

        List<Integer> ls = new ArrayList<>();
        ls.add(1);
        //ls.remove(1);
        System.out.println(ls);
    }

    public static int pow(int a,int b){
        int sum = 1;
        while (b-->0){
            sum*=a;
        }
        return sum;
    }
}
