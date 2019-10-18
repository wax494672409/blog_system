package com.wax.blogsystem.common;

public class SysCode {

    public static interface TIPMESSAGE{
        //保存成功
        String SAVESUCCESS = "保存成功";
        //修改成功
        String UPDATESUCCESS = "修改成功";

    }

    public static interface ROLECODE{
        //管理员
        String ADMIN = "admin";
        //普通用户
        String PTYH = "user1";
        //博客用户
        String BKYH = "user2";
    }


    public static interface DELTAG{
        //已删除
        String YSC = "0";
        //未删除
        String WSC = "1";
    }

}