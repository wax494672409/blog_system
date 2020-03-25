package com.wax.blogsystem.controller;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wax.blogsystem.common.JSONUtil;
import com.wax.blogsystem.domain.Follow;
import com.wax.blogsystem.domain.Role;
import com.wax.blogsystem.domain.User;
import com.wax.blogsystem.service.FollowService;
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


    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private FollowService followService;

    @GetMapping(value = "/goPersonalPage.do")
    public String goPersonPage(String id,Model model){
        User user = userService.selectById(id);
        User loginUser = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user",user);
        model.addAttribute("loginUser",loginUser);
        boolean isFollow=false;
        Follow follow = followService.selectByFollowerAndBeFollower(loginUser.getId(),user.getId());
        isFollow = follow != null ? true:false;
        model.addAttribute("isFollow",isFollow);
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

    @GetMapping(value = "/goPersonalFollow.do")
    public String goPersonalFollow(String id,Model model,String follow){
        User loginUser = (User) SecurityUtils.getSubject().getPrincipal();
        User user = userService.selectById(id);
        model.addAttribute("loginUser",loginUser);
        model.addAttribute("user",user);
        model.addAttribute("follow",follow);
        return "personal/follower";
    }

    @GetMapping(value = "/goPersonalBlog.do")
    public String goPersonalBlog(String id,Model model,String follow){
        User loginUser = (User) SecurityUtils.getSubject().getPrincipal();
        User user = userService.selectById(id);
        model.addAttribute("loginUser",loginUser);
        model.addAttribute("user",user);
        return "personal/blog";
    }

    @GetMapping(value = "/goPersonalMsg.do")
    public String goPersonalMsg(String id,Model model,String type){
        User loginUser = (User) SecurityUtils.getSubject().getPrincipal();
        User user = new User();
        if(!StringUtils.isEmpty(id)) {
            user = userService.selectById(id);
        }
        model.addAttribute("loginUser",loginUser);
        model.addAttribute("type",type);
        model.addAttribute("user", user);
        return "personal/msg";
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



}
