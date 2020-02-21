package com.wax.blogsystem.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wax.blogsystem.domain.Activity;
import com.wax.blogsystem.domain.User;
import com.wax.blogsystem.service.ActivityService;
import com.wax.blogsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/activity")
public class ActivityController {

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    @PostMapping(value = "/getActivityList.do")
    public String getActivityList(Page<Activity> page, String id, Model model){
        List<Activity> list = activityService.getActitvityList(page,id);
        User user = userService.selectById(id);
        model.addAttribute("activityList",list);
        model.addAttribute("user",user);
        return "user/personal::activity_list";
    }

}
