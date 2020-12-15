package com.lightning.lgc.core.dao;

import com.lightning.lgc.core.entity.Posts;

import java.util.List;

public interface PostsMgtDao {
    //添加文章
    int addPosts(Posts posts);

    //获取文章
    List<Posts> getPosts();

    //修改文章
    int updPosts(Posts posts);

    //通过ID获取文章
    Posts getPostsById(String id);
}
