package com.wax.blogsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping(value = "goMainPage.do")
    public String goMainPage(){
        return "main";
    }


    @RequestMapping(value = "goUserList.do")
    public String goUserList(){
        return "/user/userList";
    }
}
