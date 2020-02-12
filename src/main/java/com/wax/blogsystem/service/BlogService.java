package com.wax.blogsystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wax.blogsystem.domain.Blog;

import java.util.List;

public interface BlogService {

    public List<Blog> selectAll();

    String saveOrUpdate(Blog blog);

    Blog selectById(String id);

    IPage<Blog> selectPageByUserId(Page<Blog> page, String userId);

    void addViewNum(Blog blog);

    int getTotalNum(String id);

}
