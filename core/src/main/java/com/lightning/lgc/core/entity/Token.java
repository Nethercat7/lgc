package com.lightning.lgc.core.entity;

import lombok.AllArgsConstructor;
import org.apache.shiro.authc.AuthenticationToken;

@AllArgsConstructor
public class Token implements AuthenticationToken {
    private String token;


    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
