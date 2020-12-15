package com.lightning.lgc.core.serivce.impl;

import com.lightning.lgc.core.dao.PostsMgtDao;
import com.lightning.lgc.core.entity.Posts;
import com.lightning.lgc.core.entity.PostsCategory;
import com.lightning.lgc.core.serivce.PostsMgtService;
import com.lightning.lgc.core.util.SnowflakeIdGeneratorUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostsMgtServiceImpl implements PostsMgtService {
    @Autowired
    private PostsMgtDao postsMgtDao;

    SnowflakeIdGeneratorUntil snowflakeIdGeneratorUntil=new SnowflakeIdGeneratorUntil(0,3);

    @Override
    public int addPosts(Posts posts) {
        posts.setPostsId(snowflakeIdGeneratorUntil.getId());
        posts.setPostsAuthor("444465749134671872");
        if(posts.getPostsTitleImg()==null){
            posts.setPostsTitleImg("https://cdn.uviewui.com/uview/swiper/1.jpg");
        }
        return postsMgtDao.addPosts(posts);
    }

    @Override
    public List<Posts> getPosts() {
        return postsMgtDao.getPosts();
    }

    @Override
    public int updPosts(Posts posts) {
        if(posts.getPostsTitleImg()==null){
            posts.setPostsTitleImg("https://cdn.uviewui.com/uview/swiper/1.jpg");
        }
        return postsMgtDao.updPosts(posts);
    }

    @Override
    public Posts getPostsById(String id) {
        return postsMgtDao.getPostsById(id);
    }

    @Override
    public int delPosts(String id) {
        return postsMgtDao.delPosts(id);
    }

    @Override
    public int addPostsCategory(PostsCategory postsCategory) {
        postsCategory.setPcId(snowflakeIdGeneratorUntil.getId());
        return postsMgtDao.addPostsCategory(postsCategory);
    }

    @Override
    public List<PostsCategory> getCategories() {
        return null;
    }
}
