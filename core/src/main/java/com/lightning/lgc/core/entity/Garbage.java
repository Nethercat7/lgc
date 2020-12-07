package com.lightning.lgc.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Garbage implements Serializable {
    private long garbageId;
    private String garbageName;

    //类别ID
    private long gcId;
    private String gcName;
    private List<GarbageCategory> categories;
}
