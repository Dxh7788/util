package com.jelly.util.image.aggress;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author dongxiaohong
 * @date 2019/4/24 10:17
 */
public class ThumbnailsCompress {

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        String srcFile = "/Users/dongxiaohong/Downloads/2018-10-02161343.jpg";
        String distFile = "/Users/dongxiaohong/Downloads/2018-10-02161343333.jpg";
        /*ImageIO.setUseCache(true);
        ImageIO.setCacheDirectory(new File("/Users/dongxiaohong/seca"));*/
        //ImageInputStream is = new FileImageInputStream(new File(srcFile));
        BufferedImage bufferedImage = ImageIO.read(new File(srcFile));
        //Graphics2D graphics=(Graphics2D)bufferedImage.getGraphics();
        //graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.5f));
        //OutputStream out = new FileOutputStream(distFile);
        //ImageOutputStream output = ImageIO.createImageOutputStream(out);
        ImageIO.write(bufferedImage,"jpg",new File(distFile));
        System.out.println(System.currentTimeMillis()-start);
    }
}
