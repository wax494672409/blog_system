package com.wax.blogsystem.service.impl;

import com.wax.blogsystem.common.SysCode;
import com.wax.blogsystem.domain.BlogLike;
import com.wax.blogsystem.mapper.BlogLikeMapper;
import com.wax.blogsystem.service.BlogLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BlogLikeServiceImpl implements BlogLikeService {

    @Autowired
    private BlogLikeMapper blogLikeMapper;


    @Override
    public void addBlogLike(BlogLike blogLike) {
        blogLike.setCreateTime(new Date());
        blogLike.setDelTag(SysCode.DELTAG.WSC);
        blogLikeMapper.insert(blogLike);
    }
}
