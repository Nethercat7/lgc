package com.lightning.lgc.core;

import com.lightning.lgc.core.serivce.UserService;
import com.lightning.lgc.core.util.PwdUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

//@SpringBootTest
@Slf4j
class CoreApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        String pwd=PwdUtil.pwd2MD5("asd123","bhN)rkp",1739);
        System.out.println(pwd);
    }

}
