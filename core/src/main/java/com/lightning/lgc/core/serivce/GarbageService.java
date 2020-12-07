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
    int delCategory(Long id);

    //修改分类类别
    int updCategory(GarbageCategory garbageCategory);

    //添加分类物品
    int addGarbage(Garbage garbage);

    //获取垃圾分类
    List<Garbage> getGarbages(String name,String category);

    //更新垃圾分类
    int updGarbage(Garbage garbage);

    //删除垃圾分类
    int delGarbage(Long garbageId);
}
