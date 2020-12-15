package com.lightning.lgc.core.serivce;

import com.lightning.lgc.core.entity.Posts;

import java.util.List;

public interface PostsMgtService {
    //添加文章
    int addPosts(Posts posts);

    //获取文章
    List<Posts> getPosts();
}
