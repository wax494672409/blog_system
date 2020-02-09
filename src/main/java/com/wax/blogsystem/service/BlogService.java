package com.wax.blogsystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wax.blogsystem.domain.Blog;

import java.util.List;

public interface BlogService {

    public List<Blog> selectAll();

    String saveOrUpdate(Blog blog);

    Blog selectById(String id);

    List<Blog> selectByUserId(String userId);
}
