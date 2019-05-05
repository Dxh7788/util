package com.jelly.util.jai;

import com.jelly.util.watch.TimeWatch;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.JPEGEncodeParam;

import javax.media.jai.ImageLayout;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import java.awt.*;
import java.awt.image.renderable.ParameterBlock;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author dongxiaohong
 * @date 2019/4/25 10:56
 */
public class JPEGWriterTest {
    final static String srcFile = "/Users/dongxiaohong/Downloads/2018-10-02161343.jpg";
    final static String distFile = "/Users/dongxiaohong/Downloads/2018-10-021613090090.jpg";
    final static String distFilePath = "/Users/dongxiaohong/Downloads/";
    private ImageEncoder encoder = null;
    private JPEGEncodeParam encodeParam = null;

    // Create some Quantization tables.
    private static int[] qtable1 = {
            1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1
    };
    private static int[] qtable2 = {
            2,2,2,2,2,2,2,2,
            2,2,2,2,2,2,2,2,
            2,2,2,2,2,2,2,2,
            2,2,2,2,2,2,2,2,
            2,2,2,2,2,2,2,2,
            2,2,2,2,2,2,2,2,
            2,2,2,2,2,2,2,2,
            2,2,2,2,2,2,2,2
    };
    private static int[] qtable3 = {
            3,3,3,3,3,3,3,3,
            3,3,3,3,3,3,3,3,
            3,3,3,3,3,3,3,3,
            3,3,3,3,3,3,3,3,
            3,3,3,3,3,3,3,3,
            3,3,3,3,3,3,3,3,
            3,3,3,3,3,3,3,3,
            3,3,3,3,3,3,3,3
    };
    // Really rotten quality Q Table
    private static int[] qtable4 = {
            200,200,200,200,200,200,200,200,
            200,200,200,200,200,200,200,200,
            200,200,200,200,200,200,200,200,
            200,200,200,200,200,200,200,200,
            200,200,200,200,200,200,200,200,
            200,200,200,200,200,200,200,200,
            200,200,200,200,200,200,200,200,
            200,200,200,200,200,200,200,200
    };
    public static void main(String args[]) {
        JPEGWriterTest jtest = new JPEGWriterTest(args);
    }
    private PlanarImage loadImage(String imageName) {
        ImageLayout layout = new ImageLayout();
        ParameterBlock pb = (new ParameterBlock()).add(imageName);
        RenderingHints hints = new RenderingHints(JAI.KEY_IMAGE_LAYOUT, layout);
        PlanarImage src = JAI.create("fileload", pb,hints);
        if (src == null) {
            System.out.println("Error in loading image " + imageName);
            System.exit(1);
        }
        return src;
    }
    // Create the image encoder.
    private void encodeImage(PlanarImage img, FileOutputStream out)
    {
        encoder = ImageCodec.createImageEncoder("JPEG", out,
                encodeParam);
        try {
            encoder.encode(img);
            out.close();
        } catch (IOException e) {
            System.out.println("IOException at encoding..");
            System.exit(1);
        }
    }
    private FileOutputStream createOutputStream(String outFile) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(outFile);
        } catch(IOException e) {
            System.out.println("IOException.");
            System.exit(1);
        }
        return out;
    }
    public JPEGWriterTest(String args[]) {
        // Set parameters from command line arguments.
        TimeWatch.start();
        String inFile = srcFile;
        FileOutputStream out1 = createOutputStream(distFilePath+"out1.jpg");
        FileOutputStream out2 = createOutputStream(distFilePath+"out2.jpg");
        FileOutputStream out3 = createOutputStream(distFilePath+"out3.jpg");
        PlanarImage src = loadImage(inFile);
        TimeWatch.end();
        double[] constants = new double[3];
        constants[0] = 0.0;
        constants[1] = 0.0;
        constants[2] = 0.0;
        ParameterBlock pb = new ParameterBlock();
        pb.addSource(src);
        pb.add(constants);

        // Create a new src image with weird tile sizes
        ImageLayout layout = new ImageLayout();
        layout.setTileWidth(57);
        layout.setTileHeight(57);
        RenderingHints hints = new RenderingHints(JAI.KEY_IMAGE_LAYOUT, layout);
        PlanarImage src1 = JAI.create("addconst", pb, hints);

        // ----- End src loading ------
        // Set the encoding parameters if necessary.
        encodeParam = new JPEGEncodeParam();
        encodeParam.setQuality(0.4F);
        encodeParam.setHorizontalSubsampling(0, 1);
        encodeParam.setHorizontalSubsampling(1, 2);
        encodeParam.setHorizontalSubsampling(2, 2);
        encodeParam.setVerticalSubsampling(0, 1);
        encodeParam.setVerticalSubsampling(1, 1);
        encodeParam.setVerticalSubsampling(2, 1);
        encodeParam.setRestartInterval(64);
        //encodeParam.setWriteImageOnly(false);
        //encodeParam.setWriteTablesOnly(true);
        //encodeParam.setWriteJFIFHeader(true);
        // Create the encoder.
        TimeWatch.start();
        encodeImage(src, out1);
        //PlanarImage dst1 = loadImage(distFilePath+"out1.jpg");
        //JAI.create("filestore",dst1,"/Users/dongxiaohong/Downloads/2018-10-0216130900901.jpg", "JPEG");
        //   ----- End first encode ---------
        //encodeParam.setLumaQTable(qtable1);
        //encodeParam.setChromaQTable(qtable2);
        //encodeImage(src, out2);
        //PlanarImage dst2 = loadImage(distFilePath+"out2.jpg");
        //JAI.create("filestore",dst2,"/Users/dongxiaohong/Downloads/2018-10-021613090092.jpg", "JPEG");
        TimeWatch.end();
        //   ----- End second encode ---------
        /*encodeParam = new JPEGEncodeParam();
        encodeImage(loadImage("images/BlackCat.tif"), out3);
        PlanarImage dst3 = loadImage("out3.jpg");*/
    }
}
