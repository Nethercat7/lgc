package com.lightning.lgc.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Posts implements Serializable {
    private String postsId;
    @JsonProperty("title")
    private String postsTitle;
    @JsonProperty("image")
    private String postsTitleImg;
    private String postsContent;
    private String postsAuthor;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime postsAddTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime postsUpdTime;
    private int postsHasPic;

    //文章菜单关联
    private String pcId;
    private String pcName;
    private String userNickname;
}
