package com.lightning.lgc.core.serivce.impl;

import com.lightning.lgc.core.dao.PostsMgtDao;
import com.lightning.lgc.core.entity.Posts;
import com.lightning.lgc.core.serivce.PostsMgtService;
import com.lightning.lgc.core.util.SnowflakeIdGeneratorUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostsMgtServiceImpl implements PostsMgtService {
    @Autowired
    private PostsMgtDao postsMgtDao;

    SnowflakeIdGeneratorUntil snowflakeIdGeneratorUntil=new SnowflakeIdGeneratorUntil(0,3);

    @Override
    public int addPosts(Posts posts) {
        posts.setPostsId(snowflakeIdGeneratorUntil.getId());
        posts.setPostsAuthor("444465749134671872");
        return postsMgtDao.addPosts(posts);
    }
}
