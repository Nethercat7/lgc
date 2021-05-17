package com.lightning.lgc.core.controller;

import com.lightning.lgc.core.config.Constant;
import com.lightning.lgc.core.entity.ResultBody;
import com.lightning.lgc.core.entity.Role;
import com.lightning.lgc.core.serivce.RoleMgtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("roleMgt")
//@RequiresPermissions("rolemgt")
public class RoleMgtController {
    @Autowired
    private RoleMgtService roleMgtService;

    @PostMapping("add")
    //@RequiresPermissions("rolemgt:addrole:add")
    public ResultBody add(@RequestBody Role role) {
        int status = roleMgtService.add(role);
        if (status == Constant.SUCCESS) {
            log.info("成功添加:" + role.getRoleName());
            return new ResultBody(Constant.SUCCESS, Constant.TYPE_SUCCESS, Constant.ADD_SUCCESS);
        }
        return new ResultBody(Constant.FAILED, Constant.TYPE_ERROR, Constant.ADD_FAILED);
    }

    @GetMapping("getRoleById")
   // @RequiresPermissions("rolemgt:roletable:upd")
    public ResultBody getRoleById(String id) {
        Role role = roleMgtService.getRoleById(id);
        if (!ObjectUtils.isEmpty(role)) {
            log.info("找到角色:" + role.getRoleName());
            return new ResultBody(Constant.SUCCESS, role, Constant.GET_ROLE_SUCCESS);
        }
        return new ResultBody(Constant.FAILED, Constant.ROLE_NOT_FOUND);
    }

    @GetMapping("getRoles")
    //@RequiresPermissions(value = {"rolemgt:roletable", "usermgt:useradd:add", "usermgt:usertable:upd"}, logical = Logical.OR)
    public ResultBody getRoles() {
        List<Role> roles = roleMgtService.getRoles();
        if (!ObjectUtils.isEmpty(roles)) {
            log.info("获取" + roles.size() + "个角色");
            return new ResultBody(Constant.SUCCESS, roles, Constant.GET_SUCCESS);
        }
        return new ResultBody(Constant.FAILED, Constant.NOT_FOUND);
    }

    @PostMapping("upd")
    //@RequiresPermissions("rolemgt:roletable:upd")
    public ResultBody updRole(@RequestBody Role role) {
        int status = roleMgtService.updRole(role);
        if (status == Constant.SUCCESS) {
            log.info("更新角色:" + role.getRoleName());
            return new ResultBody(Constant.SUCCESS, Constant.TYPE_SUCCESS, Constant.UPD_SUCCESS);
        }
        return new ResultBody(Constant.FAILED, Constant.TYPE_ERROR, Constant.UPD_FAILED);
    }

    @GetMapping("del")
   // @RequiresPermissions("rolemgt:roletable:del")
    public ResultBody delRole(String id) {
        int status = roleMgtService.delRole(id);
        if (status == Constant.SUCCESS) {
            return new ResultBody(Constant.SUCCESS, Constant.TYPE_SUCCESS, Constant.DEL_SUCCESS);
        }
        return new ResultBody(Constant.FAILED, Constant.TYPE_ERROR, Constant.DEL_FAILED);
    }

    @GetMapping("getPerms")
    //@RequiresPermissions(value = {"rolemgt:roletable:add", "rolemgt:roletable:upd"}, logical = Logical.OR)
    public ResultBody getPerms() {
        return new ResultBody(Constant.SUCCESS, roleMgtService.getPerms(null), Constant.GET_SUCCESS);
    }
}
