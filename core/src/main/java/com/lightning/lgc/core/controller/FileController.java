package com.lightning.lgc.core.controller;

import com.lightning.lgc.core.config.Constant;
import com.lightning.lgc.core.entity.ResultBody;
import com.lightning.lgc.core.util.QiniuUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("file")
public class FileController {

    @PostMapping("uploadPic")
    public ResultBody upload(MultipartFile file, String type, String userId) throws IOException {
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            if (type.equals(Constant.AVATAR)) {
                fileName = userId + suffix;
            }
            FileInputStream fileInputStream = (FileInputStream) file.getInputStream();
            //上传图片
            String path = QiniuUtil.uploadPic(fileInputStream, fileName);
            if (path.equals(Constant.UPLOAD_FAILED)) {
                return new ResultBody(Constant.FAILED, Constant.UPLOAD_FAILED);
            } else {
                return new ResultBody(Constant.SUCCESS, path);
            }
        } else {
            return new ResultBody(Constant.FAILED, Constant.FILE_EMTY);
        }
    }
}
