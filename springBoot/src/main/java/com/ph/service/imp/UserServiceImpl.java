package com.ph.service.imp;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ph.pojo.User;
import com.ph.service.UserService;
import com.ph.mapper.UserMapper;
import com.ph.utils.JwtHelper;
import com.ph.utils.MD5Util;
import com.ph.utils.Result;
import com.ph.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @author BCQ
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2023-10-16 20:33:42
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtHelper jwtHelper;

    //登录
    @Override
    public Result login(User user) {
        //根据账号查询数据
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername,user.getUsername());
        User loginUser = userMapper.selectOne(lambdaQueryWrapper);

        if(loginUser==null){
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }
        //对比密码
        if(!StringUtils.isEmpty(user.getUserPwd())
                && MD5Util.encrypt(user.getUserPwd()).equals(loginUser.getUserPwd())){
            //登录成功
            //根据用户id生成token
            String token = jwtHelper.createToken(Long.valueOf(loginUser.getUid()));
            // 将token封装到result返回
            Map data = new HashMap();
            data.put("token",token);

            return Result.ok(data);

        }else {

        }
        return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
    }

    //根据token获取用户数据
    @Override
    public Result getUserInfo(String token) {
        //1.token是否在有效期 true:过期
        boolean expiration = jwtHelper.isExpiration(token);
        if(expiration){
            //失效未登录
            return Result.build(null,ResultCodeEnum.NOTLOGIN);
        }
        //2.根据token解析UserId
        int userId= jwtHelper.getUserId(token).intValue();
        //3.根据用户id查询数据
        User user = userMapper.selectById(userId);
        //4.去掉密码，封装result结果返回即可
        user.setUserPwd("");

        Map data = new HashMap();
        data.put("loginUser",user);

        return Result.ok(data);
    }

    //检查账号是否可用
    @Override
    public Result checkUserName(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,username);

        // 根据 Wrapper 条件，查询总记录数   Integer selectCount(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
        Long count = userMapper.selectCount(queryWrapper);

        if(count==0){
            return Result.ok(null);
        }
           return Result.build(null,ResultCodeEnum.USERNAME_USED);
    }

    //用户注册
    @Override
    public Result register(User user) {
        //1.依然检查账号是否已经被注册
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,user.getUsername());

            // 根据 Wrapper 条件，查询总记录数   Integer selectCount(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
            Long count = userMapper.selectCount(queryWrapper);

        if(count>0){
            return Result.build(null,ResultCodeEnum.USERNAME_USED);
        }
        //2.密码加密
        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));

        //3.插入数据库
         userMapper.insert(user);


        return Result.ok(null);
    }
}




