package com.wax.blogsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wax.blogsystem.common.SysCode;
import com.wax.blogsystem.domain.BlogLike;
import com.wax.blogsystem.mapper.BlogLikeMapper;
import com.wax.blogsystem.service.BlogLikeService;
import com.wax.blogsystem.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class BlogLikeServiceImpl implements BlogLikeService {

    @Autowired
    private BlogLikeMapper blogLikeMapper;

    @Autowired
    private BlogService blogService;


    @Override
    @Transactional
    public String addBlogLike(BlogLike blogLike) {
        BlogLike check = selectByUserAndBlog(blogLike.getUserId(),blogLike.getBlogId());
        //无点赞记录 直接点赞
        if(check==null){
            blogLike.setCreateTime(new Date());
            blogLike.setDelTag(SysCode.DELTAG.WSC);
            blogLikeMapper.insert(blogLike);
            blogService.addLikeNum(blogService.selectById(blogLike.getBlogId()));
            return SysCode.TIPMESSAGE.LIKESUCCESS;
        }
        else {
            return SysCode.TIPMESSAGE.LIKED;
        }
    }

    @Override
    public BlogLike selectByUserAndBlog(String userId,String blogId){
        QueryWrapper<BlogLike> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("blog_id",blogId);
        queryWrapper.eq("del_tag",SysCode.DELTAG.WSC);
        return blogLikeMapper.selectOne(queryWrapper);
    }


}
