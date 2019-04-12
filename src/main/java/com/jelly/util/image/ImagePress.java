package com.jelly.util.image;

import com.jelly.util.watch.TimeWatch;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.codec.binary.Base64;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.IllegalFormatCodePointException;

/**
 * @author dongxiaohong
 * @date 2019/4/11 14:05
 */
public class ImagePress {

    private static long Thousand = 1000L;
    private static long Million = 1000000L;
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        String path="/Users/dongxiaohong/Downloads";
        String fileName = "WechatIMG3.jpeg";
        try {
           /* File file = new File(path+File.separator+fileName);
            if (!file.exists()){
                System.out.println("#======图片文件不存在,请检查!!!======#");
                return;
            }
            *//**
             * 原始文件大小
             * *//*
            long srcImgL = fileLogInf(file);
            *//**
             * swing压缩
             * *//*
            for (int i=0;i<10;i++) {
                swingImgPress(file, 1);
            }
            File distFile = new File("/Users/dongxiaohong/Downloads/WechatIMG3.jpeg");
            long distImgL = fileLogInf(distFile);
            Image distImg = ImageIO.read(distFile);
            System.out.println("目的图片宽度:"+distImg.getWidth(null)+",高度:"+distImg.getHeight(null));
            BigDecimal rate = BigDecimal.valueOf(distImgL).divide(BigDecimal.valueOf(srcImgL), 2, RoundingMode.HALF_UP);
            System.out.println("#======压缩比为:"+rate+"======#");
            System.out.println("#======ImageIO压缩执行时长为:"+(System.currentTimeMillis()-startTime)+"======#");
            *//**
             * thumbnailator 压缩,压缩效果不稳定
             * *//*
            long startTime1 = System.currentTimeMillis();*/
            /*Thumbnails.of("/Users/dongxiaohong/Downloads/WechatIMG3.jpeg")
                    .scale(1f)
                    .outputQuality(0.5f)
                    .toFile("/Users/dongxiaohong/Downloads/WechatIMG3_2.jpg");
            File thumbnailsFile = new File("/Users/dongxiaohong/Downloads/WechatIMG3_2.jpg");
            long thumbnailsLen = fileLogInf(thumbnailsFile);
            BigDecimal thumbnailsRate = BigDecimal.valueOf(thumbnailsLen).divide(BigDecimal.valueOf(srcImgL), 2, RoundingMode.HALF_UP);
            System.out.println("#======压缩比为:"+thumbnailsRate+"======#");*/
            //System.out.println("#======Thumbnails压缩执行时长为:"+(System.currentTimeMillis()-startTime1)+"======#");
            /**
             * ImageIO多线程压缩
             * */
            long startTime3 = System.currentTimeMillis();
            /*String distFileSuffix = "/Users/dongxiaohong/Downloads/WechatIMG3_";
            //ExecutorService exe = new ThreadPoolExecutor(2,4,10, TimeUnit.SECONDS,new LinkedBlockingDeque<>(1));
            ThreadPoolTaskExecutor exe = new ThreadPoolTaskExecutor();
            exe.setCorePoolSize(2);
            exe.setKeepAliveSeconds(10);
            exe.setMaxPoolSize(10);
            exe.setQueueCapacity(100);
            exe.setWaitForTasksToCompleteOnShutdown(true);
            exe.initialize();
            for (int i=0;i<10;i++){
                final String distFile = distFileSuffix+i+".jpg";
                exe.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            swingImgPress("/Users/dongxiaohong/Downloads/WechatIMG3.jpeg",1,distFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            while (exe.getActiveCount()!=0) {
            }
            exe.shutdown();*/
            System.out.println("#======ImageIO多线程压缩执行时长为:"+(System.currentTimeMillis()-startTime3)+"======#");


            /**
             * base64压缩
             * */
            /*TimeWatch.start();
            String srcFile = "/Users/dongxiaohong/Downloads/WechatIMG3.jpeg";
            String distFile = "/Users/dongxiaohong/Downloads/WechatIMG3_base64.jpeg";
            base64Press(srcFile,distFile);
            TimeWatch.end();*/
            /**
             * OpenCV压缩
             * */
            //openCVPress(srcFile,distFile);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void openCVPress(String srcFile, String distFile) {
        Mat src = Imgcodecs.imread(srcFile);
        Mat dist = src.clone();
        Imgproc.resize(src,dist,new Size(src.width(),src.height()));
        Imgcodecs.imwrite(distFile,dist);
    }

