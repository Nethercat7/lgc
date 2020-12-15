package com.lightning.lgc.core.dao;

import com.lightning.lgc.core.entity.Posts;
import com.lightning.lgc.core.entity.PostsCategory;

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

    //删除文章
    int delPosts(String id);

    //添加分类目录
    int addPostsCategory(PostsCategory postsCategory);

    //获取分类目录
    List<PostsCategory> getCategories();

    //根据ID获取分类目录
    PostsCategory getCategoryById(String id);

    //修改分类目录
    int updCategory(PostsCategory postsCategory);
}
