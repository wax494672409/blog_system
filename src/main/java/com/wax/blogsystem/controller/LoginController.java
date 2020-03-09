package com.wax.blogsystem.controller;


import com.wax.blogsystem.common.JSONUtil;
import com.wax.blogsystem.common.SysCode;
import com.wax.blogsystem.domain.User;
import com.wax.blogsystem.service.LoginService;
import com.wax.blogsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {


    @Autowired
    private UserService userService;


    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/goLoginPage.do")
    public String goLoginPage(){
        return "/login";
    }

    @RequestMapping(value = "/login.do")
    @ResponseBody
    public String login(String email,String username,String password){
        return loginService.login(username,password);
    }


    @PostMapping(value = "/register.do")
    @ResponseBody
    public String register(User user){
        loginService.register(user);
        return JSONUtil.success("注册成功，请前往邮箱激活账号");
    }


    /**
     *  校验激活码
     * @param code
     * @return
     */
    @RequestMapping(value = "/checkCode.do")
    public String checkCode(String code){
        if (loginService.checkCode(code)){
            return "activeSuccess";
        }
        return "login";
    }


    @RequestMapping(value = "/logout.do")
    public String logout()
    {
        loginService.logout();
        return "login";
    }


}
