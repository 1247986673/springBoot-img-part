package com.ph.service;

import com.ph.pojo.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ph.utils.Result;

/**
* @author BCQ
* @description 针对表【news_type】的数据库操作Service
* @createDate 2023-10-16 20:33:42
*/
public interface TypeService extends IService<Type> {

    //查询所有分类并动态展示新闻类别栏位
    Result findAllTypes();
}
