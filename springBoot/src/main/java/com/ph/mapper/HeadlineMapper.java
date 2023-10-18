package com.ph.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ph.pojo.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ph.pojo.PortalVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
* @author BCQ
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2023-10-16 20:33:42
* @Entity com.ph.pojo.Headline
*/
@Repository
public interface HeadlineMapper extends BaseMapper<Headline> {
    //自定义分页查询方法
    IPage<Map> selectPageMap(IPage<Headline> page,
                             @Param("portalVo") PortalVo portalVo);

    //查询详情
    Map selectDetailMap(Integer hid);
}




