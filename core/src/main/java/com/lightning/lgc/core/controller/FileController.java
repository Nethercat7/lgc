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

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("file")
public class FileController {
    @Autowired
    private UserService userService;

    @PostMapping("uploadPic")
    public ResultBody upload(MultipartFile file, String type, String id) throws IOException {
        if (!file.isEmpty() && type != null) {
            String fileName = id + "/" + file.getOriginalFilename();
            FileInputStream fileInputStream = (FileInputStream) file.getInputStream();
            //上传图片
            String result = QiniuUtil.uploadPic(fileInputStream, fileName);
            if (result.equals(Constant.UPLOAD_FAILED)) {
                return new ResultBody(Constant.FAILED, Constant.UPLOAD_FAILED, Constant.TYPE_ERROR);
            } else {
                if (type.equals(Constant.AVATAR)) {
                    //更新用户头像
                    int status = userService.updUserAvatar(result, id);
                    if (status == Constant.SUCCESS) {
                        log.info("成功修改用户:" + id + "的头像");
                        return new ResultBody(Constant.SUCCESS, Constant.UPD_SUCCESS);
                    }
                }
            }
            return new ResultBody(Constant.FAILED, Constant.UPLOAD_FAILED, Constant.TYPE_ERROR);
        } else {
            return new ResultBody(Constant.FAILED, Constant.FILE_EMTY, Constant.TYPE_ERROR);
        }
    }
}
