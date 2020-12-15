package com.lightning.lgc.core.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class Posts implements Serializable {
    private String postsId;
    @JsonProperty("title")
    private String postsTitle;
    @JsonProperty("image")
    private String postsTitleImg;
    private String postsContent;
    private String postsAuthor;
    private LocalDateTime postsAddTime;
    private LocalDateTime postsUpdTime;

    //文章菜单关联
    List<PostsCategory> postsCategoryList;
}
