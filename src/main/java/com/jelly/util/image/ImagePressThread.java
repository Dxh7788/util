package com.jelly.util.image;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * 压缩线程
 * @author dongxiaohong
 * @date 2019/4/11 15:39
 */
public class ImagePressThread extends Thread{
    private String srcFile;
    private String distFile;

    public ImagePressThread() {
    }

    public ImagePressThread(String srcFile, String distFile) {
        this.srcFile = srcFile;
        this.distFile = distFile;
    }

    @Override
    public void run() {
        try {
            ImagePress.swingImgPress(this.srcFile,1,this.distFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
