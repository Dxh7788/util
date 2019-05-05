package com.jelly.util.image;

import com.alibaba.simpleimage.ImageRender;
import com.alibaba.simpleimage.ImageWrapper;
import com.alibaba.simpleimage.SimpleImageException;
import com.alibaba.simpleimage.render.ReadRender;
import com.alibaba.simpleimage.render.ScaleParameter;
import com.alibaba.simpleimage.render.ScaleRender;
import com.alibaba.simpleimage.render.WriteRender;
import com.alibaba.simpleimage.util.ImageReadHelper;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author dongxiaohong
 * @date 2019/4/23 16:24
 */
public class SimpleImageBoot {
    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();
        File in = new File("/Users/dongxiaohong/Downloads/2018-10-02161343.jpg");
        File out = new File("/Users/dongxiaohong/Downloads/2018-10-021613000.jpg");
        ImageWrapper imageWrapper = ImageReadHelper.read(new FileInputStream(in));
        ScaleParameter scaleParam = new ScaleParameter(imageWrapper.getWidth(), imageWrapper.getHeight());

        FileInputStream inStream = null;
        FileOutputStream outStream = null;
        WriteRender wr = null;
        try {
            inStream = new FileInputStream(in);
            outStream = new FileOutputStream(out);
            ImageRender rr = new ReadRender(inStream);
            ImageRender sr = new ScaleRender(rr, scaleParam);
            wr = new WriteRender(sr, outStream);

            wr.render();                            //触发图像处理
            System.out.println(System.currentTimeMillis()-start);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inStream);         //图片文件输入输出流必须记得关闭
            IOUtils.closeQuietly(outStream);
            if (wr != null) {
                try {
                    wr.dispose();                   //释放simpleImage的内部资源
                } catch (SimpleImageException ignore) {
                    // skip ...
                }
            }
        }
    }
}
