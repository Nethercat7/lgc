package com.lightning.lgc.core.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Role {
    private long roleId;
    private String roleCode;
    private String roleName;
    private LocalDateTime roleAddTime;
    private LocalDateTime roleUpdTime;
    private String roleStatus;

    private List<Perms> perms;
    private List<Long> permsId;
}
