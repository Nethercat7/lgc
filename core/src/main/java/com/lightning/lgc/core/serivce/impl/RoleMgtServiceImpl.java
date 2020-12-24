package com.lightning.lgc.core.serivce.impl;

import com.lightning.lgc.core.util.SnowflakeIdGeneratorUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lightning.lgc.core.config.Constant;
import com.lightning.lgc.core.dao.RoleMgtDao;
import com.lightning.lgc.core.dao.UserDao;
import com.lightning.lgc.core.entity.Perms;
import com.lightning.lgc.core.entity.Role;
import com.lightning.lgc.core.serivce.RoleMgtService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleMgtServiceImpl implements RoleMgtService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleMgtDao roleMgtDao;

    private SnowflakeIdGeneratorUntil snowflakeIdGeneratorUntil = new SnowflakeIdGeneratorUntil(0, 1);

    @Override
    public int add(Role role) {
        role.setRoleId(snowflakeIdGeneratorUntil.getId());
        int status = roleMgtDao.add(role);
        if (status == 1 && role.getPermsId().size() > 0) {
            //添加角色成功之后添加角色与权限之间的关系
            for (int i = 0; i < role.getPermsId().size(); i++) {
                status=addRolePermsRelation(role.getRoleId(),role.getPermsId().get(i));
            }
        }
        return status;
    }

    @Override
    public Role getRoleById(String id) {
        Role role = roleMgtDao.getRoleById(id);
        List<Perms> perms = roleMgtDao.getRolePerms(role.getRoleId());
        role.setPerms(perms);
        return role;
    }

    @Override
    public List<Role> getRoles() {
        return roleMgtDao.getRoles();
    }

    @Override
    public int updRole(Role role) {
        int status = roleMgtDao.updRole(role);
        if (status == 1) {
            List<String> ids = roleMgtDao.getRolePermsId(role.getRoleId());
            if (ids.size() > role.getPermsId().size()) {
                for (String id : ids) {
                    if (!role.getPermsId().contains(id)) {
                        status = roleMgtDao.delRolePerms(role.getRoleId(), id);
                        if (status != 1) {
                            break;
                        }
                    }
                }
            }else{
                for(String id:role.getPermsId()){
                    if(!ids.contains(id)){
                        status=addRolePermsRelation(role.getRoleId(),id);
                        if (status != 1) {
                            break;
                        }
                    }
                }
            }
        }
        return status;
    }

    @Override
    public int delRole(String id) {
        int status=roleMgtDao.delRole(id);
        if(status==1){
            userDao.delUserRoleRelations(id);
            roleMgtDao.delAllRolePerms(id);
        }
        return status;
    }

    @Override
    public List<Perms> getPerms(Perms p) {
        List<Perms> perms = roleMgtDao.getPerms(p);
        if (perms.size() > 0) {
            for (Perms p2 : perms) {
                p2.setId2String(String.valueOf(p2.getPermsId()));
                p2.setPid2String(String.valueOf(p2.getPermsPid()));
                if (p2.getPermsType() == 0) {
                    p2.setType2String(Constant.MENU);
                } else if (p2.getPermsType() == 1) {
                    p2.setType2String(Constant.CATALOG);
                } else if (p2.getPermsType() == 2) {
                    p2.setType2String(Constant.BUTTON);
                }
                List<Perms> children = getPerms(new Perms(p2.getPermsId()));
                p2.setChildren(children);
            }
        }
        return perms;
    }

    @Override
    public List<Perms> getRolePerms(String roleId) {
        return roleMgtDao.getRolePerms(roleId);
    }

    private int addRolePermsRelation(String roleId, String PermsId) {
        Map<String, String> ids = new HashMap<>();
        ids.put("prId", snowflakeIdGeneratorUntil.getId());
        ids.put("prRoleId", roleId);
        ids.put("prPermsId", PermsId);
        return roleMgtDao.addRolePermsRelation(ids);
    }
}
