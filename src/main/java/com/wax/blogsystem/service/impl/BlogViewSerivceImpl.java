package com.wax.blogsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wax.blogsystem.common.SysCode;
import com.wax.blogsystem.domain.Blog;
import com.wax.blogsystem.domain.BlogView;
import com.wax.blogsystem.mapper.BlogViewMapper;
import com.wax.blogsystem.service.BlogService;
import com.wax.blogsystem.service.BlogViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BlogViewSerivceImpl implements BlogViewService {

    @Autowired
    private BlogViewMapper blogViewMapper;

    @Autowired
    private BlogService blogService;


    @Override
    public void addBlogView(BlogView blogView) {
        BlogView check = selectByUserAndBlog(blogView.getUserId(),blogView.getBlogId());
        //无点赞记录 直接点赞
        if(check==null){
            blogView.setCreateTime(new Date());
            blogView.setDelTag(SysCode.DELTAG.WSC);
            blogViewMapper.insert(blogView);
            blogService.addViewNum(blogService.selectById(blogView.getBlogId()));
        }
    }

    @Override
    public BlogView selectByUserAndBlog(String userId,String blogId) {
        QueryWrapper<BlogView> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("blog_id",blogId);
        queryWrapper.eq("del_tag", SysCode.DELTAG.WSC);
        return blogViewMapper.selectOne(queryWrapper);
    }


}
