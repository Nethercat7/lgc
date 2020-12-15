package com.lightning.lgc.core.controller;

import com.lightning.lgc.core.config.Constant;
import com.lightning.lgc.core.entity.Posts;
import com.lightning.lgc.core.entity.ResultBody;
import com.lightning.lgc.core.serivce.PostsMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            return new ResultBody(Constant.SUCCESS,Constant.ADD_SUCCESS,Constant.TYPE_SUCCESS);
        }
        return new ResultBody(Constant.FAILED,Constant.ADD_FAILED,Constant.TYPE_ERROR);
    }
}
