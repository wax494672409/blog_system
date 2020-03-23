package com.wax.blogsystem.controller;

import com.wax.blogsystem.common.JSONUtil;
import com.wax.blogsystem.domain.BlogEs;
import com.wax.blogsystem.domain.User;
import com.wax.blogsystem.service.BlogEsService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping(value = "/blogEs")
public class BlogEsController {

    @Autowired
    private BlogEsService blogEsService;

    @RequestMapping(value = "/goAllSearch.do")
    public String goAllSearch(Model model){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("loginUser",user);
        return "/search/allSearch";
    }

    @RequestMapping(value = "/goSearchList.do")
    public String goSearchList(Model model, String str){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("loginUser",user);
        model.addAttribute("searchStr",str);
        return "/search/list";
    }

    @PostMapping(value = "/allSearch.do")
    public String searchAll(String str, Pageable pageable,Model model,
                            Integer likeLimit, Integer viewLimit, Date dateLimit){
        Page<BlogEs> page =blogEsService.allSearch(str,pageable,likeLimit,viewLimit,dateLimit);
        model.addAttribute("searchList",page.getContent());
        model.addAttribute("searchTotalNum",page.getTotalElements());
        return "search/list::search_list";
    }

    @RequestMapping(value = "/addOne.do")
    @ResponseBody
    public String addOne(){
        BlogEs blogEs = new BlogEs();
        blogEsService.addBlogEs(blogEs);
        return JSONUtil.success();
    }

}
