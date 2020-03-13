package com.wax.blogsystem.controller;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wax.blogsystem.common.JSONUtil;
import com.wax.blogsystem.domain.Role;
import com.wax.blogsystem.domain.User;
import com.wax.blogsystem.service.RoleService;
import com.wax.blogsystem.service.UserService;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Value("${uploadFile.resourceHandler}")
    private String resourceHandler;//请求 url 中的资源映射，不推荐写死在代码中，最好提供可配置，如 /uploadFiles/**

    @Value("${uploadFile.location}")
    private String uploadFileLocation;//上传文件保存的本地目录，使用@Value获取全局配置文件中配置的属性值，如 E:/wmx/uploadFiles/

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/goPersonalPage.do")
    public String goPersonPage(String id,Model model){
        User user = userService.selectById(id);
        User loginUser = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user",user);
        model.addAttribute("loginUser",loginUser);
        return "user/personal";
    }

    @GetMapping(value = "/goHeadPicPage.do")
    public String goHeadPicPage(String id,Model model){
        User user = userService.selectById(id);
        model.addAttribute("user",user);
        return "user/headPic";
    }

    @RequestMapping(value="/goUserAdd.do")
    public String goUserAdd(String id,Model model){
        if(StringUtils.isEmpty(id)){
            User user = new User();
            model.addAttribute("user",user);
            model.addAttribute("state","add");
        }
        else{
            QueryWrapper<User> queryWrapper = new QueryWrapper();
            queryWrapper.eq("id",id);
            User user = userService.selectOne(queryWrapper);
            model.addAttribute("user",user);
            model.addAttribute("state","update");
        }
        return "/user/userAdd";
    }

    @RequestMapping(value="/goUserChangeRole.do")
    public String goUserChangeRole(String userId,Model model){
        List<Role> list =  roleService.selectAll();
        model.addAttribute("roleList",list);
        model.addAttribute("userId",userId);
        return "/user/userChangeRole";
    }


    @RequestMapping(value = "/saveOrUpdate.do",method = RequestMethod.POST)
    @ResponseBody
    public String saveOrUpdate(User user){
        return userService.saveOrUpdate(user);
    }

    @RequiresRoles("admin")
    @RequestMapping(value = "/queryList.do")
    @ResponseBody
    public String queryList(Page<User> page, User user){
        IPage<User> iPage = userService.selectPage(page, user);
        return JSONUtil.layUITable(iPage.getRecords(),iPage.getTotal());
    }


    @RequestMapping(value = "/delete.do")
    @ResponseBody
    public String delete(String ids) {
        userService.deleteByPrimaryKeys(ids);
        return JSONUtil.success();
    }


    @PostMapping(value = "/topBlogUserList.do")
    public String topBlogUserList(Page<User> page,Model model){
        IPage<User> iPage = userService.topBlogUserList(page);
        model.addAttribute("topBlogUserList",iPage.getRecords());
        return "front/home::top_blog_user_list";
    }

    @RequestMapping(value = "/uploadHead.do")
    @ResponseBody
    public String uploadImg(@RequestParam("myFile") MultipartFile multipartFile,
                                HttpServletRequest request,String username) throws IOException {
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
        String fileServerPath = basePath + resourceHandler.substring(0, resourceHandler.lastIndexOf("/") + 1) + fileName;
        // 将文件上传的服务器根目录下的upload文件夹
        System.out.println("文件访问路径：" + fileServerPath);
        File file = new File(uploadFileLocation, fileName);
        multipartFile.transferTo(file);//文件保存
        System.out.println("文件保存路径：" + file.getPath());
        // 返回图片访问路径
        return fileServerPath;
    }


}
