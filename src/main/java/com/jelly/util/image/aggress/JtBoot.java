package com.jelly.util.image.aggress;

import com.jelly.util.image.ImgCompress;
import com.jelly.util.watch.TimeWatch;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author dongxiaohong
 * @date 2019/4/12 15:40
 */
public class JtBoot {
    private static String srcFile = "/Users/dongxiaohong/Downloads/WechatIMG3.jpeg";
    private static String distFileName = "/Users/dongxiaohong/Downloads/WechatIMG3_bsc.jpeg";
    public static void main(String[] args) {
        BscAdjustFilter bscAdjustFilter =new BscAdjustFilter()
                .brightness(0f)
                .contrast(0f)
                .saturation(0f);
        File srcImgFile = new File(srcFile);
        try {
            TimeWatch.start();
            BufferedImage srcImg = ImageIO.read(srcImgFile);
            BufferedImage distImg= null;
            distImg = bscAdjustFilter.filter(srcImg,distImg);
            ImgCompress.swingImgPress(srcImg,distImg,distFileName);
            System.out.println(distImg);
            TimeWatch.end();
        }catch (Exception e){

        }
    }
}
