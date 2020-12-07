package com.lightning.lgc.core.dao;

import com.lightning.lgc.core.entity.Garbage;
import com.lightning.lgc.core.entity.GarbageCategory;

import java.util.List;

public interface GarbageDao {
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

    //保存物品与类别的关系
    int addGarbageRelation(Long gcrId,Long garbageId,Long gcId);

    //获取所有的垃圾分类
    List<Garbage> getGarbages();

    //更新垃圾分类
    int updGarbage(Garbage garbage);

    //更新垃圾与类别的关系
    int updGarbageRelation(Garbage garbage);

    //删除垃圾分类
    int delGarbage(Long garbageId);

    //删除垃圾与类别的关系
    int delGarbageRelation(Long garbageId);
}
