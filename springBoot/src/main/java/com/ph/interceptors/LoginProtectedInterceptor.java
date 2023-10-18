package com.ph.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ph.utils.JwtHelper;
import com.ph.utils.Result;
import com.ph.utils.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.runtime.ObjectMethods;

@Component
public class LoginProtectedInterceptor implements HandlerInterceptor {


    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从请求头中获取token
        String token = request.getHeader("token");
        //检查是否有效
        boolean expiration = jwtHelper.isExpiration(token);
        if (!expiration){
            //放心
            return true;
        }
            //无效返回504
        Result result = Result.build(null, ResultCodeEnum.NOTLOGIN);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(request);
        response.getWriter().println(json);
        return false;
    }
}
