package com.wax.blogsystem.controller;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wax.blogsystem.common.JSONUtil;
import com.wax.blogsystem.common.PageUtil;
import com.wax.blogsystem.common.SysCode;
import com.wax.blogsystem.domain.Role;
import com.wax.blogsystem.domain.User;
import com.wax.blogsystem.service.RoleService;
import com.wax.blogsystem.service.UserService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

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

}
