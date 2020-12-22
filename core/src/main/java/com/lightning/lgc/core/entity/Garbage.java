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
    private String garbageId;
    private String garbageName;

    //类别ID
    private String gcId;
    private String gcName;
    private String gcType;
    private List<GarbageCategory> categories;

    //是否被收藏
    private boolean inFavorite;
}
