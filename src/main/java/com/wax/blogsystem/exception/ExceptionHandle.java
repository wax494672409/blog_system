package com.wax.blogsystem.exception;

import com.wax.blogsystem.common.JSONUtil;
import com.wax.blogsystem.common.SysCode;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle{

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String exceptiona(Exception ex){
        //shiro权限认证异常
        if(ex instanceof UnauthorizedException) {
            return JSONUtil.error("无权限,请申请相关权限");
        }
        if(ex instanceof UnauthenticatedException){
            return JSONUtil.error("请重新登录");
        }
        else {
            ex.printStackTrace();
        }
        return null;
    }
}