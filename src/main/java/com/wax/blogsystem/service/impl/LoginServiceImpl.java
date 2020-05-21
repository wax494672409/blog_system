package com.wax.blogsystem.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.wax.blogsystem.common.Encript;
import com.wax.blogsystem.common.JSONUtil;
import com.wax.blogsystem.common.SysCode;
import com.wax.blogsystem.domain.User;
import com.wax.blogsystem.mapper.UserMapper;
import com.wax.blogsystem.service.LoginService;
import com.wax.blogsystem.service.MsgService;
import com.wax.blogsystem.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserService userService;

    @Autowired
    private MsgService msgService;

    @Autowired
    UserMapper userMapper;

    @Override
    public String login(String username, String password) {
        if(StringUtils.isEmpty(username))
        {
            return JSONUtil.error("请填写用户名/邮箱");
        }
        String msg="";
        // 1、获取Subject实例对象
        Subject currentUser = SecurityUtils.getSubject();

//        // 2、判断当前用户是否登录
//        if (currentUser.isAuthenticated() == false) {
//
//        }

        // 3、将用户名和密码封装到UsernamePasswordToken
        //String md5Password = Encript.md5(password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        // 4、认证
        try {
            currentUser.login(token);// 传到MyAuthorizingRealm类中的方法进行认证
            User user = (User) currentUser.getPrincipal();
            if(user.getActivityStatus().equals(SysCode.USER_ACTIVITY_STATUS.WJH)){
                return JSONUtil.error("账号处于未激活状态，请前往邮箱激活");
            }
            Session session = currentUser.getSession();
            session.setAttribute("username", username);
            return JSONUtil.result(true,user,null);
            //return "/index";
        }catch (UnknownAccountException e)
        {
            e.printStackTrace();
            msg = "账号不存在";
        }
        catch (IncorrectCredentialsException e)
        {
            msg = "密码不正确";
        }
        catch (AuthenticationException e) {
            e.printStackTrace();
            msg="用户验证失败";
        }

        return JSONUtil.error(msg);
    }


    @Override
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

    @Override
    public void register(User user) {
        userService.add(user);
    }

    @Override
    public boolean checkCode(String code) {
        User user = userService.selectByActivityCode(code);
        if(user !=null){
            user.setActivityStatus(SysCode.USER_ACTIVITY_STATUS.YJH);
            //把code验证码清空，已经不需要了
            user.setActivityCode("");
            userMapper.updateById(user);
            msgService.addMsgWhenRegister(user);
            return true;
        }
        return false;
    }
}
