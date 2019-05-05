package com.jelly.util.image;

import com.jelly.util.watch.TimeWatch;
import com.sun.media.jai.codec.*;
import com.sun.media.jai.codecimpl.BMPCodec;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dongxiaohong
 * @date 2019/4/24 14:56
 */
public class JAICompress {
    final static String srcFile = "/Users/dongxiaohong/Downloads/2018-10-02161343.jpg";
    final static String distFile = "/Users/dongxiaohong/Downloads/2018-10-021613090090.jpg";
    public static void main(String[] args) throws IOException {

        List<Thread> threads = new ArrayList<>();
        for (int i=0;i<30;i++) {
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeWatch.start();
                        InputStream is = new FileCacheSeekableStream(new FileInputStream(srcFile));
                        RenderedImage image = JAI.create("stream", is);
                        JAI.create("filestore",image,distFile, "JPEG");
                        TimeWatch.end();
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
