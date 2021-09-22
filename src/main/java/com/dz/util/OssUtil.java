package com.dz.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.net.URL;
import java.util.Date;

/**
 * 阿里云 OSS文件类
 */
public class OssUtil {

    Log log = LogFactory.getLog(OssUtil.class);
    private String endpoint = "oss-cn-hangzhou.aliyuncs.com";
    //阿里云的accessKeyId 这里修改成为自己的
    private String accessKeyId = "LTAI5t8hEXyBpSVw9Z6Mw36A";
    //阿里云的accessKeySecret　这里修改成为自己的
     private String accessKeySecret = "MRi24YLYUvO03Gyqeojuom8WIoWMJm";

    //空间
    private String bucketName = "skd-mall";

    //文件存储目录
    private String filedir = "val/";

    private OSSClient ossClient;

    public OssUtil() {
        ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    public String getFiledir() {
        return this.filedir;
    }

    //自定义上传文件夹
    public OssUtil(String filedir) {
        this.filedir = filedir;
        ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    /**
     * 初始化
     */
    public void init() {
        ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    /**
     * 销毁
     */
    public void destory() {
        ossClient.shutdown();
    }

    /**
     * 上传到OSS服务器
     *
     * @param instream 文件流
     * @param fileName 文件名称 包括后缀名
     * @return 出错返回"" ,唯一MD5数字签名
     */
    public String uploadFile2OSS(InputStream instream, String fileName) {
        String ret = "";
        // 判断bucket是否已经存在,不存在进行创建
        if (!doesBucketExist()) {
            createBucket();
        }
        try {
            //创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(instream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);

            // 指定上传文件操作时是否覆盖同名Object。
            // 不指定x-oss-forbid-overwrite时，默认覆盖同名Object。
            // 指定x-oss-forbid-overwrite为false时，表示允许覆盖同名Object。
            // 指定x-oss-forbid-overwrite为true时，表示禁止覆盖同名Object，如果同名Object已存在，程序将报错。
            objectMetadata.setHeader("x-oss-forbid-overwrite", "false");

            String objectName = filedir + fileName;

            //上传文件
            ossClient.putObject(bucketName, objectName, instream, objectMetadata);
            // 封装  url 路径
            Date expiration = new Date(System.currentTimeMillis() + 500 * 3600 * 1000);
            URL url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
//            String url = "http://" + bucketName + "." + endpoint + "/" + objectName;
            System.out.println(objectName);
            ret = url.toString();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            ossClient.shutdown();
            try {
                if (instream != null) {
                    instream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }


    /**
     * 判断文件是否存在。doesObjectExist还有一个参数isOnlyInOSS，
     * 如果为true则忽略302重定向或镜像；如果为false，则考虑302重定向或镜像。
     * yourObjectName 表示上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
     *
     * @return 存在返回true
     */
    public boolean doesObjectExist(String objectName) {
        boolean exists = ossClient.doesObjectExist(bucketName, filedir + objectName);
        return exists;
    }

    /**
     * 判断Bucket是否存在
     *
     * @return 存在返回true
     */
    public boolean doesBucketExist() {
        boolean exists = ossClient.doesBucketExist(bucketName);
        return exists;
    }

    /**
     * 创建Bucket
     */
    public void createBucket() {
        CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
        // 设置bucket权限为公共读，默认是私有读写
        createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
        // 设置bucket存储类型为低频访问类型，默认是标准类型
        createBucketRequest.setStorageClass(StorageClass.IA);
        boolean exists = ossClient.doesBucketExist(bucketName);
        if (!exists) {
            try {
                ossClient.createBucket(createBucketRequest);
                // 关闭client
                ossClient.shutdown();
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

    /**
     * Description: 判断OSS服务文件上传时文件的contentType
     *
     * @param FilenameExtension 文件后缀
     * @return String
     */
    public static String getcontentType(String FilenameExtension) {
        if ("bmp".equalsIgnoreCase(FilenameExtension)) {
            return "image/bmp";
        }
        if ("gif".equalsIgnoreCase(FilenameExtension)) {
            return "image/gif";
        }
        if ("jpeg".equalsIgnoreCase(FilenameExtension) ||
                "jpg".equalsIgnoreCase(FilenameExtension) ||
                "png".equalsIgnoreCase(FilenameExtension)) {
            return "image/jpeg";
        }
        if ("html".equalsIgnoreCase(FilenameExtension)) {
            return "text/html";
        }
        if ("txt".equalsIgnoreCase(FilenameExtension)) {
            return "text/plain";
        }
        if ("vsd".equalsIgnoreCase(FilenameExtension)) {
            return "application/vnd.visio";
        }
        if ("pptx".equalsIgnoreCase(FilenameExtension) ||
                "ppt".equalsIgnoreCase(FilenameExtension)) {
            return "application/vnd.ms-powerpoint";
        }
        if ("docx".equalsIgnoreCase(FilenameExtension) ||
                "doc".equalsIgnoreCase(FilenameExtension)) {
            return "application/msword";
        }
        if ("xml".equalsIgnoreCase(FilenameExtension)) {
            return "text/xml";
        }
        return "image/jpeg";
    }


    /**
     * @param fileName
     * @return
     * @Title: getInputStreamByFileUrl
     * @Description: 根据文件路径获取InputStream流
     * @return: InputStream
     */
    public InputStream getInputStreamByFileUrl(String fileName) {
        // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
        OSSObject ossObject = ossClient.getObject(bucketName, fileName);
        return ossObject.getObjectContent();
    }
}
