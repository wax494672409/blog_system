package com.wax.blogsystem.controller;

import com.wax.blogsystem.common.JSONUtil;
import com.wax.blogsystem.domain.BlogLike;
import com.wax.blogsystem.service.BlogLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/blogLike")
public class BlogLikeController {

    @Autowired
    private BlogLikeService blogLikeService;


    @PostMapping(value = "/addBlogLike.do")
    @ResponseBody
    public String addBlogLike(BlogLike blogLike){
        return JSONUtil.success(blogLikeService.addBlogLike(blogLike));
    }


    @PostMapping(value = "/cancelLike.do")
    @ResponseBody
    public String cancelLike(BlogLike blogLike){
        blogLikeService.cancelLike(blogLike);
        return JSONUtil.success("已取消点赞");
    }

}
