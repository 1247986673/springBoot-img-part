package com.ph.service;

import com.ph.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ph.utils.Result;

/**
* @author BCQ
* @description 针对表【news_user】的数据库操作Service
* @createDate 2023-10-16 20:33:42
*/
public interface UserService extends IService<User> {

    //登录
    Result login(User user);

    //根据token获取用户数据
    Result getUserInfo(String token);

    //检查账号是否可用
    Result checkUserName(String username);

    //用户注册
    Result register(User user);
}
