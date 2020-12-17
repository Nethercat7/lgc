package com.lightning.lgc.core.controller;

import com.lightning.lgc.core.config.Constant;
import com.lightning.lgc.core.entity.ResultBody;
import com.lightning.lgc.core.util.SnowflakeIdGeneratorUntil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("file")
public class FileController {

    SnowflakeIdGeneratorUntil snowflakeIdGeneratorUntil =new SnowflakeIdGeneratorUntil(0,4);

    @PostMapping("uploadTitlePic")
    public ResultBody uploadTitlePic(MultipartFile file) {
        String filename = snowflakeIdGeneratorUntil.getId();
        String filepath = "D:/Projects/lgc/core/src/main/resources/static/pic/title/";
        return getResultBody(file, filename, filepath);
    }

    @PostMapping("uploadAvatar")
    public ResultBody uploadAvatar(MultipartFile file,String id) {
        String filepath = "D:/Projects/lgc/core/src/main/resources/static/pic/avatar/";
        return getResultBody(file, id, filepath);
    }

    private ResultBody getResultBody(MultipartFile file, String filename, String filepath) {
        File dest = new File(filepath + filename);
        try {
            file.transferTo(dest);
            ResultBody resultBody=new ResultBody();
            resultBody.setCode(Constant.SUCCESS);
            resultBody.setObj(filename);
            resultBody.setMsg("上传成功");
            return resultBody;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResultBody(Constant.FAILED, "上传失败");
    }
}
