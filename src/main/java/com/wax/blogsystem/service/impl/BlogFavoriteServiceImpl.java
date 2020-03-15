package com.wax.blogsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wax.blogsystem.common.SysCode;
import com.wax.blogsystem.domain.BlogFavorite;
import com.wax.blogsystem.mapper.BlogFavoriteMapper;
import com.wax.blogsystem.service.BlogFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BlogFavoriteServiceImpl implements BlogFavoriteService {

    @Autowired
    private BlogFavoriteMapper blogFavoriteMapper;

    @Override
    public void favorite(BlogFavorite blogFavorite) {
        blogFavorite.setCreateTime(new Date());
        blogFavorite.setDelTag(SysCode.DELTAG.WSC);
        blogFavoriteMapper.insert(blogFavorite);
    }

    @Override
    public BlogFavorite selectByUserAndBlog(String userId,String blogId) {
        QueryWrapper<BlogFavorite> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("blog_id",blogId);
        queryWrapper.eq("del_tag", SysCode.DELTAG.WSC);
        return blogFavoriteMapper.selectOne(queryWrapper);
    }

    @Override
    public void cancelFavorite(BlogFavorite blogFavorite) {
        blogFavorite = selectByUserAndBlog(blogFavorite.getUserId(),blogFavorite.getBlogId());
        blogFavorite.setDelTag(SysCode.DELTAG.YSC);
        blogFavoriteMapper.updateById(blogFavorite);
    }
}
