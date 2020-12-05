package com.lightning.lgc.core.shiro;

import com.lightning.lgc.core.entity.Token;
import com.lightning.lgc.core.entity.User;
import com.lightning.lgc.core.serivce.RoleMgtService;
import com.lightning.lgc.core.serivce.UserService;
import com.lightning.lgc.core.util.JWTUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Slf4j
public class AuthRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleMgtService roleMgtService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return  token instanceof Token;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("进入授权认证");
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        Long id= JWTUtil.getUserId((String) principalCollection.getPrimaryPrincipal());
        List<String> perms=userService.getUserPerms(id);
        simpleAuthorizationInfo.addStringPermissions(perms);
        return simpleAuthorizationInfo;
    }

    @SneakyThrows
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("进入身份认证");
        String token = (String) authenticationToken.getCredentials();
        User user=userService.getUserByName(JWTUtil.getUsername(token));
        //只有当用户名不为空，并且信息和token中的payload匹配时才进入授权
        if(!ObjectUtils.isEmpty(user)&&JWTUtil.verify(token,user.getUserId(),user.getUserName())){
            return new SimpleAuthenticationInfo(token,token,this.getName());
        }
        return null;
    }
}
