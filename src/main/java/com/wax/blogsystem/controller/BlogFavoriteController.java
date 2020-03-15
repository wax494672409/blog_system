package com.wax.blogsystem.controller;

import com.wax.blogsystem.common.JSONUtil;
import com.wax.blogsystem.domain.BlogFavorite;
import com.wax.blogsystem.service.BlogFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/favorite")
public class BlogFavoriteController {

    @Autowired
    private BlogFavoriteService blogFavoriteService;

    @PostMapping("/favorite.do")
    @ResponseBody
    public String favorite(BlogFavorite blogFavorite){
        blogFavoriteService.favorite(blogFavorite);
        return JSONUtil.success("收藏成功");
    }

    @PostMapping("/cancelFavorite.do")
    @ResponseBody
    public String cancelFavorite(BlogFavorite blogFavorite){
        blogFavoriteService.cancelFavorite(blogFavorite);
        return JSONUtil.success("已取消收藏");
    }

}
