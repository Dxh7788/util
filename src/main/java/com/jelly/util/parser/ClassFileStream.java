package com.jelly.util.parser;

import java.io.*;

/**
 * @author dongxiaohong
 * @date 2019/4/2 14:49
 */
public class ClassFileStream {
    private static String classSuffix = ".class";
    private int begin;
    private int end;
    private volatile  int current;

    /**
     * 文件名称
     * */
    private String source;
    boolean needVerify;
    private int length;
    /**
     * 保存文件内容的buf
     * */
    private byte[] buf;
    public ClassFileStream(byte[] buf,String source, boolean needVerify, int length) throws Exception {
        if (source==null || source.isEmpty()){
            throw new ClassCastException();
        }
        if (buf==null || buf.length==0){
            throw new IllegalAccessError();
        }
        /**
         * 1.获取当前工程所在的根目录
         * */
        String userDir= System.getProperty("user.dir");
        System.out.println("当前位置为:"+userDir);
        String obsFilePath = userDir+ File.separator+"target/classes"+File.separator+source.replace(".",File.separator)+classSuffix;
        System.out.println("#======绝对文件路径为:"+obsFilePath+",开始加载class文件======#");
        InputStream is = new FileInputStream(obsFilePath);
        if (is!=null){
            int aLen = is.available();
            System.out.println(aLen);
            this.buf = new byte[is.available()];
            int destPos = 0;
            int size;
            while ((size = is.read(buf))!=-1){
                System.arraycopy(buf,0,this.buf,destPos,size);
                destPos += size;
            }
        }
        System.out.println("#======文件内容读取完毕======#");
        this.source = source;
        this.needVerify = needVerify;
        this.length = length;
    }

    public byte[] buf(){
        return buf;
    }

    public int length(){
        return length;
    }

    public int current(){
        return current;
    }

    public void setCurrent(int current){
        this.current = current;
    }

    public boolean needVerify() {
        return needVerify;
    }

    public void setNeedVerify(boolean needVerify) {
        this.needVerify = needVerify;
    }

    /**
     * 获取一个字节/获取2个字节/获取4个字节
     * */
    public byte[] getU1Fast(){
        return null;
    }
    public byte[] getU2Fast(){
        return null;
    }
    public byte[] getU4Fast(){
        byte[] value = new byte[4];
        System.arraycopy(this.buf,0,value,0,4);
        return value;
    }
    /**
     * 跳过一个字节
     * */
    public void skipU1(){

    }
    public void skipU1Fast(){

    }
    public void skipU2(){

    }
    public void skipU2Fast(){

    }
    public void skipU3(){

    }
    public void skipU3Fast(){

    }

    public static void main(String[] args) throws Exception {
        ClassFileStream stream = new ClassFileStream(new byte[24],"com.jelly.util.model.User",false,1024);
        byte[] magic = stream.getU4Fast();
        for (int i = 0;i<magic.length;i++){
            int v = magic[i]&0xff;
            String hv = Integer.toHexString(v);
            System.out.print(hv);
        }
    }
}
