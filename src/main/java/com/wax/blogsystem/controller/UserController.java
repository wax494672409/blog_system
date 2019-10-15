package com.wax.blogsystem.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.wax.blogsystem.common.JSONUtil;
import com.wax.blogsystem.domain.User;
import com.wax.blogsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/save.do")
    @ResponseBody
    public String save(User user){
        userService.insert(user);
        return "success";
    }

    @RequestMapping(value = "queryList.do")
    @ResponseBody
    public String queryList(User user){
        List<User> list =  userService.selectByCondition(user);
        int count = userService.selectByConditionCount(user);
        return JSONUtil.layUITable(list,count);
    }
}
