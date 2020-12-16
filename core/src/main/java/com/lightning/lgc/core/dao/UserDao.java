package com.lightning.lgc.core.dao;

import com.lightning.lgc.core.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    //根据用户名获取用户
    User getUserByName(String name);

    //添加用户
    int add(User user);

    //获取所有用户
    List<User> getUsers(Boolean rate);

    //修改用户
    int upd(User user);

    //删除用户
    int del(String id);

    //添加用户的角色
    int addUserRoleRelation(Map<String,String> ids);

    //根据用户ID获取用户与角色的关系
    List<String> getUrRoleId(String id);

    //删除用户与角色之间的关系
    int delUserRoleRelation(String userId,String roleId);

    //删除用户时删除所有与用户相关联的角色关系
    int delUserRoleRelations(String id);

    //获取用户登入信息，用户登入验证
    User getUserLoginInfo(String name);

    //获取用户权限
    List<String> getUserPerms(String id);

    //获取用户积分
    Long getIntegral(String id);

    //更新用户积分
    int updIntegral(Long integral,String id);

    //获取指定用户的排名
    Map<String,Object> getUserRate(String id);
}
