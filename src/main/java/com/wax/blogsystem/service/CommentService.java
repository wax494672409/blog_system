package com.wax.blogsystem.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wax.blogsystem.domain.Comment;

public interface CommentService {

    void addComment(Comment comment);

    IPage<Comment> getCommentByBlogId(Page<Comment> page,String blogId);

    int getTotalNum(String blogId);

}