    private static void swingImgPress(File file,double rate) throws IOException {
        Image srcImg = ImageIO.read(file);
        System.out.println(file.getAbsolutePath());
        System.out.println("源图片宽度:"+srcImg.getWidth(null)+",高度:"+srcImg.getHeight(null));
        int width = (int)(srcImg.getWidth(null)*rate);
        int height = (int)(srcImg.getHeight(null)*rate);
        BufferedImage bfImg = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        bfImg.getGraphics().drawImage(srcImg.getScaledInstance(width,height,Image.SCALE_AREA_AVERAGING),0,0,null);
        FileOutputStream out = new FileOutputStream("/Users/dongxiaohong/Downloads/WechatIMG3.jpeg");
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(bfImg);
        out.close();
    }

    private static void swingImgPress(File file) throws IOException {
        Image srcImg = ImageIO.read(file);
        System.out.println(file.getAbsolutePath());
        System.out.println("源图片宽度:"+srcImg.getWidth(null)+",高度:"+srcImg.getHeight(null));
        int width = srcImg.getWidth(null);
        int height = srcImg.getHeight(null);
        BufferedImage bfImg = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        bfImg.getGraphics().drawImage(srcImg.getScaledInstance(width,height,Image.SCALE_AREA_AVERAGING),0,0,null);
        FileOutputStream out = new FileOutputStream("/Users/dongxiaohong/Downloads/WechatIMG3.jpeg");
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(bfImg);
        out.close();
    }

    public static void swingImgPress(String srcFile,double rate,String distFile) throws IOException {
        Image srcImg = ImageIO.read(new File(srcFile));
        System.out.println("源图片宽度:"+srcImg.getWidth(null)+",高度:"+srcImg.getHeight(null));
        int width = (int)(srcImg.getWidth(null)*rate);
        int height = (int)(srcImg.getHeight(null)*rate);
        BufferedImage bfImg = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        bfImg.getGraphics().drawImage(srcImg.getScaledInstance(width,height,Image.SCALE_AREA_AVERAGING),0,0,null);
        FileOutputStream out = new FileOutputStream(distFile);
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(bfImg);
        out.close();
        srcImg.flush();
    }

    private static long fileLogInf(File file) {
        long l = file.length();
        if (l/Thousand>=1&&l/Million<=0){
            System.out.println("#======文件大小为:" + BigDecimal.valueOf(l*100/Thousand).divide(BigDecimal.valueOf(100),2,RoundingMode.HALF_UP)+"KB");
        }else if (l/Million>0) {
            System.out.println("#======文件大小为:" + BigDecimal.valueOf(l*100 / Million).divide(BigDecimal.valueOf(100),2,RoundingMode.HALF_UP) + "MB");
        }else {
            System.out.println("#======文件大小为:" + l +"B");
        }
        return l;
    }

    /**
     * base64压缩图片
     * */
    public static void base64Press(String srcFileName,String distFileName){
        try {
            File srcFile = new File(srcFileName);
            if (!srcFile.exists()){
                System.out.println("#======文件不存在======#");
                return;
            }
            System.out.println(srcFile.length());
            FileInputStream fis = new FileInputStream(srcFile);
            byte[] cs = new byte[(int)srcFile.length()];
            fis.read(cs,0,(int)srcFile.length());
            /**
             * base64压缩
             * */
            /*BASE64Encoder encoder = new BASE64Encoder();
            String encode = encoder.encode(cs);
            System.out.println(Base64.encodeBase64(cs).length);*/
            BASE64Decoder decoder =new BASE64Decoder();
            byte[] imageBytes = null;
            File imageFile = null;

            imageBytes = decoder.decodeBuffer(srcFileName);
            for (int i=0;i < imageBytes.length;++i){
                if (imageBytes[i]<0){
                    imageBytes[i] += 256;
                }
            }
            imageFile = new File(distFileName);
            if (!imageFile.createNewFile()){
                throw  new IllegalFormatCodePointException(2);
            }
            OutputStream os = new FileOutputStream(imageFile);
            os.write(imageBytes);
            os.flush();
        }catch (Exception e){

        }
    }
}
