package com.jelly.util.base;

import com.jelly.util.model.User;

/**
 * @author dongxiaohong
 * @date 2019/1/16 11:34
 */
public class Jelly {
    private BaseJelly baseJelly;

    public Jelly(BaseJelly baseJelly) {
        this.baseJelly = baseJelly;
    }
    public void start(User user){
        System.out.println(user.getName());
    }
}
