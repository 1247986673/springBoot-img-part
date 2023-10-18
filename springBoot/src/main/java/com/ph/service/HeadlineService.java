package com.ph.service;

import com.ph.pojo.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ph.pojo.PortalVo;
import com.ph.utils.Result;

/**
* @author BCQ
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2023-10-16 20:33:42
*/
public interface HeadlineService extends IService<Headline> {

    //分页查询首页信息
    Result findNewsPage(PortalVo portalVo);

    //查看详情
    Result showHeadlineDetail(Integer hid);

    //发布
    Result publish(Headline headline);

    //修改回显
    Result findHeadlineByHid(Integer hid);

    //修改
    Result updateHeadLine(Headline headline);
}
