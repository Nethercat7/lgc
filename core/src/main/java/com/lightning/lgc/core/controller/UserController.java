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
import java.util.Map;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("add")
    public ResultBody add(@RequestBody User user) {
        List<String> list = userService.add(user);
        if (list.get(0).equals(Constant.REG_SUCCESS)) {
            log.info("成功注册用户:" + user.getUserName());
            return new ResultBody(Constant.SUCCESS, Constant.REG_SUCCESS, Constant.TYPE_SUCCESS);
        }
        log.info("注册:" + user.getUserName() + "失败");
        return new ResultBody(Constant.FAILED, list, Constant.REG_FAILED, Constant.TYPE_ERROR);
    }

    @PostMapping("login")
    public ResultBody login(@RequestParam("name") String name, @RequestParam("pwd") String pwd) {
        return userService.login(name, pwd);
    }

    @GetMapping("getUserByName")
    public ResultBody getUserByName(String name) {
        User user = userService.getUserByName(name);
        if (!ObjectUtils.isEmpty(user)) {
            log.info("找到用户:" + user.getUserName());
            return new ResultBody(Constant.SUCCESS, user, Constant.GET_SUCCESS);
        }
        log.info("未找到用户:" + user.getUserName());
        return new ResultBody(Constant.SUCCESS, user, Constant.NOT_FOUND);
    }

    @PostMapping("upd")
    public ResultBody upd(@RequestBody User user) {
        int status = userService.upd(user);
        if (status == Constant.SUCCESS) {
            log.info("成功修改1个用户");
            return new ResultBody(Constant.SUCCESS, Constant.UPD_SUCCESS, Constant.TYPE_SUCCESS);
        }
        return new ResultBody(Constant.FAILED, Constant.UPD_FAILED, Constant.TYPE_ERROR);
    }

    @GetMapping("updIntegral")
    public ResultBody updIntegral(Long integral, String id) {
        int status = userService.updIntegral(integral, id);
        if (status > 0) {
            log.info("用户:" + id + "增加积分:" + integral);
            return new ResultBody(Constant.SUCCESS, Constant.UPD_SUCCESS, Constant.TYPE_SUCCESS);
        }
        return new ResultBody(Constant.FAILED, Constant.UPD_INTEGRAL_FAILED, Constant.TYPE_ERROR);
    }

    @GetMapping("updUserPhone")
    public ResultBody updUserPhone(String phone, String id) {
        int status = userService.updUserPhone(phone, id);
        if (status == Constant.SUCCESS) {
            log.info("成功修改：" + id + "的手机号码为：" + phone);
            return new ResultBody(Constant.SUCCESS, Constant.UPD_SUCCESS, Constant.TYPE_SUCCESS);
        } else if (status == Constant.PHONE) {
            return new ResultBody(Constant.PHONE, Constant.USERPHONE_EXISTED, Constant.TYPE_ERROR);
        }
        return new ResultBody(Constant.FAILED, Constant.UPD_FAILED, Constant.TYPE_ERROR);
    }

    @GetMapping("updUserEmail")
    public ResultBody updUserEmail(String email, String id) {
        int status = userService.updUserEmail(email, id);
        if (status == Constant.SUCCESS) {
            log.info("成功修改：" + id + " 的电子邮箱为：" + email);
            return new ResultBody(Constant.SUCCESS, Constant.UPD_SUCCESS, Constant.TYPE_SUCCESS);
        } else if (status == Constant.EMAIL) {
            return new ResultBody(Constant.EMAIL, Constant.USEREMAIL_EXISTED, Constant.TYPE_ERROR);
        }
        return new ResultBody(Constant.FAILED, Constant.UPD_FAILED, Constant.TYPE_ERROR);
    }

    @PostMapping("updUserPwd")
    public ResultBody updUserPwd(@RequestBody Map<String, String> pwd) {
        int status = userService.updUserPwd(pwd.get("newPwd"), pwd.get("oldPwd"), pwd.get("id"));
        if (status == Constant.SUCCESS) {
            log.info("成功修改：" + pwd.get("id") + " 的密码");
            return new ResultBody(Constant.SUCCESS, Constant.UPD_SUCCESS, Constant.TYPE_SUCCESS);
        } else if (status == Constant.PWD) {
            return new ResultBody(Constant.PWD, Constant.PWD_NO_MATCH, Constant.TYPE_ERROR);
        }
        return new ResultBody(Constant.FAILED, Constant.UPD_FAILED, Constant.TYPE_ERROR);
    }
}
