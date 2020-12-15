package com.lightning.lgc.core;

import com.lightning.lgc.core.util.SnowflakeIdGeneratorUntil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

//@SpringBootTest
@Slf4j
class CoreApplicationTests {

    @Test
    void contextLoads() {
        SnowflakeIdGeneratorUntil snowflakeIdGeneratorUntil = new SnowflakeIdGeneratorUntil(0, 2);
        System.out.println(snowflakeIdGeneratorUntil.getId());
    }

}
