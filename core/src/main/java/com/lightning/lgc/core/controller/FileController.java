package com.lightning.lgc.core.controller;

import com.lightning.lgc.core.config.Constant;
import com.lightning.lgc.core.entity.ResultBody;
import com.lightning.lgc.core.serivce.UserService;
import com.lightning.lgc.core.util.QiniuUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("file")
public class FileController {
    @Autowired
    private UserService userService;

    @PostMapping("upload")
    public ResultBody upload(MultipartFile file, String type, String id) throws IOException {
        if (!file.isEmpty() && type != null) {
            String suffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));
            String fileName = type + "/" + id + "/" + UUID.randomUUID() + suffix;
            FileInputStream fileInputStream = (FileInputStream) file.getInputStream();
            //上传图片
            String result = QiniuUtil.upload(fileInputStream, fileName);
            if (result.equals(Constant.PIC_UPLOAD_FAILED)) {
                return new ResultBody(Constant.FAILED, Constant.PIC_UPLOAD_FAILED, Constant.TYPE_ERROR);
            } else {
                if (type.equals(Constant.AVATAR)) {
                    //更新用户头像
                    int status = userService.updUserAvatar(result, id);
                    if (status == Constant.SUCCESS) {
                        log.info("成功修改用户:" + id + "的头像");
                        return new ResultBody(Constant.SUCCESS, Constant.UPD_SUCCESS);
                    }
                } else if (type.equals(Constant.POSTS)) {
                    return new ResultBody(Constant.SUCCESS, (Object) result, Constant.UPD_SUCCESS);
                }
            }
            return new ResultBody(Constant.FAILED, Constant.PIC_UPLOAD_FAILED, Constant.TYPE_ERROR);
        }
        return new ResultBody(Constant.FAILED, Constant.FILE_EMTY, Constant.TYPE_ERROR);
    }

    @PostMapping("delete")
    public ResultBody delete(String key) {
        int status = QiniuUtil.delete(key);
        if (status == 1) {
            return new ResultBody(Constant.SUCCESS, Constant.DEL_SUCCESS, Constant.TYPE_SUCCESS);
        }
        return new ResultBody(Constant.FAILED, Constant.DEL_FAILED, Constant.TYPE_ERROR);
    }
}
