package com.lightning.lgc.core.serivce;

import com.lightning.lgc.core.entity.ResultBody;
import com.lightning.lgc.core.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    //添加用户
    List<String> add(User user);

    //根据用户名获取用户
    User getUserByName(String name);

    //用户登入
    ResultBody login(String name, String pwd);

    //获取所有用户
    List<User> getUsers(Boolean rate);

    //根据ID修改用户
    int upd(User user);

    //删除用户
    int del(String id);

    //获取用户权限
    List<String> getUserPerms(String id);

    //更新用户积分
    int updIntegral(Long integral,String id);

    //获取指定用户的排名
    Map<String,Object> getUserRate(String id);
}
