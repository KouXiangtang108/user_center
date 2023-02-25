package com.dxz.demo1.service;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dxz.demo1.entry.User;

import com.dxz.demo1.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;


import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Auther: fmdxz
 * @Version: 1.0
 * @Classname UserServiceTest
 * @Date 2023/2/20 11:36
 * @Created by Administrator
 */
@SpringBootTest
class UserServiceTest {
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

    @Test
    void md5Test(){
        final String SALT = "dxz";
        String newPassword = DigestUtils.md5DigestAsHex((SALT+"userPassword").getBytes(StandardCharsets.UTF_8));
        System.out.println(newPassword);
    }

    @Test
    void userRegister() {
        String userAccount = "dxzdxz";
        String userPassword = "123123123";
        String checkPassword = "123123123";
        long l = userService.userRegister(userAccount, userPassword, checkPassword);
        System.out.println(l);
    }

    @Test
    void matcherTest(){
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher("@userAccount");
        System.out.println(matcher.find());
    }
}