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
        System.out.println(len);
        /**
         * 1.第一个参数是原图片位置
         * */
        String originFileName = args[0];
        System.out.println(originFileName);
        /**
         * 2.第二个图片是目的图片位置
         * */
        String distFileName = args[1];
        System.out.println(distFileName);
        try {
            BufferedImage targetImage = null;
            BufferedImage img = ImageIO.read(new File(originFileName));

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
