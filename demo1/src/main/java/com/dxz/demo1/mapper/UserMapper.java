package com.dxz.demo1.mapper;

import com.dxz.demo1.entry.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Administrator
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2023-02-20 11:31:27
* @Entity com.dxz.demo1.entry.User
*/

public interface UserMapper extends BaseMapper<User> {

}




