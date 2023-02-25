package com.dxz.demo1.entry.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: fmdxz
 * @Version: 1.0
 * @Classname UserLoginRequest
 * @Date 2023/2/21 10:46
 * @Created by Administrator
 */
@Data
public class UserLoginRequest implements Serializable {

    private String userAccount;

    private String userPassword;
}
