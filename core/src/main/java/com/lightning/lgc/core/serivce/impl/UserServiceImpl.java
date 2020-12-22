package com.lightning.lgc.core.serivce.impl;

import com.lightning.lgc.core.config.Constant;
import com.lightning.lgc.core.dao.UserDao;
import com.lightning.lgc.core.entity.ResultBody;
import com.lightning.lgc.core.entity.User;
import com.lightning.lgc.core.serivce.UserService;
import com.lightning.lgc.core.util.JWTUtil;
import com.lightning.lgc.core.util.PwdUtil;
import com.lightning.lgc.core.util.SnowflakeIdGeneratorUntil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
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
    public List<String> add(User user) {
        List<String> list = new ArrayList<>();
        int checkName = userDao.checkUserName(user.getUserName());

        if (checkName > 0) list.add(Constant.USERNAME_EXISTED);

        if (user.getUserPhone() != null) {
            int checkPhone = userDao.checkUserPhone(user.getUserPhone());
            if (checkPhone > 0) list.add(Constant.USERPHONE_EXISTED);
        }

        if (user.getUserPhone() != null) {
            int checkEmail = userDao.checkUserEmail(user.getUserEmail());
            if (checkEmail > 0) list.add(Constant.USEREMAIL_EXISTED);
        }

        if (list.size() == 0) {
            user.setUserId(snowflakeIdGeneratorUntil.getId());
            String salt = PwdUtil.getSalt(SALT);
            user.setUserPwd(PwdUtil.pwd2MD5(user.getUserPwd(), salt, HASH));
            user.setUserSalt(salt);
            int status = userDao.add(user);
            if (status == 1) {
                //添加用户的角色
                if (user.getRoleIds() != null && user.getRoleIds().size() > 0) {
                    for (String id : user.getRoleIds()) {
                        status = addUserRoleRelation(user.getUserId(), id);
                        if (status == 0) {
                            list.add(Constant.REG_FAILED);
                            return list;
                        }
                    }
                } else {
                    //如果添加用户时没有选择角色，默认为普通用户
                    status = addUserRoleRelation(user.getUserId(), "444519257385074688");
                    if (status == 0) {
                        list.add(Constant.REG_FAILED);
                        return list;
                    }
                }
            }
            list.add(Constant.REG_SUCCESS);
        }

        return list;
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
            return new ResultBody(Constant.FAILED, Constant.USER_NOT_REGISTER, Constant.TYPE_ERROR);
        } else if (!user.getUserPwd().equals(PwdUtil.pwd2MD5(pwd, user.getUserSalt(), HASH))) {
            //密码是否正确
            return new ResultBody(Constant.FAILED, Constant.WRONG_PWD, Constant.TYPE_ERROR);
        }
        return new ResultBody(Constant.SUCCESS, JWTUtil.createToken(user.getUserId(), user.getUserName()), Constant.LOGIN_SUCCESS, Constant.TYPE_SUCCESS);
    }

    @Override
    public List<User> getUsers(Boolean rate) {
        return userDao.getUsers(rate);
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
                for (String id : user.getRoleIds()) {
                    if (!ids.contains(id)) {
                        status = addUserRoleRelation(user.getUserId(), id);
                    }
                    if (status == 0) {
                        break;
                    }
                }
            }
        }
        return status;
    }

    @Override
    public int del(String id) {
        int status = userDao.del(id);
        if (status == 1) {
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

    @Override
    public int updIntegral(Long integral, String id) {
        //先获取用户积分
        Long userIntegral = userDao.getIntegral(id);
        //把获取到的积分+上本次的积分
        userIntegral += integral;
        return userDao.updIntegral(userIntegral, id);
    }

    @Override
    public Map<String, Object> getUserRate(String id) {
        return userDao.getUserRate(id);
    }

    @Override
    public int updUserPhone(String phone, String id) {
        //检查重复
        int checkPhone = userDao.checkUserPhone(phone);
        int status = Constant.PHONE;//手机号码已被使用
        if (checkPhone > 0) return status;
        return userDao.updUserPhone(phone, id);
    }

    @Override
    public int updUserEmail(String email, String id) {
        //检查重复
        int checkEmail = userDao.checkUserEmail(email);
        int status = Constant.EMAIL;//邮箱已被使用
        if (checkEmail > 0) return status;
        return userDao.updUserEmail(email, id);
    }
}
