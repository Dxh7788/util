package com.jelly.util.image;

import com.sun.imageio.plugins.jpeg.JPEGImageReaderSpi;

import javax.imageio.IIOException;
import javax.imageio.ImageReader;

/**
 * @author dongxiaohong
 * @date 2019/4/18 19:09
 */
public class DkdJPEGImageReaderSpi extends JPEGImageReaderSpi {

    @Override
    public ImageReader createReaderInstance(Object extension) throws IIOException {
        return new DkdJPEGImageReader(this);
    }
}
