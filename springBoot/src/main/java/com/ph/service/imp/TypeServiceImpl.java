package com.ph.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ph.pojo.Type;
import com.ph.service.TypeService;
import com.ph.mapper.TypeMapper;
import com.ph.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author BCQ
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2023-10-16 20:33:42
*/
@Service

public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

    @Autowired
    private TypeMapper typeMapper;


    //查询所有分类并动态展示新闻类别栏位
    @Override
    public Result findAllTypes() {
        List<Type> types = typeMapper.selectList(null);


        return Result.ok(types);
    }
}




