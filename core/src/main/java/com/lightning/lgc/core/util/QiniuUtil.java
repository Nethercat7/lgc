package com.lightning.lgc.core.util;

import com.google.gson.Gson;
import com.lightning.lgc.core.config.Constant;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import java.io.FileInputStream;


public class QiniuUtil {
    private static final String ACCESS_KEY = "87K6anIxlH9MciOCccKUzCByxs_Apa5AdurchoH4";
    private static final String SECRET_KEY = "iWUFa1ghu3YKgpLk8oX9v6Bd9xJLH-VYqGAXHL8l";
    private static final String BUCKET = "nethercat7";
    private static final String DOMAIN = "http://qlr4gr51r.hn-bkt.clouddn.com";

    public static String upload(FileInputStream fileInputStream, String key) {
        Configuration config = new Configuration(Region.huanan());
        UploadManager uploadManager = new UploadManager(config);
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        String token = auth.uploadToken(BUCKET);
        try {
            Response res = uploadManager.put(fileInputStream, key, token, null, null);
            if (res.isOK()) {
                DefaultPutRet putRet = new Gson().fromJson(res.bodyString(), DefaultPutRet.class);
                return DOMAIN + "/" + putRet.key;
            } else {
                return Constant.PIC_UPLOAD_FAILED;
            }
        } catch (QiniuException e) {
            e.printStackTrace();
            return Constant.PIC_UPLOAD_FAILED;
        }
    }

    public static int delete(String key) {
        Configuration config = new Configuration(Region.huanan());
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        BucketManager bucketManager = new BucketManager(auth, config);
        try {
            Response res = bucketManager.delete(BUCKET,key);
            if(res.isOK()){
                return Constant.SUCCESS;
            }else{
                return Constant.FAILED;
            }
        } catch (QiniuException e) {
            e.printStackTrace();
            return Constant.FAILED;
        }
    }
}
