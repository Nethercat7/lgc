package com.lightning.lgc.core.controller;

import com.lightning.lgc.core.config.Constant;
import com.lightning.lgc.core.entity.ResultBody;
import com.lightning.lgc.core.entity.User;
import com.lightning.lgc.core.serivce.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("userMgt")
//@RequiresPermissions("userMgt")
public class UserMgtController {
    @Autowired
    private UserService userService;

    @PostMapping("add")
    //@RequiresPermissions("usermgt:addUser:add")
    public ResultBody add(@RequestBody User user){
        int status=userService.add(user);
        if(status== Constant.SUCCESS){
            log.info("成功添加用户:"+user.getUserName());
            return new ResultBody(Constant.SUCCESS,Constant.TYPE_SUCCESS,Constant.ADD_SUCCESS);
        }
        log.info("添加:"+user.getUserName()+"失败");
        return new ResultBody(Constant.FAILED,Constant.TYPE_ERROR,Constant.ADD_FAILED);
    }

    @GetMapping("getUserByName")
    //@RequiresPermissions("usermgt:usertable:upd")
    public ResultBody getUserByName(String name){
        User user=userService.getUserByName(name);
        if(!ObjectUtils.isEmpty(user)){
            log.info("找到用户:"+user.getUserName());
            return new ResultBody(Constant.SUCCESS,user,Constant.GET_SUCCESS);
        }
        log.info("未找到用户:"+user.getUserName());
        return new ResultBody(Constant.SUCCESS,user,Constant.NOT_FOUND);
    }

    @GetMapping("getUsers")
    //@RequiresPermissions("usermgt:usertable")
    public ResultBody getUsers(){
        List<User> users=userService.getUsers();
        if(!ObjectUtils.isEmpty(users)){
            log.info("找到"+users.size()+"个用户");
            for (User user : users) {
                //将性别转为字符串
                if (user.getUserGender().equals("1")) {
                    user.setUserGender(Constant.MALE);
                } else {
                    user.setUserGender(Constant.FEMALE);
                }
                //将状态转为字符串
                if (user.getUserStatus().equals("0")) {
                    user.setUserStatus(Constant.NORMAL);
                }
            }
            return new ResultBody(Constant.SUCCESS,users,Constant.GET_SUCCESS);
        }
        log.info("未找到用户");
        return new ResultBody(Constant.SUCCESS,users,Constant.NOT_FOUND);
    }

    @PostMapping("upd")
    //@RequiresPermissions("usermgt:usertable:upd")
    public ResultBody upd(@RequestBody User user){
        int status=userService.upd(user);
        if(status==Constant.SUCCESS){
            log.info("成功修改1个用户");
            return new ResultBody(Constant.SUCCESS,Constant.TYPE_SUCCESS,Constant.UPD_SUCCESS);
        }
        return new ResultBody(Constant.FAILED,Constant.TYPE_ERROR,Constant.UPD_FAILED);
    }

    @GetMapping("del")
    //@RequiresPermissions("usermgt:usertable:del")
    public ResultBody del(String id){
        int status=userService.del(id);
        if(status==Constant.SUCCESS){
            log.info("成功删除1个用户");
            return new ResultBody(Constant.SUCCESS,Constant.TYPE_SUCCESS,Constant.DEL_SUCCESS);
        }
        return new ResultBody(Constant.FAILED,Constant.TYPE_ERROR,Constant.DEL_FAILED);
    }
}
