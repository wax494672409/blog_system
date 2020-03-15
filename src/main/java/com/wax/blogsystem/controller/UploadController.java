package com.wax.blogsystem.controller;

import com.wax.blogsystem.common.WangEditor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

@Controller
@RequestMapping("/upload")
public class UploadController {

    @Value("${uploadFile.headHandler}")
    private String headHandler;//请求 url 中的资源映射，不推荐写死在代码中，最好提供可配置，如 /uploadFiles/**

    @Value("${uploadFile.blogHandler}")
    private String blogHandler;

    @Value("${uploadFile.headLocation}")
    private String uploadHeadLocation;//上传文件保存的本地目录，使用@Value获取全局配置文件中配置的属性值，如 E:/wmx/uploadFiles/

    @Value("${uploadFile.blogLocation}")
    private String uploadBlogLocation;

    @RequestMapping(value = "/uploadHead.do")
    @ResponseBody
    public String uploadImg(@RequestParam("myFile") MultipartFile multipartFile,
                            HttpServletRequest request, String username) throws IOException {
        InputStream inputStream = multipartFile.getInputStream();
        // 根目录下新建文件夹upload，存放上传图片
        String uploadPath = "target/classes/static/upload/head/";
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        Random random = new Random();
        int number = random.nextInt(1000000000);
        // 获取文件名称
        String fileName = multipartFile.getOriginalFilename();
        int index = fileName.indexOf(".");
        fileName = username + number + fileName.substring(index);
        String fileServerPath = basePath + headHandler.substring(0, headHandler.lastIndexOf("/") + 1) + fileName;
        // 将文件上传的服务器根目录下的upload文件夹
        System.out.println("文件访问路径：" + fileServerPath);
        File file = new File(uploadHeadLocation, fileName);
        multipartFile.transferTo(file);//文件保存
        System.out.println("文件保存路径：" + file.getPath());
        // 返回图片访问路径
        return fileServerPath;
    }

    @RequestMapping(value = "/uploadBlogImg.do")
    @ResponseBody
    public WangEditor uploadImg(@RequestParam("myFile") MultipartFile multipartFile,
                                HttpServletRequest request) throws IOException {
        InputStream inputStream = multipartFile.getInputStream();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        // 获取文件名称
        String fileName = multipartFile.getOriginalFilename();
        // 将文件上传的服务器根目录下的upload文件夹
        File file = new File(uploadBlogLocation, fileName);
        String fileServerPath = basePath + blogHandler.substring(0, blogHandler.lastIndexOf("/") + 1) + fileName;
        multipartFile.transferTo(file);//文件保存
        // 返回图片访问路径
        String url = fileServerPath;
        String[] str = {url};
        WangEditor we = new WangEditor(str);
        return we;
    }

}
