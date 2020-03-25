package com.wax.blogsystem.common;

public class SysCode {

    public static interface TIPMESSAGE{
        //保存成功
        String SAVESUCCESS = "保存成功";
        //修改成功
        String UPDATESUCCESS = "修改成功";
        //评论成功
        String COMMENTSUCCESS = "评论成功";
        //点赞成功
        String LIKESUCCESS = "点赞成功";
        //已点赞
        String LIKED = "您已经点赞过";

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

    public static interface NUM{

        int ZERO = 0;

        int ONE = 1;

        int TWO = 2;

        int THREE = 3;

    }

    public static interface BLOG_STATUS{

        /**
         * 草稿
         */
        String DRAFT = "draft";

        /**
         * 已发表
         */
        String RELEASED = "released";

    }


    public static interface ACTIVITY_CATEGORY{

        /**
         * 发表博客
         */
        String BLOG = "发表博客";

        /**
         * 发表评论
         */
        String COMMENT = "评论博客";


    }

    public static interface USER_ACTIVITY_STATUS{

        //未激活
        String WJH = "0";

        //已激活
        String YJH = "1";

    }

    public static interface IS_RECOMMEND{

        String YES = "1";

        String NO = "0";

    }


    public static interface IS_READ{

        String YES = "1";

        String NO = "0";

    }

}
