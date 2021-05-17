package com.lightning.lgc.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestControllerAdvice
public class ExceptionController{
    @ExceptionHandler(value= {UnauthorizedException.class})
    public void unauthorized(ServletResponse response){
        HttpServletResponse resp= (HttpServletResponse) response;
        resp.setStatus(403);
    }
}
