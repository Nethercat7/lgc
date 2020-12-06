package com.lightning.lgc.core.serivce;

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
}
