package com.lightning.lgc.core.serivce.impl;

import com.lightning.lgc.core.dao.GarbageDao;
import com.lightning.lgc.core.entity.Garbage;
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

    SnowflakeIdGeneratorUntil snowflakeIdGeneratorUntil = new SnowflakeIdGeneratorUntil(0, 2);

    @Override
    public int addCategory(GarbageCategory garbageCategory) {
        garbageCategory.setGcId(snowflakeIdGeneratorUntil.getId());
        return garbageDao.addCategory(garbageCategory);
    }

    @Override
    public List<GarbageCategory> getCategories() {
        return garbageDao.getCategories();
    }

    @Override
    public int delCategory(String id) {
        return garbageDao.delCategory(id);
    }

    @Override
    public int updCategory(GarbageCategory garbageCategory) {
        return garbageDao.updCategory(garbageCategory);
    }

    @Override
    public int addGarbage(Garbage garbage) {
        garbage.setGarbageId(snowflakeIdGeneratorUntil.getId());
        int status = garbageDao.addGarbage(garbage);
        if (status == 1) {//如果成功了就保存垃圾与类别的关系
            status = garbageDao.addGarbageRelation(snowflakeIdGeneratorUntil.getId(), garbage.getGarbageId(), garbage.getGcId());
        }
        return status;
    }

    @Override
    public List<Garbage> getGarbages(String name, String category, Integer num,String userId) {
        return garbageDao.getGarbages(name, category, num,userId);
    }

    @Override
    public int updGarbage(Garbage garbage) {
        int status = garbageDao.updGarbage(garbage);
        if (status == 1) {
            status = garbageDao.updGarbageRelation(garbage);
        }
        return status;
    }

    @Override
    public int delGarbage(String garbageId) {
        int status = garbageDao.delGarbage(garbageId);
        if (status == 1) {
            status = garbageDao.delGarbageRelation(garbageId);
        }
        return status;
    }

    @Override
    public List<Garbage> getGarbageWithFavorite(String name, String category, Integer num, String userId) {
        List<Garbage> garbageList=garbageDao.getGarbages(name,category,num,userId);
        for (Garbage garbage:garbageList){
            garbage.setInFavorite(false);
            //查询是否被收藏
            int inFavorite=garbageDao.inFavorite(userId,garbage.getGarbageId());
            if(inFavorite>0) garbage.setInFavorite(true);
        }
        return garbageList;
    }

    @Override
    public int addFavorite(String userId, String garbageId) {
        return garbageDao.addFavorite(snowflakeIdGeneratorUntil.getId(),userId,garbageId);
    }

    @Override
    public int delFavorite(String userId, String garbageId) {
        return garbageDao.delFavorite(userId,garbageId);
    }
}
