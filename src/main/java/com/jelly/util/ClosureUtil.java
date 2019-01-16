package com.jelly.util;

import com.jelly.util.base.Jelly;
import com.jelly.util.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 闭包测试
 * @author dongxiaohong
 * @date 2019/1/16 11:24
 */
public class ClosureUtil {

    public static void main(String[] args) {
        List<String> ids = new ArrayList<>();
        ids.add("123");
        ids.add("345");
        ids.forEach(id->{
            System.out.println(id);
        });
        ids.forEach(System.out::println);

        new Thread(()->{
            System.out.println("running method is executing");
        }).start();

        List<User> users = new ArrayList<>();
        User user = new User();
        user.setName("ddd");
        users.add(user);

        users.forEach(u->{
            new Jelly(bj->{
            }).start(u);
        });
    }
}
