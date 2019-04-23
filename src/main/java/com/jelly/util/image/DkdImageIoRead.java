package com.jelly.util.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @author dongxiaohong
 * @date 2019/4/22 10:25
 */
public class DkdImageIoRead {
    /**
     * 读锁
     * */

    public static void main(String[] args) {
        final String srcFile = "/Users/dongxiaohong/Downloads/2018-10-02161343.jpg";
        List<Thread> threads = new ArrayList<>();

        for (int i=0;i!=6;i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                try {
                    BufferedImage bufferedImage = ImageIO.read(new File(srcFile));
                    BufferedImage copyBI = new BufferedImage(0,0,0);
                    Raster raster = bufferedImage.getData(new Rectangle(0,0,100,100));
                    Graphics2D graphics =(Graphics2D)bufferedImage.getGraphics();
                    graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.9f));
                    graphics.dispose();
                    ImageIO.write(bufferedImage,"jpg",new File("/Users/dongxiaohong/Downloads/2018-10-02_io161343.jpg"));
                    System.out.println(bufferedImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                }
            });
            threads.add(thread);
        }
        for (Thread t:threads){
            t.start();
        }
    }
}
