package com.jelly.util.image;


import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author dongxiaohong
 * @date 2019/4/17 16:23
 */
public class NioImageReader {
    static String path="/Users/dongxiaohong/Downloads";
    static String fileName = "2018-10-02 161343.jpg";
    public static void main(String[] args) throws IOException {
        /*long t0 = System.currentTimeMillis();
        File file = new File(path+File.separator+fileName);
        BufferedImage srcImg = ImageIO.read(file);
        int width = srcImg.getWidth(null);
        int height = srcImg.getHeight(null);
        BufferedImage bfImg = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        bfImg.getGraphics().drawImage(srcImg.getScaledInstance(width,height, Image.SCALE_AREA_AVERAGING),0,0,null);
        FileOutputStream out = new FileOutputStream("/Users/dongxiaohong/Downloads/WechatIMG3_1.jpg");
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(bfImg);
        out.close();
        System.out.println(System.currentTimeMillis()-t0);*/

        java.util.List<Thread> threadList = new ArrayList<>();
        /**
         * 生成cache directory缓存目录
         * */
        File dir = new File("/Users/dongxiaohong/image/io/cache/");
        if (!dir.exists()){
            dir.mkdirs();
        }
        ImageIO.setCacheDirectory(dir);
        /**开10个线程*/

        for (int i=0;i<20;i++){
            Thread thread = new Thread(new Runnable(){
                @Override
                public void run(){
                    long t1 = System.currentTimeMillis();
                    File file2 = new File(path+File.separator+fileName);
                    BufferedImage srcImg2 = null;
                    try {
                        // 取得图片读入流
                        Iterator imgIter = ImageIO.getImageReadersBySuffix("jpeg");
                        ImageReader reader = (ImageReader) imgIter.next();
                        ImageReadParam readParam = reader.getDefaultReadParam();
                        /*readParam.setSourceBands(new int[]{0,1});
                        readParam.setDestinationBands(new int[]{0,1});*/
                        InputStream source = new FileInputStream(file2);
                        ImageInputStream input = ImageIO.createImageInputStream(source);
                        reader.setInput(input,false);
                        srcImg2 = reader.read(0,readParam);
                        Graphics2D graphics2D = (Graphics2D)srcImg2.getGraphics();
                        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.9f));
                        graphics2D.dispose();
                        ImageIO.write(srcImg2, "jpg", new File("/Users/dongxiaohong/Downloads/2018-10-02 161343"+Thread.currentThread().getName()+".jpeg"));
                        reader.setInput(input);
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(System.currentTimeMillis()-t1);
                }
            });
            threadList.add(thread);
        }
        /**
         * 一起开启线程
         * */
        for (Thread t:threadList){
            t.start();
        }
    }
}
