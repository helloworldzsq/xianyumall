package com.dz.controller.mall;

import com.dz.pojo.Product;
import com.dz.service.impl.ProductServiceImpl;
import com.dz.util.OssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.UUID;

@Controller
public class FileUploadController {
    @Autowired
    private ProductServiceImpl productService;
    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public String upload(Product product,@RequestParam("file")MultipartFile file) throws Exception {
        String filename = file.getResource().getFilename();
        //这里文件名用了uuid 防止重复，可以根据自己的需要来写
        String name = UUID.randomUUID() + filename.substring(filename.lastIndexOf("."), filename.length());
        name = name.replace("-", "");
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("上传失败");
        }
        OssUtil util = new OssUtil();
        //上传成功返回完整路径的url
        String url = util.uploadFile2OSS(inputStream, name);
        product.setPictureUrl(url);
        Date date = new Date();
        product.setTime(date);
        productService.save(product);
        //将数据添加到ElasticSearch
        productService.parseProduct(product);
        return "redirect:/";
    }

    /**
     * 判断文件是否存在
     * @param fileName  储存的文件名
     * @return
     */
    @GetMapping("/file/exists")
    public Boolean exists(String fileName) {
        OssUtil util = new OssUtil();
        Boolean bool = util.doesObjectExist(fileName);
        return bool;
    }


    /**
     * 下载oss对应文件
     * @param fileName 储存的文件名
     * @return
     */
    @PostMapping("/file/downLoad")
    public void downloadFile(String fileName, HttpServletResponse response) {
        try {
            OssUtil ossClientUtil = new OssUtil();
            InputStream is = ossClientUtil.getInputStreamByFileUrl(ossClientUtil.getFiledir() + fileName);
            // 配置文件下载
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            // 下载文件能正常显示中文
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[1024];
            int len = -1;
            while ((len = is.read(b)) != -1) {
                os.write(b, 0, len);
            }
            is.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
