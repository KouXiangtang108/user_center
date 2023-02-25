package com.dxz.demo1.service;

import com.dxz.demo1.entry.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
* @author Administrator
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2023-02-20 11:31:27
*/
public interface UserService extends IService<User> {



    /**
     * @Author fmdxz
     * @Description 用户注册
     * @Date 2023/2/20 14:12
     **/
    long userRegister(String userAccount,String userPassword,String checkPassword);

    /**
     * @Author fmdxz
     * @Description 用户登录
     * @Date 2023/2/20 22:22
     **/
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * @Author fmdxz
     * @Description 用户脱敏
     * @Date 2023/2/25 16:53
     **/
    User getSafetyUser(User user);
}
