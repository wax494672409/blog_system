package com.wax.blogsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wax.blogsystem.common.SysCode;
import com.wax.blogsystem.domain.Activity;
import com.wax.blogsystem.domain.Blog;
import com.wax.blogsystem.domain.Comment;
import com.wax.blogsystem.mapper.CommentMapper;
import com.wax.blogsystem.service.ActivityService;
import com.wax.blogsystem.service.BlogService;
import com.wax.blogsystem.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    public BlogService blogService;

    @Autowired
    public ActivityService activityService;


    @Autowired
    public CommentMapper commentMapper;


    @Override
    @Transactional
    public void addComment(Comment comment) {
        comment.setCreateTime(new Date());
        comment.setDelTag(SysCode.DELTAG.WSC);
        commentMapper.insert(comment);
        Blog blog = blogService.selectById(comment.getBlogId());
        blogService.addCommentNum(blog);
        Activity activity = new Activity();
        activity.setBlogTitle(blog.getTitle());
        activity.setCategory(SysCode.ACTIVITY_CATEGORY.COMMENT);
        activity.setContent(comment.getContent());
        activity.setCreateTime(new Date());
        activity.setDelTag(SysCode.DELTAG.WSC);
        activity.setUserId(comment.getSender());
        activityService.addActivity(activity);
    }

    @Override
    public IPage<Comment> getCommentByBlogId(Page<Comment> page,String blogId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("blog_id",blogId);
        queryWrapper.eq("del_tag",SysCode.DELTAG.WSC);
        queryWrapper.orderByDesc("create_time");
        return commentMapper.selectPage(page,queryWrapper);
    }

    @Override
    public int getTotalNum(String blogId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("blog_id",blogId);
        queryWrapper.eq("del_tag",SysCode.DELTAG.WSC);
        return commentMapper.selectCount(queryWrapper);
    }


}
