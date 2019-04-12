package com.jelly.util.image.aggress;

import java.awt.image.BufferedImage;

/**
 * @author dongxiaohong
 * @date 2019/4/12 15:32
 */
public abstract class AbstractBufferedImageOp {

    public static final double clo60 = 1.0 / 60.0;
    public static final double clo255 = 1.0 / 255.0;
    public int tr = 0, tg = 0, tb = 0;
    public AbstractBufferedImageOp(){}

    /**
     * 读取像素数据
     * */
    public int[] getRGB(BufferedImage image, int x, int y, int width, int height, int[] pixels){
        int type = image.getType();
        if(type == BufferedImage.TYPE_INT_ARGB || type == BufferedImage.TYPE_INT_RGB){
            return (int[]) image.getRaster().getDataElements(x,y,width,height,pixels);
        }else{
            return image.getRGB(x,y,width,height,pixels,0,width);
        }
    }
    /**
     * 写入像素数据
     * */
    public void setRGB(BufferedImage image,int x, int y, int width, int height, int[] pixels){
        int type = image.getType();
        if(type == BufferedImage.TYPE_INT_ARGB || type == BufferedImage.TYPE_INT_RGB){
            image.getRaster().setDataElements(x,y,width,height,pixels);
        }else {
            image.setRGB(x,y,width,height,pixels,0,width);
        }
    }

    public BufferedImage createCompatibleDestImage(BufferedImage src, BufferedImage dest){
        return new BufferedImage(src.getWidth(),src.getHeight(),BufferedImage.TYPE_INT_BGR);
    }
    public double[] rgb2Hsl(int[] hsl){
        double min,max,dif,sum;
        double f1,f2;
        double h,s,l;//HSL色彩空间的三个分量
        double hsl1[]={0.0,0.0,0.0};
        tr = hsl[0];
        tg = hsl[1];
        tb = hsl[2];
        min = tr;
        if(tg<min)
            min = tg;
        if(tb<min)
            min = tb;
        max = tr;
        f1 = 0.0;
        f2 = tg-tb;
        if(tg>max){
            max = tg;
            f1 = 120.0;
            f2 = tb-tr;
        }
        if(tb >max){
            max = tb;
            f1 = 240.0;
            f2 = tr-tg;
        }
        dif = max-min;
        sum = max+min;
        l =0.5*sum;//亮度仅与图像的最多颜色成分和最少的颜色成分的总量有关。亮度越小，图像越趋于黑色。亮度越高图像越趋于明亮的白色。
        if(dif == 0){//最大值与最小值相同，则表示为灰色，那么s定义为0，h未定义通常也写为0
            h = 0.0;
            s = 0.0;
        } else if(l<127.5){//根据亮度l计算饱和度
            s = 255.0*dif/sum;
        } else{
            s = 255.0*dif/(510.0-sum);
        }
        h = (f1 +60.0*f2)/dif;//计算色调h
        if(h<0.0){
            h+=360.0;
        }
        if(h>360.0){
            h-=360.0;
        }
        hsl1[0] = h;
        hsl1[1] = s;
        hsl1[2] = l;
        return hsl1;
    }
    //HSL色彩空间转换为RGB色彩空间
    public int[] hsl2RGB(double[] hsl){
        double h,s,l;//HSL色彩空间的三个分量
        h = hsl[0];
        s = hsl[1];
        l = hsl[2];
        int[] rgb1={0,0,0};//RGB数组初始化
        double v1,v2,v3,h1;
//HSL 转换为 RGB
        if(s == 0){//表示灰色，R，G，B定义为0
            tr = (int)l;
            tg = (int)l;
            tb = (int)l;
        }else{
            if(l<127.5){
                v2 = clo255 *l*(255+s);
            }else{
                v2 = l+s-clo255*s*l;
            }
            v1 = 2*l-v2;
            v3 = v2-v1;
            h1 = h+120.0;
            if(h1>=360.0)
                h1 -=360.0;
            //计算tr
            if(h1<60){
                tr = (int)(v1 +v3 *h1 *clo60);
            }else if(h1<180.0){
                tr = (int)v2;
            }else if(h1<240.0){
                tr = (int)(v1+v3*(4-h1*clo60));
            }else{
                tr = (int)v1;
            }
            //计算tg
            h1 = h;
            if(h1<60.0){
                tg = (int)(v1 +v3 *h1 *clo60);
            }else if(h1 <180.0){
                tg = (int)v2;
            }else if(h1<240.0){
                tg = (int)(v1+v3*(4-h1*clo60));
            }else {
                tg = (int)v1;
            }
            //计算tb
            h1 = h-120.0;
            if(h1 <0.0){
                h1 += 360.0;
            }
            if(h1<60.0){
                tb = (int)(v1 +v3* h1 *clo60);
            }else if(h1<180.0){
                tb = (int)v2;
            }else if(h1<240.0){
                tb = (int)(v1+v3*(4-h1*clo60));
            }else{
                tb = (int)v1;
            }

        }
        rgb1[0] = tr;
        rgb1[1] = tg;
        rgb1[2] = tb;
        return rgb1;
    }
}
