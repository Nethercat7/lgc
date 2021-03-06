package com.lightning.lgc.core.controller;

import com.lightning.lgc.core.config.Constant;
import com.lightning.lgc.core.entity.Garbage;
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
            log.info("成功添加：" + garbageCategory.getGcName());
            return new ResultBody(Constant.SUCCESS, Constant.ADD_SUCCESS, Constant.TYPE_SUCCESS);
        }
        return new ResultBody(Constant.FAILED, Constant.ADD_FAILED, Constant.TYPE_ERROR);
    }

    @GetMapping("getCategories")
    public ResultBody getCategory() {
        List<GarbageCategory> categories = garbageService.getCategories();
        log.info("成功获取：" + categories.size() + "条数据");
        return new ResultBody(Constant.SUCCESS, categories, Constant.GET_SUCCESS);
    }

    @GetMapping("delCategory")
    public ResultBody delCategory(String id) {
        int status = garbageService.delCategory(id);
        if (status == 1) {
            log.info("成功删除1条类别");
            return new ResultBody(Constant.SUCCESS, Constant.DEL_SUCCESS, Constant.TYPE_SUCCESS);
        }
        return new ResultBody(Constant.FAILED, Constant.DEL_FAILED, Constant.TYPE_ERROR);
    }

    @PostMapping("updCategory")
    public ResultBody updCategory(@RequestBody GarbageCategory garbageCategory) {
        int status = garbageService.updCategory(garbageCategory);
        if (status == 1) {
            log.info("成功修改：" + garbageCategory.getGcName());
            return new ResultBody(Constant.SUCCESS, Constant.UPD_SUCCESS, Constant.TYPE_SUCCESS);
        }
        return new ResultBody(Constant.FAILED, Constant.UPD_FAILED, Constant.TYPE_ERROR);
    }

    @PostMapping("addGarbage")
    public ResultBody addGarbage(@RequestBody Garbage garbage) {
        int status = garbageService.addGarbage(garbage);
        if (status == 1) {
            log.info("成功添加分类物品：" + garbage.getGarbageName());
            return new ResultBody(Constant.SUCCESS, Constant.ADD_SUCCESS, Constant.TYPE_SUCCESS);
        }
        return new ResultBody(Constant.FAILED, Constant.ADD_FAILED, Constant.TYPE_ERROR);
    }

    @GetMapping("getGarbages")
    public ResultBody getGarbages(String name, String category, Integer num,String userId) {
        List<Garbage> garbages = garbageService.getGarbages(name, category, num,userId);
        log.info("成功获取：" + garbages.size() + "条数据");
        return new ResultBody(Constant.SUCCESS, garbages, Constant.GET_SUCCESS, Constant.TYPE_SUCCESS);
    }

    @PostMapping("updGarbage")
    public ResultBody udpGarbage(@RequestBody Garbage garbage) {
        int status = garbageService.updGarbage(garbage);
        if (status == 1) {
            log.info("成功修改：" + garbage.getGarbageName());
            return new ResultBody(Constant.SUCCESS, Constant.UPD_SUCCESS, Constant.TYPE_SUCCESS);
        }
        return new ResultBody(Constant.FAILED, Constant.UPD_FAILED, Constant.TYPE_ERROR);
    }

    @GetMapping("delGarbage")
    public ResultBody delGarbage(String garbageId) {
        int status = garbageService.delGarbage(garbageId);
        if (status == 1) {
            log.info("成功删除垃圾" + garbageId);
            return new ResultBody(Constant.SUCCESS, Constant.DEL_SUCCESS, Constant.TYPE_SUCCESS);
        }
        return new ResultBody(Constant.FAILED, Constant.DEL_FAILED, Constant.TYPE_ERROR);
    }

    @GetMapping("getGarbageWithFavorite")
    public ResultBody getGarbageWithFavorite(String name, String category, Integer num, String userId) {
        List<Garbage> garbageList = garbageService.getGarbageWithFavorite(name, category, num, userId);
        return new ResultBody(Constant.SUCCESS, garbageList);
    }

    @GetMapping("addFavorite")
    public ResultBody addFavorite(String userId, String garbageId) {
        int status = garbageService.addFavorite(userId, garbageId);
        if (status == Constant.SUCCESS) {
            log.info("用户:" + userId + "把分类物品:" + garbageId + "添加至收藏");
            return new ResultBody(Constant.SUCCESS, Constant.ADD_FAVORITE_SUCCESS, Constant.TYPE_SUCCESS);
        }
        return new ResultBody(Constant.FAILED, Constant.ADD_FAVORITE_FAIL, Constant.TYPE_ERROR);
    }

    @GetMapping("delFavorite")
    public ResultBody delFavorite(String userId, String garbageId) {
        int status = garbageService.delFavorite(userId, garbageId);
        if (status == Constant.SUCCESS) {
            log.info("用户:" + userId + "把分类物品:" + garbageId + "移除出收藏");
            return new ResultBody(Constant.SUCCESS, Constant.DEL_FAVORITE_SUCCESS, Constant.TYPE_SUCCESS);
        }
        return new ResultBody(Constant.FAILED, Constant.DEL_FAVORITE_FAIL, Constant.TYPE_ERROR);
    }
}
