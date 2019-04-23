package com.jelly.util.image;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        StringBuffer cmd =new StringBuffer("java -classpath ")
                .append(classpath)
                .append(" ")
                .append(className)
                .append(" ");
        String originFileName = "/Users/dongxiaohong/Downloads/2018-10-02 161343.jpg";
        originFileName = originFileName.replaceAll(" ","<");
        String distFileName = "/Users/dongxiaohong/Downloads/2018-10-02_161343_de_1.jpg".replaceAll(" ","<");

        cmd.append(originFileName).append(" ").append(distFileName);
        System.out.println(cmd);
        System.out.println("#======开始执行压缩======#");
        Runtime rn =Runtime.getRuntime();
        try {
            Process exec = rn.exec(cmd.toString());
            InputStream stderr = exec.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(stderr));
            String line = null;
            while ((line = br.readLine())!=null){
                System.out.println(line);
            }
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
