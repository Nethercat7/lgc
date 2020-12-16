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

    //根据ID删除分类目录
    int delCategory(String id);

    //添加文章和文章类别之间的关系
    int addPostsCategoryRelation(String pcrId,String postsId,String categoryId);

    //修改文章和类别之间的关系
    int updPostsCategoryRelation(String pcId,String postsId);

    //删除文章和类别之间的关系
    int delPostsCategoryRelation(String id);
}
