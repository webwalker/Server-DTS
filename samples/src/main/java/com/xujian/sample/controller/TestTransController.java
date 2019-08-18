package com.xujian.sample.controller;

import com.xujian.sample.model.User;
import com.xujian.sample.service.ManualUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xujian on 2019-08-18
 */
@RestController
public class TestTransController {
    @Autowired
    private ManualUserService service;

    @RequestMapping("/testTrans1")
    public void test1() {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setName("x1");
        user1.setAge(21);

        User user2 = new User();
        user2.setName("x2");
        user2.setAge(20);

        users.add(user1);
        users.add(user2);

        service.addUsers0(users);
    }

    @RequestMapping("/testTrans2")
    public void test2() {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setName("s1");
        user1.setAge(21);

        User user2 = new User();
        user2.setName("s2");
        user2.setAge(20);

        users.add(user1);
        users.add(user2);

        service.addUsers1(users);
    }

    @RequestMapping("/testTrans3")
    public void test3() {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setName("y1");
        user1.setAge(21);

        User user2 = new User();
        user2.setName("y2");
        user2.setAge(20);

        users.add(user1);
        users.add(user2);

        service.addUsers2(users);
    }
}
