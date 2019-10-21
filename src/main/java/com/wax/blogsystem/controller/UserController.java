package com.wax.blogsystem.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.druid.util.StringUtils;
import com.wax.blogsystem.common.JSONUtil;
import com.wax.blogsystem.common.PageUtil;
import com.wax.blogsystem.common.SysCode;
import com.wax.blogsystem.common.pojo.Page;
import com.wax.blogsystem.domain.Role;
import com.wax.blogsystem.domain.User;
import com.wax.blogsystem.service.RoleService;
import com.wax.blogsystem.service.UserService;
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
            User user = userService.selectByPrimaryKey(id);
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
    public String save(User user){
        if(StringUtils.isEmpty(user.getId())){
            UUID Id = UUID.randomUUID();
            user.setId(Id.toString());
            user.setDelTag(SysCode.DELTAG.WSC);
            user.setRoleCode(SysCode.ROLECODE.PTYH);
            user.setCreateTime(new Date());
            userService.insertSelective(user);
        }
        else{
            userService.updateByPrimaryKeySelective(user);
            return JSONUtil.success(SysCode.TIPMESSAGE.UPDATESUCCESS);
        }
        return JSONUtil.success(SysCode.TIPMESSAGE.SAVESUCCESS);
    }

    @RequestMapping(value = "/queryList.do")
    @ResponseBody
    public String queryList(User user, Page page,String isSearch){
        Page scope = PageUtil.queryScope(page);
        List<User> list =  userService.selectByCondition(user,scope);
        int count = userService.selectByConditionCount(user);
        return JSONUtil.layUITable(list,count);
    }


    @RequestMapping(value = "/delete.do")
    @ResponseBody
    public String delete(String ids) {
        userService.deleteByPrimaryKeys(ids);
        return JSONUtil.success();
    }

}
