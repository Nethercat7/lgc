package com.lightning.lgc.core.controller;

import com.lightning.lgc.core.entity.ResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lightning.lgc.core.serivce.UserService;

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
}
