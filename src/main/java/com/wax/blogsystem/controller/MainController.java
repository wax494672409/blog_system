package com.wax.blogsystem.controller;

import com.wax.blogsystem.domain.Role;
import com.wax.blogsystem.domain.User;
import com.wax.blogsystem.service.BlogService;
import com.wax.blogsystem.service.CommentService;
import com.wax.blogsystem.service.RoleService;
import com.wax.blogsystem.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;


    @RequestMapping(value = "/goMainPage.do")
    public String goMainPage(Model model){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("loginUser",user);
        return "main";
    }


    @RequestMapping(value = "/goUserList.do")
    public String goUserList(Model model){
        List<Role> list =  roleService.selectAll();
        model.addAttribute("roleList",list);
        return "user/userList";
    }


    @RequestMapping(value = "/goFrontHomePage.do")
    public String goFrontHomePage(Model model){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("loginUser",user);
        return "front/home";
    }

    @PostMapping(value = "/getInfo.do")
    public String getInfo(Model model){
        int blogNum = blogService.getAllNum();
        int userNum = userService.getAllNum();
        int commentNum = commentService.getAllNum();
        model.addAttribute("blogNum",blogNum);
        model.addAttribute("userNum",userNum);
        model.addAttribute("commentNum",commentNum);
        return "front/home::info";
    }


}
