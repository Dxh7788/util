package com.jelly.util;

import com.jelly.util.model.Food;

/**
 * @author dongxiaohong
 * @date 2019/1/16 11:42
 */
public class JellyClosure {


    public static void main(String[] args) {
        Food food =new Food();
        food.getEat().eat();
        food.getEat().eat();
    }
}
