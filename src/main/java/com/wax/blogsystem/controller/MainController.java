package com.wax.blogsystem.controller;

import com.wax.blogsystem.domain.Role;
import com.wax.blogsystem.domain.User;
import com.wax.blogsystem.service.BlogService;
import com.wax.blogsystem.service.RoleService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private BlogService blogService;


    @RequestMapping(value = "/goMainPage.do")
    public String goMainPage(){
        return "main";
    }


    @RequestMapping(value = "/goUserList.do")
    public String goUserList(Model model){
        List<Role> list =  roleService.selectAll();
        model.addAttribute("roleList",list);
        return "user/userList";
    }


    @RequestMapping(value = "/goLoginPage.do")
    public String goLoginPage(){
        return "/login";
    }

    @RequestMapping(value = "/goFrontHomePage.do")
    public String goFrontHomePage(Model model){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("loginUser",user);
        model.addAttribute("blogTotalNum",blogService.getAllNum());
        return "front/home";
    }


}
