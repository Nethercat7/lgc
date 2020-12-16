package com.lightning.lgc.core.serivce.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import com.lightning.lgc.core.config.Constant;
import com.lightning.lgc.core.dao.UserDao;
import com.lightning.lgc.core.entity.ResultBody;
import com.lightning.lgc.core.entity.User;
import com.lightning.lgc.core.serivce.UserService;
import com.lightning.lgc.core.util.JWTUtil;
import com.lightning.lgc.core.util.PwdUtil;
import com.lightning.lgc.core.util.SnowflakeIdGeneratorUntil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    //随机盐的个数
    private static final int SALT = 7;
    //散列次数
    private static final int HASH = 1739;

    @Autowired
    private UserDao userDao;

    SnowflakeIdGeneratorUntil snowflakeIdGeneratorUntil = new SnowflakeIdGeneratorUntil(0, 0);

    @Override
    public int add(User user) {
        user.setUserId(snowflakeIdGeneratorUntil.getId());
        String salt = PwdUtil.getSalt(SALT);
        user.setUserPwd(PwdUtil.pwd2MD5(user.getUserPwd(), salt, HASH));
        user.setUserSalt(salt);
        int status = userDao.add(user);
        if (status == 1) {
            //添加用户的角色
            if (user.getRoleIds().size() > 0) {
                for (String id : user.getRoleIds()) {
                    status = addUserRoleRelation(user.getUserId(), id);
                    if (status == 0) {
                        break;
                    }
                }
            } else {
                //如果添加用户时没有选择角色，默认为普通用户
                status = addUserRoleRelation(user.getUserId(), "444519257385074688");
            }
        }
        return status;
    }

    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Override
    public ResultBody login(String name, String pwd) {
        User user = userDao.getUserLoginInfo(name);
        if (ObjectUtils.isEmpty(user)) {
            //用户名是否注册
            return new ResultBody(Constant.FAILED, Constant.USER_NOT_REGISTER,Constant.TYPE_ERROR);
        } else if (!user.getUserPwd().equals(PwdUtil.pwd2MD5(pwd, user.getUserSalt(), HASH))) {
            //密码是否正确
            return new ResultBody(Constant.FAILED,Constant.WRONG_PWD,Constant.TYPE_ERROR);
        }
        return new ResultBody(Constant.SUCCESS, JWTUtil.createToken(user.getUserId(), user.getUserName()), Constant.LOGIN_SUCCESS,Constant.TYPE_SUCCESS);
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public int upd(User user) {
        int status = userDao.upd(user);
        if (status == 1) {
            List<String> ids = userDao.getUrRoleId(user.getUserId());
            //如果前端传入的角色ID小于数据库中已存在的ID，那么就删除用户和角色的关系，否则即更新。
            if (ids.size() > user.getRoleIds().size()) {
                for (String id : ids) {
                    if (!user.getRoleIds().contains(id)) {
                        status = userDao.delUserRoleRelation(user.getUserId(), id);
                    }
                    if (status == 0) {
                        break;
                    }
                }
            } else {
                for(String id:user.getRoleIds()){
                    if(!ids.contains(id)){
                        status=addUserRoleRelation(user.getUserId(),id);
                    }
                    if(status==0){
                        break;
                    }
                }
            }
        }
        return status;
    }

    @Override
    public int del(String id) {
        int status=userDao.del(id);
        if(status==1) {
            //删除和用户相关联的角色
            if (userDao.delUserRoleRelations(id) < 1) {
                status = 0;
            }
        }
        return status;
    }

    @Override
    public List<String> getUserPerms(String id) {
        return userDao.getUserPerms(id);
    }

    private int addUserRoleRelation(String userId, String roleId) {
        Map<String, String> ids = new HashMap<>();
        ids.put("urId", snowflakeIdGeneratorUntil.getId());
        ids.put("urUserId", userId);
        ids.put("urRoleId", roleId);
        return userDao.addUserRoleRelation(ids);
    }
}
