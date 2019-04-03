package com.jelly.util.model;


import com.jelly.util.base.Active;

/**
 * @author dongxiaohong
 * @date 2019/1/16 11:46
 */
public class Food {

    public static final String name = "Food";
    private static int num = 20;

    public Food() {
        System.out.println("delicious food");
    }

    public Active getEat(){
        //内访问上层类的参数
        return new Active() {
            @Override
            public void eat() {
                if (num == 0){
                    System.out.println("吃货,已经吃完了");
                }
                num--;
                System.out.println("吃货,你吃了一份了");
            }
        };
    }

    public void currentNum(){
        System.out.println("还剩:"+num+"份");
    }
}
