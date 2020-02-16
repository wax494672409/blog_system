package com.wax.blogsystem.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wax.blogsystem.common.JSONUtil;
import com.wax.blogsystem.common.SysCode;
import com.wax.blogsystem.domain.Blog;
import com.wax.blogsystem.domain.User;
import com.wax.blogsystem.mapper.BlogMapper;
import com.wax.blogsystem.service.BlogService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    public BlogMapper blogMapper;

    @Override
    public List<Blog> selectAll() {
        return blogMapper.selectList(null);
    }

    @Override
    public String saveOrUpdate(Blog blog) {
        User user = (User)SecurityUtils.getSubject().getPrincipal();
        if(StringUtils.isEmpty(blog.getId())){
            blog.setCreateTime(new Date());
            blog.setAuthor(user.getId());
            blog.setAuthorName(user.getName());
            blog.setCollectNum(SysCode.NUM.ZERO);
            blog.setCommentNum(SysCode.NUM.ZERO);
            blog.setDelTag(SysCode.DELTAG.WSC);
            if(blog.getStatus().equals(SysCode.BLOG_STATUS.RELEASED)){
                blog.setReleaseTime(new Date());
            }
            blogMapper.insert(blog);
            return JSONUtil.success(SysCode.TIPMESSAGE.SAVESUCCESS);
        }
        else{
            blog.setUpdateTime(new Date());
            blogMapper.updateById(blog);
            return JSONUtil.success(SysCode.TIPMESSAGE.UPDATESUCCESS);
        }
    }

    @Override
    public Blog selectById(String id) {
        QueryWrapper<Blog> queryWrapper = new QueryWrapper();
        queryWrapper.eq("id",id);
        return blogMapper.selectOne(queryWrapper);
    }

    @Override
    public IPage<Blog> selectPageByUserId(Page<Blog> page, String userId) {
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author",userId);
        queryWrapper.eq("status",SysCode.BLOG_STATUS.RELEASED);
        queryWrapper.eq("del_tag",SysCode.DELTAG.WSC);
        queryWrapper.orderByDesc("release_time");
        return blogMapper.selectPage(page,queryWrapper);
    }

    @Override
    public void addViewNum(Blog blog) {
        blog.setViewNum(blog.getViewNum()+1);
        blogMapper.updateById(blog);
    }

    @Override
    public int getTotalNum(String id) {
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author",id);
        queryWrapper.eq("status",SysCode.BLOG_STATUS.RELEASED);
        queryWrapper.eq("del_tag",SysCode.DELTAG.WSC);
        return blogMapper.selectCount(queryWrapper);
    }


}
