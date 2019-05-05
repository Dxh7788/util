package com.jelly.util.image;

import com.sun.media.jai.codec.FileSeekableStream;

import javax.media.jai.Interpolation;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import javax.media.jai.widget.ScrollingImagePanel;
import java.awt.*;
import java.awt.image.renderable.ParameterBlock;
import java.io.IOException;

/**
 * @author dongxiaohong
 * @date 2019/4/25 09:53
 */
public class JAISampleProgram {
    final static String srcFile = "/Users/dongxiaohong/Downloads/2018-10-02161343.jpg";
    public static void main(String[] args) {
        FileSeekableStream stream = null;
        try {
            stream = new FileSeekableStream(srcFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

        RenderedOp image1 = JAI.create("stream", stream);
        Interpolation interp = Interpolation.getInstance(
                Interpolation.INTERP_BILINEAR);
        ParameterBlock params = new ParameterBlock();
        params.addSource(image1);
        params.add(2.0F);
        params.add(2.0F);
        params.add(0.0F);
        params.add(0.0F);
        params.add(interp);
        RenderedOp image2 = JAI.create("scale", params);
        int width = image2.getWidth();
        int height = image2.getHeight();
        ScrollingImagePanel panel = new ScrollingImagePanel(
                image2, width, height);
        Frame window = new Frame("JAI Sample Program");
        window.add(panel);
        window.pack();
        window.show();
    }
}
