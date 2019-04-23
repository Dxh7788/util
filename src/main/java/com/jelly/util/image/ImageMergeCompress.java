package com.jelly.util.image;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

/**
 * 合并压缩
 * @author dongxiaohong
 * @date 2019/4/23 14:31
 */
public class ImageMergeCompress {
    final static String srcFile = "/Users/dongxiaohong/Downloads/2018-10-02161343.jpg";
    public static void main(String[] args) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File(srcFile));
        int w = bufferedImage.getWidth(null);
        int h = bufferedImage.getHeight(null);
        int mWidth = w/2;
        int mHeight = h/2;
        /**
         * 一分四份
         * */
        BufferedImage ltBufImg = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        BufferedImage rtBufImg = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        BufferedImage lbBufImg = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        BufferedImage rbBufImg = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        BufferedImage toImg = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        ltBufImg.setData(bufferedImage.getData(new Rectangle(0,0,mWidth,mHeight)));


        Graphics2D ltGraphics = (Graphics2D)ltBufImg.getGraphics();
        ltGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.9f));
        ltGraphics.dispose();
        File file = new File("/Users/dongxiaohong/Downloads/2018-10-02161343lt.jpg");
        ImageIO.write(ltBufImg,"jpg",file);

        rtBufImg.setData(bufferedImage.getData(new Rectangle(mWidth,0,(w-mWidth),mHeight)));
        ImageIO.write(rtBufImg,"jpg",file);
        lbBufImg.setData(bufferedImage.getData(new Rectangle(0,mHeight,mWidth,h-mHeight)));
        ImageIO.write(lbBufImg,"jpg",file);
        rbBufImg.setData(bufferedImage.getData(new Rectangle(mWidth,mHeight,w-mWidth,h-mHeight)));
        ImageIO.write(rbBufImg,"jpg",file);
    }
}
