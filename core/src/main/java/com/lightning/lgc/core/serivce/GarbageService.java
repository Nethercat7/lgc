package com.lightning.lgc.core.serivce;

import com.lightning.lgc.core.entity.Garbage;
import com.lightning.lgc.core.entity.GarbageCategory;

import java.util.List;

public interface GarbageService {
    //添加分类类别
    int addCategory(GarbageCategory garbageCategory);

    //获取所有分类类别
    List<GarbageCategory> getCategories();

    //删除分类类别
    int delCategory(String id);

    //修改分类类别
    int updCategory(GarbageCategory garbageCategory);

    //添加分类物品
    int addGarbage(Garbage garbage);

    //获取垃圾分类
    List<Garbage> getGarbages(String name,String category,Integer num,String userId);

    //更新垃圾分类
    int updGarbage(Garbage garbage);

    //删除垃圾分类
    int delGarbage(String garbageId);

    //获取分类物品以及是否收藏
    List<Garbage> getGarbageWithFavorite(String name,String category,Integer num,String userId);

    //添加物品至收藏
    int addFavorite(String userId,String garbageId);

    //从收藏中删除物品
    int delFavorite(String userId,String garbageId);
}
