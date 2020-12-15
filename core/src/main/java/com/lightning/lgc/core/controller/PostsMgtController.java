package com.lightning.lgc.core.controller;

import com.lightning.lgc.core.config.Constant;
import com.lightning.lgc.core.entity.Posts;
import com.lightning.lgc.core.entity.ResultBody;
import com.lightning.lgc.core.serivce.PostsMgtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("postsMgt")
public class PostsMgtController {
    @Autowired
    private PostsMgtService postsMgtService;

    @PostMapping("addPosts")
    public ResultBody addPosts(@RequestBody Posts posts){
        int status=postsMgtService.addPosts(posts);
        if(status==1){
            log.info("成功添加文章："+posts);
            return new ResultBody(Constant.SUCCESS,Constant.ADD_SUCCESS,Constant.TYPE_SUCCESS);
        }
        return new ResultBody(Constant.FAILED,Constant.ADD_FAILED,Constant.TYPE_ERROR);
    }

    @GetMapping("getPosts")
    public ResultBody getPosts(){
        List<Posts> postsList=postsMgtService.getPosts();
        return new ResultBody(Constant.SUCCESS,postsList,Constant.GET_SUCCESS);
    }
}
