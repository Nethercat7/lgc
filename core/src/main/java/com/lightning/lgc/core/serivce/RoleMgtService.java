package com.lightning.lgc.core.serivce;

import com.lightning.lgc.core.entity.Perms;
import com.lightning.lgc.core.entity.Role;

import java.util.List;

public interface RoleMgtService {
    //添加角色
    int add(Role role);

    //通过角色名称获取角色
    Role getRoleByName(String name);

    //获取所有角色
    List<Role> getRoles();

    //通过ID修改角色
    int updRole(Role role);

    //通过角色名删除角色
    int delRole(String id);

    //获取所有权限
    List<Perms> getPerms(Perms perms);

    //获取角色拥有的权限
    List<Perms> getRolePerms(String roleId);
}
