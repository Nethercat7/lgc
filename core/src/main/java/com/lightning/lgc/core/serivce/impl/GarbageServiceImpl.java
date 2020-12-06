package com.lightning.lgc.core.serivce.impl;

import com.lightning.lgc.core.dao.GarbageDao;
import com.lightning.lgc.core.entity.GarbageCategory;
import com.lightning.lgc.core.serivce.GarbageService;
import com.lightning.lgc.core.util.SnowflakeIdGeneratorUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GarbageServiceImpl implements GarbageService {
    @Autowired
    private GarbageDao garbageDao;

    SnowflakeIdGeneratorUntil snowflakeIdGeneratorUntil=new SnowflakeIdGeneratorUntil(0,2);

    @Override
    public int addCategory(GarbageCategory garbageCategory) {
        garbageCategory.setGcId(snowflakeIdGeneratorUntil.nextId());
        return garbageDao.addCategory(garbageCategory);
    }

    @Override
    public List<GarbageCategory> getCategories() {
        return garbageDao.getCategories();
    }

    @Override
    public int delCategory(Long id) {
        return garbageDao.delCategory(id);
    }

    @Override
    public int updCategory(GarbageCategory garbageCategory) {
        return garbageDao.updCategory(garbageCategory);
    }
}
