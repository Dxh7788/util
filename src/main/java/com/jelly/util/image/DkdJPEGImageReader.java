package com.jelly.util.image;

import com.sun.imageio.plugins.jpeg.JPEGImageReader;

import javax.imageio.ImageReadParam;
import javax.imageio.spi.ImageReaderSpi;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author dongxiaohong
 * @date 2019/4/18 11:06
 */
public class DkdJPEGImageReader extends JPEGImageReader {
    private BufferedImage image = null;
    public DkdJPEGImageReader(ImageReaderSpi originator) {
        super(originator);
    }

    @Override
    public BufferedImage read(int imageIndex, ImageReadParam param) throws IOException {
        setThreadLock();
        try {
            Iterator imageTypes = this.getImageTypes(0);
            image = getDestination(param, imageTypes, getWidth(0), getHeight(0));
            return image;
        } catch (IOException e){
            clearThreadLock();
        }finally {
            clearThreadLock();
        }
        return null;
    }
    private Thread theThread = null;
    private int theLockCount = 0;

    private synchronized void setThreadLock() {
        Thread currThread = Thread.currentThread();
        if (theThread != null) {
            if (theThread != currThread) {
                // it looks like that this reader instance is used
                // by multiple threads.
                throw new IllegalStateException("Attempt to use instance of " +
                        this + " locked on thread " +
                        theThread + " from thread " +
                        currThread);
            } else {
                theLockCount ++;
            }
        } else {
            theThread = currThread;
            theLockCount = 1;
        }
    }
    private synchronized void clearThreadLock() {
        Thread currThread = Thread.currentThread();
        if (theThread == null || theThread != currThread) {
            throw new IllegalStateException("Attempt to clear thread lock " +
                    " form wrong thread." +
                    " Locked thread: " + theThread +
                    "; current thread: " + currThread);
        }
        theLockCount --;
        if (theLockCount == 0) {
            theThread = null;
        }
    }
    private CallBackLock cbLock = new CallBackLock();

    private static class CallBackLock {

        private State lockState;

        CallBackLock() {
            lockState = State.Unlocked;
        }

        void check() {
            if (lockState != State.Unlocked) {
                throw new IllegalStateException("Access to the reader is not allowed");
            }
        }

        private void lock() {
            lockState = State.Locked;
        }

        private void unlock() {
            lockState = State.Unlocked;
        }

        private enum State {
            Unlocked,
            Locked
        }
    }
}
