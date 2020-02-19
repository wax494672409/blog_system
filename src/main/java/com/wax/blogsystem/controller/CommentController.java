package com.wax.blogsystem.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wax.blogsystem.common.JSONUtil;
import com.wax.blogsystem.common.SysCode;
import com.wax.blogsystem.domain.Comment;
import com.wax.blogsystem.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    public CommentService commentService;



    @PostMapping(value = "/add.do")
    @ResponseBody
    public String addComment(Comment comment){
        commentService.addComment(comment);
        return JSONUtil.success(SysCode.TIPMESSAGE.COMMENTSUCCESS);
    }

    @PostMapping(value = "/getPage.do")
    public String getPage(Page<Comment> page, String blogId, Model model){
        IPage<Comment> commentIPage = commentService.getCommentByBlogId(page,blogId);
        model.addAttribute("comments",commentIPage.getRecords());
        model.addAttribute("commentTotalNum",commentIPage.getTotal());
        return "blog/blog_one::comment_list";
    }


}
