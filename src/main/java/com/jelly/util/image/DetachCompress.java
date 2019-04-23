package com.jelly.util.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author dongxiaohong
 * @date 2019/4/19 16:00
 */
public class DetachCompress {

    public static void main(String[] args) {
        int len = args.length;
        System.out.println("长度为:"+len);
        /**
         * 1.第一个参数是原图片位置
         * */
        String originFileName = args[0].replaceAll("<"," ");
        System.out.println("第一个参数为:"+originFileName);
        /**
         * 2.第二个图片是目的图片位置
         * */
        String distFileName = args[1].replaceAll("<"," ");
        System.out.println("第二个参数为:"+distFileName);
        System.out.println("tag");
        try {
            BufferedImage targetImage = null;
            BufferedImage img = ImageIO.read(new File(originFileName));
            System.out.println("tag:"+img.getHeight(null));
            targetImage = new BufferedImage( img.getWidth(null), img.getHeight(null), 1);
            Graphics g = targetImage.getGraphics();
            g.drawImage(img.getScaledInstance(img.getWidth(null), img.getHeight(null), 4), 0, 0, null);
            g.dispose();
            ImageIO.write(targetImage, "jpg", new File(distFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
