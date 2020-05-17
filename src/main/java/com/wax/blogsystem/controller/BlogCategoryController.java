package com.wax.blogsystem.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wax.blogsystem.common.JSONUtil;
import com.wax.blogsystem.domain.BlogCategory;
import com.wax.blogsystem.service.BlogCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/blogCategory")
public class BlogCategoryController {

    @Autowired
    private BlogCategoryService blogCategoryService;

    @RequestMapping(value = "/goListPage.do")
    public String goBlogCategoryListPage(){
        return "/background/blogCategory/list";
    }

    @RequestMapping(value = "/goAddPage.do")
    public String goAddPage(String id,Model model){
        if(StringUtils.isEmpty(id)){
            BlogCategory blogCategory = new BlogCategory();
            model.addAttribute("blogCategory",blogCategory);
            model.addAttribute("state","add");
        }
        else{
            BlogCategory blogCategory = blogCategoryService.selectById(id);
            model.addAttribute("blogCategory",blogCategory);
            model.addAttribute("state","update");
        }
        return "/background/blogCategory/add";
    }


    @PostMapping(value = "/selectAll4Front.do")
    public String selectAll4Front(Page<BlogCategory> page, Model model){
        IPage<BlogCategory> ipage = blogCategoryService.selectAll(page,null);
        model.addAttribute("categoryList",ipage.getRecords());
        return "/front/home::category_list";
    }


    @GetMapping(value = "/selectAll.do")
    @ResponseBody
    public String selectAll(Page<BlogCategory> page,String name){
        IPage<BlogCategory> ipage = blogCategoryService.selectAll(page,name);
        return JSONUtil.layUITable(ipage.getRecords(),ipage.getTotal());
    }

    @PostMapping(value = "/saveOrUpdate.do")
    public String saveOrUpdate(BlogCategory blogCategory){
        blogCategoryService.saveOrUpdate(blogCategory);
        return JSONUtil.success("添加成功");
    }


}
