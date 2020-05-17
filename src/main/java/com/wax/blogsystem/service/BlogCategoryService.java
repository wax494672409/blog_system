package com.wax.blogsystem.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wax.blogsystem.domain.BlogCategory;

public interface BlogCategoryService {

    void saveOrUpdate(BlogCategory blogCategory);

    IPage<BlogCategory> selectAll(Page<BlogCategory> page,String name);

    BlogCategory selectById(String id);
}
