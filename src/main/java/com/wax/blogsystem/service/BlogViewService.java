package com.wax.blogsystem.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wax.blogsystem.domain.BlogView;

public interface BlogViewService {


    void addBlogView(BlogView blogView);


    BlogView selectByUserAndBlog(String userId,String blogId);


}
