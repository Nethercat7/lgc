package com.lightning.lgc.core.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class PostsCategory implements Serializable {
    private String pcId;
    private String pcName;
    private String pcDescription;
    private LocalDateTime pcAddTime;
    private LocalDateTime pcUpdTime;
    private String pcStatus;
}
