package com.jelly.util.image;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dongxiaohong
 * @date 2019/4/19 16:40
 */
public class DetachCompressBoot {
    public static void main(String[] args){
        List<Thread> threads = new ArrayList<>();
        for (int i=0;i<30;i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    compress();
                }
            });
            threads.add(thread);
        }
        for (Thread t:threads){
            t.start();
        }
    }

    private static void compress() {
        String suffix = ".class";
        String className = DetachCompress.class.getName();
        String classpath = DetachCompress.class.getResource("/").getPath();
        String classNamePath = className.replace(".","/")+suffix;
        URL is = Thread.currentThread().getContextClassLoader().getResource(classNamePath);
        String cmdPath = is.getFile();
        cmdPath = cmdPath.replaceAll(suffix,"");
        System.out.println(cmdPath);
        StringBuffer cmd =new StringBuffer("java -classpath ")
                .append(classpath)
                .append(" ")
                .append(className)
                .append(" ");
        String originFileName = "/Users/dongxiaohong/Downloads/2018-10-02 161343.jpg";
        String distFileName = "/Users/dongxiaohong/Downloads/2018-10-02_161343_de_1.jpg";

        cmd.append("\"").append(originFileName).append("\"").append(" \"").append(distFileName).append("\"");
        System.out.println(cmd);
        System.out.println("#======开始执行压缩======#");
        Runtime rn =Runtime.getRuntime();
        try {
            Process exec = rn.exec(cmd.toString());
            while (true){
                if (!exec.isAlive()){
                    System.out.println("#======压缩完成======#");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
