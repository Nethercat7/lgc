package com.lightning.lgc.core.serivce.impl;

import com.lightning.lgc.core.config.Constant;
import com.lightning.lgc.core.dao.PostsMgtDao;
import com.lightning.lgc.core.entity.Posts;
import com.lightning.lgc.core.entity.PostsCategory;
import com.lightning.lgc.core.serivce.PostsMgtService;
import com.lightning.lgc.core.util.SnowflakeIdGeneratorUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PostsMgtServiceImpl implements PostsMgtService {
    @Autowired
    private PostsMgtDao postsMgtDao;

    SnowflakeIdGeneratorUntil snowflakeIdGeneratorUntil = new SnowflakeIdGeneratorUntil(0, 3);
    DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public int addPosts(Posts posts) {
        posts.setPostsId(snowflakeIdGeneratorUntil.getId());
        posts.setPostsAuthor("444465749134671872");
        if (posts.getPostsTitleImg() == null) {
            posts.setPostsTitleImg("https://cdn.uviewui.com/uview/swiper/1.jpg");
        }
        int status=postsMgtDao.addPosts(posts);
        if(status==1){
            status=postsMgtDao.addPostsCategoryRelation(snowflakeIdGeneratorUntil.getId(),posts.getPostsId(),posts.getPcId());
        }
        return status;
    }

    @Override
    public List<Posts> getPosts(String id){
        return postsMgtDao.getPosts(id);
    }

    @Override
    public int updPosts(Posts posts) {
        if (posts.getPostsTitleImg() == null) {
            posts.setPostsTitleImg("https://cdn.uviewui.com/uview/swiper/1.jpg");
        }
        int status=postsMgtDao.updPosts(posts);
        if(status==1){
            status=postsMgtDao.updPostsCategoryRelation(posts.getPcId(),posts.getPostsId());
        }
        return status;
    }

    @Override
    public Posts getPostsById(String id) {
        return postsMgtDao.getPostsById(id);
    }

    @Override
    public int delPosts(String id) {
        int status=postsMgtDao.delPosts(id);
        if(status==1){
            status=postsMgtDao.delPostsCategoryRelation(id);
        }
        return status;
    }

    @Override
    public int addPostsCategory(PostsCategory postsCategory) {
        postsCategory.setPcId(snowflakeIdGeneratorUntil.getId());
        return postsMgtDao.addPostsCategory(postsCategory);
    }

    @Override
    public List<PostsCategory> getCategories() {
        List<PostsCategory> postsCategories = postsMgtDao.getCategories();
        for (PostsCategory p : postsCategories) {
            if (p.getPcStatus() == 0) {
                p.setPcStatus2String(Constant.ENABLE);
            } else {
                p.setPcStatus2String(Constant.DISABLE);
            }
        }
        return postsCategories;
    }

    @Override
    public PostsCategory getCategoryById(String id) {
        return postsMgtDao.getCategoryById(id);
    }

    @Override
    public int updCategory(PostsCategory postsCategory) {
        return postsMgtDao.updCategory(postsCategory);
    }

    @Override
    public int delCategory(String id) {
        return postsMgtDao.delCategory(id);
    }
}
