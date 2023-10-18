package com.ph.controller;

import com.ph.pojo.PortalVo;
import com.ph.service.HeadlineService;
import com.ph.service.TypeService;
import com.ph.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("portal")
@CrossOrigin
public class PortalController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private HeadlineService headlineService;

    //查询所有分类并动态展示新闻类别栏位
    @GetMapping("findAllTypes")
    public Result findAllTypes(){
            Result result = typeService.findAllTypes();
            return result;
    }

    //分页查询首页信息
    @PostMapping("findNewsPage")
    public Result findNewsPage(@RequestBody PortalVo portalVo){
        Result result = headlineService.findNewsPage(portalVo);
        return result;
    }

    //查看详情
    @PostMapping("showHeadlineDetail")
    public Result showHeadlineDetail(Integer hid){
        Result result = headlineService.showHeadlineDetail(hid);
        return result;
    }

}
