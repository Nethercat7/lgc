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
    List<User> getUsers();

    //修改用户
    int upd(User user);

    //删除用户
    int del(Long id);

    //添加用户的角色
    int addUserRoleRelation(Map<String,Long> ids);

    //根据用户ID获取用户与角色的关系
    List<Long> getUrRoleId(Long id);

    //删除用户与角色之间的关系
    int delUserRoleRelation(Long userId,Long roleId);

    //删除用户时删除所有与用户相关联的角色关系
    int delUserRoleRelations(Long id);

    //获取用户登入信息，用户登入验证
    User getUserLoginInfo(String name);

    //获取用户权限
    List<String> getUserPerms(Long id);
}
