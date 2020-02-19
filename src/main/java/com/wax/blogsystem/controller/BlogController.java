package com.wax.blogsystem.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.wax.blogsystem.common.WangEditor;
import com.wax.blogsystem.domain.Blog;
import com.wax.blogsystem.domain.User;
import com.wax.blogsystem.service.BlogService;
import com.wax.blogsystem.service.CommentService;
import com.wax.blogsystem.service.UserService;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
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
import java.util.List;

@Controller
@RequestMapping(value = "/blog")
public class BlogController {

    @Autowired
    public BlogService blogService;

    @Autowired
    public UserService userService;

    @Autowired
    public CommentService commentService;


    @GetMapping(value = "/goAdd.do")
    public String goBlogAdd() {
        return "blog/add";
    }

    @GetMapping(value = "/goBlogOnePage.do")
    public String goBlogOnePage(String id, Model model) {
        User user = (User)SecurityUtils.getSubject().getPrincipal();
        Blog blog = blogService.selectById(id);
        blogService.addViewNum(blog);
        int totalNum = blogService.getTotalNum(blog.getAuthor());
        int commentTotalNum = commentService.getTotalNum(blog.id);
        model.addAttribute("totalNum",totalNum);
        model.addAttribute("commentTotalNum",commentTotalNum);
        model.addAttribute("blog",blog);
        model.addAttribute("loginUser",user);
        return "blog/blog_one";
    }

    @GetMapping(value = "/goBlogListPage.do")
    public String goBlogListPage(String id,Model model){
        User user = userService.selectById(id);
        int totalNum = blogService.getTotalNum(id);
        model.addAttribute("user",user);
        model.addAttribute("totalNum",totalNum);
        return "blog/blogList";
    }


    @PostMapping(value = "/getBlogList.do")
    public String getBlogList(Page<Blog> page,String id, Model model){
        IPage<Blog> blogPage = blogService.selectPageByUserId(page,id);
        model.addAttribute("blogList",blogPage.getRecords());
        model.addAttribute("totalNum",blogPage.getTotal());
        return "blog/blogList::blog_list";
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


    @PostMapping(value = "getTopViewList.do")
    public String getTopViewList(Page<Blog> page, Model model){
        List<Blog> list = blogService.getTopViewList(page);
        model.addAttribute("topViewList",list);
        return "blog/blog_one::top_view_list";
    }


}
