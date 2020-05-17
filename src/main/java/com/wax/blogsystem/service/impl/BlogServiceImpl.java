package com.wax.blogsystem.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wax.blogsystem.common.JSONUtil;
import com.wax.blogsystem.common.SysCode;
import com.wax.blogsystem.domain.Activity;
import com.wax.blogsystem.domain.Blog;
import com.wax.blogsystem.domain.BlogEs;
import com.wax.blogsystem.domain.User;
import com.wax.blogsystem.mapper.BlogMapper;
import com.wax.blogsystem.service.ActivityService;
import com.wax.blogsystem.service.BlogEsService;
import com.wax.blogsystem.service.BlogService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    public BlogMapper blogMapper;

    @Autowired
    public ActivityService activityService;

    @Autowired
    private BlogEsService blogEsService;

    @Override
    public IPage<Blog> selectAll(Page<Blog> page) {
        return blogMapper.selectAll(page);
    }

    @Override
    public String saveOrUpdate(Blog blog) {
        User user = (User)SecurityUtils.getSubject().getPrincipal();
        if(StringUtils.isEmpty(blog.getId())){
            blog.setCreateTime(new Date());
            blog.setAuthor(user.getId());
            blog.setAuthorName(user.getUsername());
            blog.setCollectNum(SysCode.NUM.ZERO);
            blog.setCommentNum(SysCode.NUM.ZERO);
            blog.setLikeNum(SysCode.NUM.ZERO);
            blog.setDelTag(SysCode.DELTAG.WSC);
            blog.setIsRecommend(SysCode.IS_RECOMMEND.NO);
            if(blog.getStatus().equals(SysCode.BLOG_STATUS.RELEASED)){
                blog.setReleaseTime(new Date());
            }
            blogMapper.insert(blog);
            activityService.addActivity(addActivity(blog));
            BlogEs blogEs = new BlogEs();
            BeanUtils.copyProperties(blog,blogEs);
            blogEsService.addBlogEs(blogEs);
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
        return blogMapper.selectById(id);
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
        BlogEs blogEs = new BlogEs();
        BeanUtils.copyProperties(blog,blogEs);
        blogEsService.updateBlogEs(blogEs);
    }


    @Override
    public int getTotalNum(String id) {
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author",id);
        queryWrapper.eq("status",SysCode.BLOG_STATUS.RELEASED);
        queryWrapper.eq("del_tag",SysCode.DELTAG.WSC);
        return blogMapper.selectCount(queryWrapper);
    }

    @Override
    public void addCommentNum(Blog blog) {
        blog.setCommentNum(blog.getCommentNum()+1);
        blogMapper.updateById(blog);
        BlogEs blogEs = new BlogEs();
        BeanUtils.copyProperties(blog,blogEs);
        blogEsService.updateBlogEs(blogEs);
    }

    @Override
    public void subtractCommentNum(Blog blog) {
        blog.setCommentNum(blog.getCommentNum()-1);
        blogMapper.updateById(blog);
        BlogEs blogEs = new BlogEs();
        BeanUtils.copyProperties(blog,blogEs);
        blogEsService.updateBlogEs(blogEs);
    }

    @Override
    public void addLikeNum(Blog blog) {
        blog.setLikeNum(blog.getLikeNum()+1);
        blogMapper.updateById(blog);
        BlogEs blogEs = new BlogEs();
        BeanUtils.copyProperties(blog,blogEs);
        blogEsService.updateBlogEs(blogEs);
    }

    @Override
    public void subtractLikeNum(Blog blog) {
        blog.setLikeNum(blog.getLikeNum()-1);
        blogMapper.updateById(blog);
        BlogEs blogEs = new BlogEs();
        BeanUtils.copyProperties(blog,blogEs);
        blogEsService.updateBlogEs(blogEs);
    }

    @Override
    public List<Blog> getTopViewList(Page<Blog> page,String id) {
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(id)){
            queryWrapper.eq("author",id);
        }
        queryWrapper.eq("status",SysCode.BLOG_STATUS.RELEASED);
        queryWrapper.eq("del_tag",SysCode.DELTAG.WSC);
        queryWrapper.orderByDesc("view_num");
        return blogMapper.selectPage(page,queryWrapper).getRecords();
    }

    @Override
    public List<Blog> getTopLikeList(Page<Blog> page) {
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status",SysCode.BLOG_STATUS.RELEASED);
        queryWrapper.eq("del_tag",SysCode.DELTAG.WSC);
        queryWrapper.orderByDesc("like_num");
        return blogMapper.selectPage(page,queryWrapper).getRecords();
    }

    @Override
    public int getAllNum() {
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status",SysCode.BLOG_STATUS.RELEASED);
        queryWrapper.eq("del_tag",SysCode.DELTAG.WSC);
        return blogMapper.selectCount(queryWrapper);
    }

    @Override
    public IPage<Blog> getTenDaysTopLikeBlog(Page<Blog> page) {
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_tag",SysCode.DELTAG.WSC);
        queryWrapper.eq("status",SysCode.BLOG_STATUS.RELEASED);
        queryWrapper.apply(" (TIME_TO_SEC(TIMEDIFF(SYSDATE(),sys_blog.release_time)))/86400<=10 ");
        queryWrapper.orderByDesc("like_num");
        return blogMapper.selectPage(page,queryWrapper);
    }


    public Activity addActivity(Blog blog){
        Activity activity = new Activity();
        activity.setUserId(blog.getAuthor());
        activity.setDelTag(SysCode.DELTAG.WSC);
        activity.setCreateTime(new Date());
        activity.setContent(blog.getSummary());
        activity.setBlogTitle(blog.getTitle());
        activity.setCategory(SysCode.ACTIVITY_CATEGORY.BLOG);
        return activity;
    }

    @Override
    public IPage<Blog> get48HoursViewBlogList(Page<Blog> page) {
        return blogMapper.get48HoursViewBlogList(page);
    }

    @Override
    public IPage<Blog> selectAll4Background(Page<Blog> page, Blog condition) {
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_tag",SysCode.DELTAG.WSC);
        queryWrapper.eq("status",SysCode.BLOG_STATUS.RELEASED);
        if(!StringUtils.isEmpty(condition.getAuthorName())){
            queryWrapper.like("author_name",condition.getAuthorName());
        }
        if (!StringUtils.isEmpty(condition.getTitle())){
            queryWrapper.like("title",condition.getTitle());
        }
        queryWrapper.orderByDesc("release_time");
        return blogMapper.selectPage(page,queryWrapper);
    }

    @Override
    public void recommend(String id) {
        Blog blog = new Blog();
        blog.setId(id);
        blog.setIsRecommend(SysCode.IS_RECOMMEND.YES);
        blogMapper.updateById(blog);
    }

    @Override
    public void cancelRecommend(String id) {
        Blog blog = new Blog();
        blog.setId(id);
        blog.setIsRecommend(SysCode.IS_RECOMMEND.NO);
        blogMapper.updateById(blog);
    }

    @Override
    public IPage<Blog> getEditorRecommendBlog(Page<Blog> page) {
        return blogMapper.getEditorRecommendBlog(page);
    }

    @Override
    public IPage<Blog> getMyCommentList(Page<Blog> page,String userId) {
        return blogMapper.getMyCommentList(page,userId);
    }

    @Override
    public IPage<Blog> getILikeList(Page<Blog> page,String userId) {
        return blogMapper.getILikeList(page,userId);
    }

    @Override
    public IPage<Blog> getMyFavoriteList(Page<Blog> page, String userId) {
        return blogMapper.getMyFavoriteList(page,userId);
    }

    @Override
    public IPage<Blog> getBlogByCategory(Page<Blog> page, String category) {
        return blogMapper.getBlogByCategory(page,category);
    }

    @Override
    public IPage<Blog> getReleasedList(Page<Blog> page, String userId) {
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_tag",SysCode.DELTAG.WSC);
        queryWrapper.eq("author",userId);
        queryWrapper.eq("status",SysCode.BLOG_STATUS.RELEASED);
        queryWrapper.orderByDesc("release_time");
        return blogMapper.selectPage(page,queryWrapper);
    }

    @Override
    public IPage<Blog> getDraftList(Page<Blog> page, String userId) {
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_tag",SysCode.DELTAG.WSC);
        queryWrapper.eq("author",userId);
        queryWrapper.eq("status",SysCode.BLOG_STATUS.DRAFT);
        queryWrapper.orderByDesc("create_time");
        return blogMapper.selectPage(page,queryWrapper);
    }

    @Override
    public void cancelRelease(String id) {
        Blog blog = selectById(id);
        blog.setStatus(SysCode.BLOG_STATUS.DRAFT);
        blogMapper.updateById(blog);
    }

    @Override
    public void release(String id) {
        Blog blog = selectById(id);
        blog.setStatus(SysCode.BLOG_STATUS.RELEASED);
        blog.setReleaseTime(new Date());
        blogMapper.updateById(blog);
    }

}
