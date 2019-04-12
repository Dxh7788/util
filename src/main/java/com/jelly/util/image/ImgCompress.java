package com.jelly.util.image;

import com.jelly.util.watch.TimeWatch;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.imageio.plugins.jpeg.JPEGImageWriter;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author dongxiaohong
 * @date 2019/4/12 14:11
 */
public class ImgCompress {
    public static void main(String[] args) throws Exception{
        String srcFile = "/Users/dongxiaohong/Downloads/WechatIMG3.jpeg";
        String distFile = "/Users/dongxiaohong/Downloads/WechatIMG3_jwt.jpeg";
        TimeWatch.start();
//        for (int i=0;i<10;i++) {
            awtImgPress(srcFile, distFile, 1f);
//        }
        TimeWatch.end();
    }

    public static void swingImgPress(String srcFile,String distFile,double rate) throws IOException {
        File srcImgFile = new File(srcFile);
        BufferedImage srcImg = ImageIO.read(srcImgFile);
        System.out.println("源图片宽度:"+srcImg.getWidth(null)+",高度:"+srcImg.getHeight(null));
        int width = (int)(srcImg.getWidth(null)*rate);
        int height = (int)(srcImg.getHeight(null)*rate);
        BufferedImage bfImg = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bfImg.getGraphics();
        graphics.drawImage(srcImg.getScaledInstance(width,height,Image.SCALE_AREA_AVERAGING),0,0,null);
        TimeWatch.end();
        FileOutputStream out = new FileOutputStream(distFile);
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(bfImg);
        out.close();
        srcImg.flush();
        File distImgFile = new File(distFile);
        BigDecimal ratio = BigDecimal.valueOf(distImgFile.length()).divide(BigDecimal.valueOf(srcImgFile.length()),2, RoundingMode.HALF_UP);
        System.out.println("压缩比为:"+ratio);
//        TimeWatch.end();
    }

    public static void swingImgPress(BufferedImage srcImg,BufferedImage distImg,String distFile) throws IOException {
        Graphics graphics = distImg.getGraphics();
        int width = srcImg.getWidth(null);
        int height = srcImg.getHeight(null);
        graphics.drawImage(srcImg.getScaledInstance(width,height,Image.SCALE_AREA_AVERAGING),0,0,null);
        TimeWatch.end();
        FileOutputStream out = new FileOutputStream(distFile);
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(distImg);
        out.close();
        srcImg.flush();
    }

    public static void awtImgPress(String srcFile,String distFile,float quality) throws IOException {
        File srcImgFile = new File(srcFile);
        BufferedImage srcImg = ImageIO.read(srcImgFile);
        FileOutputStream out = new FileOutputStream(distFile);
        JPEGImageWriter imageWriter = (JPEGImageWriter) ImageIO.getImageWritersBySuffix("jpg").next();
        ImageOutputStream ios = ImageIO.createImageOutputStream(out);
        imageWriter.setOutput(ios);
        IIOMetadata imageMetaData = imageWriter.getDefaultImageMetadata(new ImageTypeSpecifier(srcImg), null);
        if (quality >= 0 && quality <= 1f) {
            JPEGImageWriteParam jpegParams = (JPEGImageWriteParam) imageWriter
                    .getDefaultWriteParam();
            jpegParams.setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT);
            jpegParams.setCompressionQuality(quality);
        }
        imageWriter.write(imageMetaData, new IIOImage(srcImg, null, null), null);
        ios.close();
        imageWriter.dispose();
        out.close();
    }
}
