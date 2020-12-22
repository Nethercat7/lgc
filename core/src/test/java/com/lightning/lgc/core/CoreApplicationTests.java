package com.lightning.lgc.core;

import com.lightning.lgc.core.serivce.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

//@SpringBootTest
@Slf4j
class CoreApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        String name="用户_"+String.valueOf(UUID.randomUUID()).replace("-","");
    }

}
