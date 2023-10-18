package com.ph.mapper;

import com.ph.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author BCQ
* @description 针对表【news_user】的数据库操作Mapper
* @createDate 2023-10-16 20:33:42
* @Entity com.ph.pojo.User
*/
@Repository
public interface UserMapper extends BaseMapper<User> {

}




