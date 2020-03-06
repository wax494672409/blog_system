package com.wax.blogsystem.service;

import com.wax.blogsystem.domain.BlogLike;

public interface BlogLikeService {

    String addBlogLike(BlogLike blogLike);

    /**
     * 根据用户id 博客id查询
     * @param userId
     * @param blogId
     * @return
     */
    BlogLike selectByUserAndBlog(String userId,String blogId);

}
