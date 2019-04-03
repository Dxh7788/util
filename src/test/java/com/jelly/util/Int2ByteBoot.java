package com.jelly.util;

/**
 * @author dongxiaohong
 * @date 2019/4/3 10:29
 */
public class Int2ByteBoot {

    public static void main(String[] args) {
        byte[] bs = new byte[4];
        Integer value = 126;
        bs[0]= (byte)(value>>24 &0xff);
        bs[1]= (byte)(value>>16 &0xff);
        bs[2]= (byte)(value>>8 &0xff);
        bs[3]= (byte)(value &0xff);
        System.out.println(bs);
    }
}
