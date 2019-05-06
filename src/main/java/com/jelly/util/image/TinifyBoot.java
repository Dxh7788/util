package com.jelly.util.image;

import com.jelly.util.watch.TimeWatch;
import com.tinify.Source;
import com.tinify.Tinify;

import java.io.IOException;

/**
 * @author dongxiaohong
 * @date 2019/5/6 11:28
 */
public class TinifyBoot {
    private static String srcFileName="/Users/dongxiaohong/Pictures/2018-10-02161343.jpg";
    private static String distFileName="/Users/dongxiaohong/Pictures/2018-10-02161343_tiny_01.jpg";
    public static void main(String[] args) throws IOException {
        TimeWatch.start();
        Tinify.setKey("qLtE6CKG9Skamd1SHiPSGACkvw7icuU6");
        //Tinify.setProxy("");
        Source source = Tinify.fromFile(srcFileName);

        source.toFile(distFileName);
        TimeWatch.end();
    }
}
