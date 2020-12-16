package com.lightning.lgc.core.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Perms {
    private String permsId;
    private String permsPid;
    private String permsName;
    private String permsCode;
    private int permsType;
    private String permsUrl;
    private String permsIcon;
    private String permsDescription;
    private int permsStatus;
    private LocalDateTime permsAddTime;
    private LocalDateTime permsUpdTime;

    //用于存储子权限
    private List<Perms> children;

    //用于将int文本化
    private String id2String;
    private String pid2String;
    private String type2String;

    public Perms(String permsPid) {
        this.permsPid = permsPid;
    }
}
