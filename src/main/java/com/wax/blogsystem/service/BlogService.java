package com.wax.blogsystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wax.blogsystem.domain.Blog;

import java.util.List;

public interface BlogService {

    IPage<Blog> selectAll(Page<Blog> page);

    String saveOrUpdate(Blog blog);

    Blog selectById(String id);

    IPage<Blog> selectPageByUserId(Page<Blog> page, String userId);

    void addViewNum(Blog blog);

    int getTotalNum(String id);

    void addCommentNum(Blog blog);

    void subtractCommentNum(Blog blog);

    void addLikeNum(Blog blog);

    void subtractLikeNum(Blog blog);

    List<Blog> getTopViewList(Page<Blog> page);

    int getAllNum();

    IPage<Blog> getTenDaysTopLikeBlog(Page<Blog> page);

    IPage<Blog> get48HoursViewBlogList(Page<Blog> page);

    IPage<Blog> selectAll4Background(Page<Blog> page,Blog condition);

    void recommend(String id);

    void cancelRecommend(String id);

    IPage<Blog> getEditorRecommendBlog(Page<Blog> page);

    IPage<Blog> getMyCommentList(Page<Blog> page,String userId);

    IPage<Blog> getILikeList(Page<Blog> page,String userId);

    IPage<Blog> getMyFavoriteList(Page<Blog> page,String userId);

    IPage<Blog> getBlogByCategory(Page<Blog> page,String category);

    IPage<Blog> getReleasedList(Page<Blog> page,String userId);

    IPage<Blog> getDraftList(Page<Blog> page,String userId);

    void cancelRelease(String id);

    void release(String id);

}
