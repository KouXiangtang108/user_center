package com.dxz.demo1.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dxz.demo1.entry.User;
import com.dxz.demo1.entry.request.UserLoginRequest;
import com.dxz.demo1.entry.request.UserRegisterRequest;
import com.dxz.demo1.service.UserService;

import static com.dxz.demo1.constant.UserConstant.ADMIN_ROLE;
import static com.dxz.demo1.constant.UserConstant.LOGIN_STATE;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @Auther: fmdxz
 * @Version: 1.0
 * @Classname UserController
 * @Date 2023/2/21 10:18
 * @Created by Administrator
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;

    /**
     * @Author fmdxz
     * @Description 用户注册
     * @Date 2023/2/22 9:22
     **/

    @PostMapping("/register")
    public Long userRegister(@RequestBody UserRegisterRequest userRegisterRequest){
        if(userRegisterRequest == null){
            return null;
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if(StringUtils.isAnyBlank(userAccount,userPassword,checkPassword)){
            return null;
        }
        return userService.userRegister(userAccount, userPassword, checkPassword);
    }

    /**
     * @Author fmdxz
     * @Description 用户登录
     * @Date 2023/2/22 9:22
     **/
    @PostMapping("login")
    public User userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request){
        if(userLoginRequest == null){
            return null;
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if(StringUtils.isAnyBlank(userAccount,userPassword)){
            return null;
        }
        return userService.userLogin(userAccount,userPassword,request);
    }

    /**
     * @Author fmdxz
     * @Description 用户查询
     * @Date 2023/2/22 9:22
     **/

    @GetMapping("/search")
    public List<User> searchUsers(String username,HttpServletRequest request){
        if(!isAdmin(request)){
            return new ArrayList<>();
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(username)){
            wrapper.like("username",username);
        }
        List<User> list = userService.list(wrapper);
        return list.stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList());
    }

    /**
     * @Author fmdxz
     * @Description 用户删除
     * @Date 2023/2/22 9:22
     **/
    @PostMapping("/delete")
    public boolean deleteUser(@RequestBody long id,HttpServletRequest request){
        if(!isAdmin(request)){
            return false;
        }
        if(id < 0){
            return false;
        }
        return userService.removeById(id);
    }

    /**
     * @Author fmdxz
     * @Description 检查是否为管理员
     * @Date 2023/2/22 9:51
     **/
    private boolean isAdmin(HttpServletRequest request){
        Object userObj = request.getSession().getAttribute(LOGIN_STATE);
        User user = (User)userObj;
        return user != null && user.getRole() == ADMIN_ROLE;
    }

}
