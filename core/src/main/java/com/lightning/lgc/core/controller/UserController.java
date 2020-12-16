package com.lightning.lgc.core.controller;

import com.lightning.lgc.core.config.Constant;
import com.lightning.lgc.core.entity.ResultBody;
import com.lightning.lgc.core.entity.User;
import com.lightning.lgc.core.serivce.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("user")
public class UserController{
    @Autowired
    private UserService userService;

    @PostMapping("login")
    public ResultBody login(String name, String pwd){
        return userService.login(name,pwd);
    }

    @GetMapping("getUserByName")
    public ResultBody getUserByName(String name){
        User user=userService.getUserByName(name);
        if(!ObjectUtils.isEmpty(user)){
            log.info("找到用户:"+user.getUserName());
            return new ResultBody(Constant.SUCCESS,user,Constant.GET_SUCCESS);
        }
        log.info("未找到用户:"+user.getUserName());
        return new ResultBody(Constant.SUCCESS,user,Constant.NOT_FOUND);
    }

    @PostMapping("upd")
    public ResultBody upd(@RequestBody User user){
        int status=userService.upd(user);
        if(status==Constant.SUCCESS){
            log.info("成功修改1个用户");
            return new ResultBody(Constant.SUCCESS,Constant.UPD_SUCCESS,Constant.TYPE_SUCCESS);
        }
        return new ResultBody(Constant.FAILED,Constant.UPD_FAILED,Constant.TYPE_ERROR);
    }

    @GetMapping("updIntegral")
    public ResultBody updIntegral(Long integral,String id){
        int status=userService.updIntegral(integral,id);
        if (status>0){
            log.info("用户:"+id+"增加积分:"+integral);
            return new ResultBody(Constant.SUCCESS,Constant.UPD_SUCCESS,Constant.TYPE_SUCCESS);
        }
        return new ResultBody(Constant.FAILED,Constant.UPD_INTEGRAL_FAILED,Constant.TYPE_ERROR);
    }
}
