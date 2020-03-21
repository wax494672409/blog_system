package com.wax.blogsystem.controller;

import com.wax.blogsystem.common.JSONUtil;
import com.wax.blogsystem.domain.BlogEs;
import com.wax.blogsystem.service.BlogEsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/blogEs")
public class BlogEsController {

    @Autowired
    private BlogEsService blogEsService;

    @RequestMapping(value = "/allSearch.do")
    @ResponseBody
    public String searchAll(String str, Pageable pageable){
        Page<BlogEs> page =blogEsService.allSearch(str,pageable);
        return JSONUtil.result(true,page.getContent(),"");
    }

    @RequestMapping(value = "/addOne.do")
    @ResponseBody
    public String addOne(){
        BlogEs blogEs = new BlogEs();
        blogEsService.addBlogEs(blogEs);
        return JSONUtil.success();
    }

}
