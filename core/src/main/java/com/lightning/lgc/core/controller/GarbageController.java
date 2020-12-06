package com.lightning.lgc.core.controller;

import com.lightning.lgc.core.config.Constant;
import com.lightning.lgc.core.entity.GarbageCategory;
import com.lightning.lgc.core.entity.ResultBody;
import com.lightning.lgc.core.serivce.GarbageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("garbage")
public class GarbageController {
    @Autowired
    private GarbageService garbageService;

    @PostMapping("addCategory")
    public ResultBody addCategory(@RequestBody GarbageCategory garbageCategory) {
        int status = garbageService.addCategory(garbageCategory);
        if (status == 1) {
            log.info("成功添加："+garbageCategory.getGcName());
            return new ResultBody(Constant.SUCCESS, Constant.ADD_SUCCESS, Constant.TYPE_SUCCESS);
        }
        return new ResultBody(Constant.FAILED, Constant.ADD_FAILED, Constant.TYPE_ERROR);
    }

    @GetMapping("getCategories")
    public ResultBody getCategory(){
        List<GarbageCategory> categories=garbageService.getCategories();
        log.info("成功获取："+categories.size()+"数据");
        return new ResultBody(Constant.SUCCESS,categories,Constant.GET_SUCCESS);
    }

    @GetMapping("delCategory")
    public ResultBody delCategory(Long id){
        int status=garbageService.delCategory(id);
        if(status==1){
            log.info("成功删除1条类别");
            return new ResultBody(Constant.SUCCESS,Constant.DEL_SUCCESS,Constant.TYPE_SUCCESS);
        }
        return new ResultBody(Constant.FAILED,Constant.DEL_FAILED,Constant.TYPE_ERROR);
    }

    @PostMapping("updCategory")
    public ResultBody updCategory(@RequestBody GarbageCategory garbageCategory){
        int status=garbageService.updCategory(garbageCategory);
        if(status==1){
            log.info("成功修改："+garbageCategory.getGcName());
            return new ResultBody(Constant.SUCCESS,Constant.UPD_SUCCESS,Constant.TYPE_SUCCESS);
        }
        return new ResultBody(Constant.FAILED,Constant.UPD_FAILED,Constant.TYPE_ERROR);
    }
}
