package com.lightning.lgc.core.controller;

import com.lightning.lgc.core.config.Constant;
import com.lightning.lgc.core.entity.Posts;
import com.lightning.lgc.core.entity.PostsCategory;
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
            log.info("成功添加文章："+posts.getPostsTitle());
            return new ResultBody(Constant.SUCCESS,Constant.ADD_SUCCESS,Constant.TYPE_SUCCESS);
        }
        return new ResultBody(Constant.FAILED,Constant.ADD_FAILED,Constant.TYPE_ERROR);
    }

    @GetMapping("getPosts")
    public ResultBody getPosts(){
        List<Posts> postsList=postsMgtService.getPosts();
        return new ResultBody(Constant.SUCCESS,postsList,Constant.GET_SUCCESS);
    }

    @PostMapping("updPosts")
    public ResultBody updPosts(@RequestBody Posts posts){
        int status=postsMgtService.updPosts(posts);
        if(status==1){
            log.info("成功修改文章："+posts.getPostsTitle());
            return new ResultBody(Constant.SUCCESS,Constant.UPD_SUCCESS,Constant.TYPE_SUCCESS);
        }
        return new ResultBody(Constant.FAILED,Constant.UPD_FAILED,Constant.TYPE_ERROR);
    }

    @GetMapping("getPostsById")
    public ResultBody getPostsById(String id){
        Posts posts=postsMgtService.getPostsById(id);
        log.info("找到文章："+posts.getPostsTitle());
        return new ResultBody(Constant.SUCCESS,posts,Constant.GET_SUCCESS);
    }

    @GetMapping("delPosts")
    public ResultBody delPosts(String id){
        int status=postsMgtService.delPosts(id);
        if(status==1){
            log.info("成功删除文章："+id);
            return new ResultBody(Constant.SUCCESS,Constant.DEL_SUCCESS,Constant.TYPE_SUCCESS);
        }
        return new ResultBody(Constant.FAILED,Constant.DEL_FAILED,Constant.TYPE_ERROR);
    }

    @PostMapping("addCategory")
    public ResultBody addCategory(@RequestBody PostsCategory postsCategory){
        int status=postsMgtService.addPostsCategory(postsCategory);
        if(status==1){
            log.info("成功添加分类目录："+postsCategory.getPcName());
            return new ResultBody(Constant.SUCCESS,Constant.ADD_SUCCESS,Constant.TYPE_SUCCESS);
        }
        return new ResultBody(Constant.FAILED,Constant.ADD_FAILED,Constant.TYPE_ERROR);
    }

    @GetMapping("getCategories")
    public ResultBody getCategories(){
        List<PostsCategory> categories=postsMgtService.getCategories();
        log.info("找到："+categories.size()+"个分类目录");
        return new ResultBody(Constant.SUCCESS,categories,Constant.GET_SUCCESS);
    }

    @GetMapping("getCategoryById")
    public ResultBody getCategoryById(String id){
        PostsCategory category=postsMgtService.getCategoryById(id);
        log.info("找到分类目录："+category.getPcName());
        return new ResultBody(Constant.SUCCESS,category,Constant.GET_SUCCESS);
    }

    @PostMapping("updCategory")
    public ResultBody updCategory(@RequestBody PostsCategory postsCategory){
        int status=postsMgtService.updCategory(postsCategory);
        if(status==1){
            log.info("成功修改分类目录："+postsCategory.getPcName());
            return new ResultBody(Constant.SUCCESS,Constant.UPD_SUCCESS,Constant.TYPE_SUCCESS);
        }
        return new ResultBody(Constant.FAILED,Constant.UPD_FAILED,Constant.TYPE_ERROR);
    }
}
