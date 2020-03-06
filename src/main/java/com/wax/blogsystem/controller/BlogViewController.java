package com.wax.blogsystem.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wax.blogsystem.common.JSONUtil;
import com.wax.blogsystem.domain.BlogView;
import com.wax.blogsystem.service.BlogViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/blogView")
public class BlogViewController {

    @Autowired
    private BlogViewService blogViewService;


    @PostMapping(value = "/addBlogView.do")
    @ResponseBody
    public String addBlogView(BlogView blogView){
        blogViewService.addBlogView(blogView);
        return JSONUtil.success();
    }




}
