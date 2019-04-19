package com.jelly.util.image;

import sun.awt.image.ToolkitImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author dongxiaohong
 * @date 2019/4/19 14:03
 */
public class DkdToolKitImage {
    public static void main(String[] args) {
        java.util.List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    String filename = "/Users/dongxiaohong/Downloads/2018-10-02 161343.jpg";
                    ToolkitImage image = (ToolkitImage) Toolkit.getDefaultToolkit().getImage(filename);
                    System.out.println(image.getWidth());
                    BufferedImage newImage = toBufferedImage(image);
                    try {
                        ImageIO.write(newImage, "jpg", new File("/Users/dongxiaohong/Downloads/2018-10-02 161343" + Thread.currentThread().getName() + ".jpeg"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(image);
                }
            });
            threadList.add(thread);
        }
        /**
         * 一起开启线程
         * */
        for (Thread t : threadList) {
            t.start();
        }
    }

    public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }

        // This code ensures that all the pixels in the image are loaded
        image = new ImageIcon(image).getImage();

        // Determine if the image has transparent pixels; for this method's
        // implementation, see e661 Determining If an Image Has Transparent Pixels
        //boolean hasAlpha = hasAlpha(image);

        // Create a buffered image with a format that's compatible with the screen
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            // Determine the type of transparency of the new buffered image
            int transparency = Transparency.OPAQUE;
	       /* if (hasAlpha) {
	         transparency = Transparency.BITMASK;
	         }*/

            // Create the buffered image
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(
                    image.getWidth(null), image.getHeight(null), transparency);
        } catch (HeadlessException e) {
            // The system does not have a screen
        }

        if (bimage == null) {
            // Create a buffered image using the default color model
            int type = BufferedImage.TYPE_INT_RGB;
            //int type = BufferedImage.TYPE_3BYTE_BGR;//by wang
	        /*if (hasAlpha) {
	         type = BufferedImage.TYPE_INT_ARGB;
	         }*/
            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        }

        // Copy image to buffered image
        Graphics g = bimage.createGraphics();

        // Paint the image onto the buffered image
        g.drawImage(image, 0, 0, null);
        g.dispose();

        return bimage;

    }
}
