package com.wzxy.uavfilingsystem.util;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

@Component
public class QiniuUtils {

    @Value("${qiniu.accessKey}")
    private String accessKey;

    @Value("${qiniu.secretKey}")
    private String secretKey;

    @Value("${qiniu.bucket}")
    private String bucket;

    @Value("${qiniu.domain}")
    private String domain;

    private UploadManager uploadManager;
    private Auth auth;
    private String upToken;

    // 在 Spring 初始化 bean 后执行
    @PostConstruct
    public void init() {
        // 配置上传区域，自动选择适合的区域
        Configuration cfg = new Configuration(Region.autoRegion());
        this.uploadManager = new UploadManager(cfg);
        this.auth = Auth.create(accessKey, secretKey);
        this.upToken = auth.uploadToken(bucket);
    }

    /**
     * 上传文件到七牛云，并根据用户名创建文件夹
     *
     * @param inputStream 文件流
     * @param username    用户名，用于创建文件夹
     * @return 文件在云端的访问地址
     * @throws QiniuException
     */
    public String uploadFile(InputStream inputStream, String username) throws QiniuException {
        // 构建文件路径：username/avatar.png
        String fileName = "Avatar" + "/"+username+"avatar.png";
        //动态生成Token
        String upToken = auth.uploadToken(bucket);
        // 上传文件
        Response response = uploadManager.put(inputStream, fileName, upToken, null, null);
        if (response.isOK()) {
            // 返回云端文件的访问路径
            return domain + "/" + fileName;
        }
        throw new RuntimeException("上传失败: " + response.bodyString());
    }

    /**
     * 删除七牛云上的文件
     *
     * @param fileName 文件名
     * @throws QiniuException
     */
    public void deleteFile(String fileName) throws QiniuException {
        BucketManager bucketManager = new BucketManager(auth, new Configuration(Region.autoRegion()));
        bucketManager.delete(bucket, fileName);
    }

    public String uploadFiling(InputStream inputStream, String fileName) throws QiniuException {
        String ImageName="Filing"+ "/"+fileName+".png";
        //动态生成Token
        String upToken = auth.uploadToken(bucket);
        //上传文件
        Response response = uploadManager.put(inputStream, ImageName, upToken, null, null);
        if(response.isOK()){
            return domain+"/"+ImageName;
        }throw new RuntimeException("上传失败: "+response.bodyString());
    }
}
