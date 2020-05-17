package com.wax.blogsystem.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wax.blogsystem.common.SysCode;
import com.wax.blogsystem.domain.BlogCategory;
import com.wax.blogsystem.mapper.BlogCategoryMapper;
import com.wax.blogsystem.service.BlogCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BlogCategoryServiceImpl implements BlogCategoryService {

    @Autowired
    private BlogCategoryMapper blogCategoryMapper;


    @Override
    public void saveOrUpdate(BlogCategory blogCategory) {
        if(StringUtils.isEmpty(blogCategory.getId())){
            //add
            blogCategory.setCreateTime(new Date());
            blogCategory.setDelTag(SysCode.DELTAG.WSC);
            blogCategoryMapper.insert(blogCategory);
        }
        else {
            //update
            blogCategoryMapper.updateById(blogCategory);
        }
    }

    @Override
    public IPage<BlogCategory> selectAll(Page<BlogCategory> page,String name) {
        QueryWrapper<BlogCategory> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)){
            queryWrapper.like("name",name);
        }
        queryWrapper.eq("del_tag", SysCode.DELTAG.WSC);
        return blogCategoryMapper.selectPage(page,queryWrapper);
    }

    @Override
    public BlogCategory selectById(String id) {
        QueryWrapper<BlogCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        queryWrapper.eq("del_tag",SysCode.DELTAG.WSC);
        return blogCategoryMapper.selectOne(queryWrapper);
    }
}
