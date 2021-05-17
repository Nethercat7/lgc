package com.lightning.lgc.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable {
    private String userId;
    private String userName;
    private String userPwd;
    private int userGender;
    private LocalDateTime userBirthday;
    private String userPhone;
    private String userEmail;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime userAddTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime userUpdTime;
    private String userSalt;
    private Long userIntegral;
    private String userAvatar;
    private int userStatus;
    private String userNickname;

    //用户所拥有的角色列表
    private List<Role> roleList;
    //用于接收前端传回来的角色ID数组
    private List<String> roleIds;
}
