package com.lightning.lgc.core.dao;

import com.lightning.lgc.core.entity.Perms;
import com.lightning.lgc.core.entity.Role;

import java.util.List;
import java.util.Map;

public interface RoleMgtDao {
    //添加角色
    int add(Role role);

    //通过角色名称获取角色
    Role getRoleById(String id);

    //获取所有角色
    List<Role> getRoles();

    //通过ID修改角色
    int updRole(Role role);

    //通过角色名删除角色
    int delRole(String id);

    //获取所有权限
    List<Perms> getPerms(Perms perms);

    //添加角色与权限的关系
    int addRolePermsRelation(Map<String,String> ids);

    //获取角色拥有的权限
    List<Perms> getRolePerms(String roleId);

    //获取角色拥有的权限ID
    List<String> getRolePermsId(String roleId);

    //删除角色权限
    int delRolePerms(String rpRoleId,String rpPermsId);

    //删除所有角色权限
    int delAllRolePerms(String rpRoleId);
}
