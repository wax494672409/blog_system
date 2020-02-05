package com.wax.blogsystem.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.wax.blogsystem.common.Encript;
import com.wax.blogsystem.common.JSONUtil;
import com.wax.blogsystem.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public String login(String username, String password) {
        if(StringUtils.isEmpty(username))
        {
            return JSONUtil.error("用户名为空");
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
            Session session = currentUser.getSession();
            session.setAttribute("username", username);
            return JSONUtil.success();
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
}
