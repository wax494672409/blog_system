package com.wax.blogsystem.controller;

import com.wax.blogsystem.common.WangEditor;
import com.wax.blogsystem.domain.Blog;
import com.wax.blogsystem.service.BlogService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping(value = "/blog")
public class BlogController {

    @Autowired
    public BlogService blogService;


    @GetMapping(value = "/goAdd.do")
    public String goBlogAdd() {
        return "blog/add";
    }

    @GetMapping(value = "/goBlogOnePage.do")
    public String goBlogOnePage(String id, Model model) {
        Blog blog = blogService.selectById(id);
        model.addAttribute("blog",blog);
        return "blog/blog_one";
    }

    @RequestMapping(value = "/uploadImg.do")
    @ResponseBody
    public WangEditor uploadImg(@RequestParam("myFile") MultipartFile multipartFile,
                                HttpServletRequest request) throws IOException {
        InputStream inputStream = multipartFile.getInputStream();
        // 根目录下新建文件夹upload，存放上传图片
        String uploadPath = "src/main/resources/static/upload";
        // 获取文件名称
        String filename = multipartFile.getOriginalFilename();
        // 将文件上传的服务器根目录下的upload文件夹
        File file = new File(uploadPath, filename);
        FileUtils.copyInputStreamToFile(inputStream, file);
        // 返回图片访问路径
        String url =
//                request.getScheme() + "://" + request.getServerName()
//                + ":" + request.getServerPort() +
                "/upload/" + filename;
        String[] str = {url};
        WangEditor we = new WangEditor(str);
        return we;
    }


    @PostMapping(value = "saveOrUpdate.do")
    @ResponseBody
    public String saveOrUpdate(Blog blog){
        return blogService.saveOrUpdate(blog);
    }


}
