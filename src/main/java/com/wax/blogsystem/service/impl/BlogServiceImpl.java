package com.wax.blogsystem.service.impl;

import com.wax.blogsystem.domain.Blog;
import com.wax.blogsystem.mapper.BlogMapper;
import com.wax.blogsystem.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BlogServiceImpl implements BlogService {

    @Autowired
    public BlogMapper blogMapper;

    @Override
    public List<Blog> selectAll() {
        return blogMapper.selectList(null);
    }
}
