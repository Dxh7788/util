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
        /**
         * 关闭流
         * */
        if (is!=null){
            is.close();
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
    public byte getU1Fast(){
        byte[] value = new byte[3];
        System.arraycopy(this.buf,current,value,0,1);
        current+=1;
        if (value.length==1){
            return value[0];
        }else {
            return 0;
        }
    }
    public byte[] getU2Fast(){
        byte[] value = new byte[2];
        System.arraycopy(this.buf,current,value,0,2);
        current+=2;
        return value;
    }
    public byte[] getU4Fast(){
        byte[] value = new byte[4];
        System.arraycopy(this.buf,current,value,0,4);
        current+=4;
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
        /**
         * 获取 cafebabe 魔法值
         * */
        for (int i = 0;i<magic.length;i++){
            int v = magic[i]&0xff;
            String hv = Integer.toHexString(v);
            System.out.print(hv);
        }
        /**
         * 获取大小版本
         * */
        byte[] version = stream.getU4Fast();
        StringBuffer verBuf =new StringBuffer();
        for (int i = 0;i<version.length;i++){
            int v = version[i]&0xff;
            String hv = Integer.toHexString(v);
            verBuf.append(hv);
        }
        System.out.println(verBuf.toString());
        /**
         * 解析常量池
         * 常量池大小
         * */
        byte[] constantPool = stream.getU2Fast();
        int constantPoolSize = ((constantPool[0]&0xff)<<16)|(constantPool[1]&0xff);
        System.out.println("#======常量池大小为:"+constantPoolSize+"======#");
        System.out.println("#======开始解析常量池======#");
        for (int i = 0;i < constantPoolSize ; i++){
            int tag = stream.getU1Fast()&0xff;
            switch (tag){
                case Constant.Tag.CONSTANT_Utf8:
                    break;
                case Constant.Tag.CONSTANT_Unicode:
                    break;
                case Constant.Tag.CONSTANT_Integer:
                    break;
                case Constant.Tag.CONSTANT_Float:
                    break;
                case Constant.Tag.CONSTANT_Long:
                    break;
                case Constant.Tag.CONSTANT_Double:
                    break;
                case Constant.Tag.CONSTANT_Class:
                    break;
                case Constant.Tag.CONSTANT_String:
                    break;
                case Constant.Tag.CONSTANT_FieldRef:
                    break;
                case Constant.Tag.CONSTANT_MethodRef:
                    break;
                case Constant.Tag.CONSTANT_InterfaceMethodRef:
                    break;
                case Constant.Tag.CONSTANT_NameAndType:
                    break;
                case Constant.Tag.CONSTANT_MethodHandle:
                    break;
                case Constant.Tag.CONSTANT_MethodType:
                    break;
                case Constant.Tag.CONSTANT_InvokeDynamic:
                    break;
                default:
                    throw new IllegalAccessError();
            }
        }
    }
}
