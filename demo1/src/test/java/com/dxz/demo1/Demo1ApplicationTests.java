package com.dxz.demo1;

import com.dxz.demo1.entry.User;
import com.dxz.demo1.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class Demo1ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    UserServiceImpl userService;
    @Test
    void testAddUser(){
        User user = new User();
        user.setUsername("dxz");
        user.setUserAccount("123");
        user.setAvatar("https://fmdxz.love/attach/20221230/1f1e995760f74583b37e0c8fda2bbaa8.jpg");
        user.setGender(1);
        user.setUserPassword("123");
        user.setPhone("123");
        user.setEmail("123");
        boolean save = userService.save(user);
        System.out.println(save);
        Assertions.assertTrue(save);
    }


}
