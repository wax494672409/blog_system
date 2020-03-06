package com.wax.blogsystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wax.blogsystem.domain.User;

import java.util.List;

public interface UserService {

    String saveOrUpdate(User user);

    void deleteByPrimaryKeys(String ids);

    User selectOne (QueryWrapper queryWrapper);

    IPage<User> selectPage(Page<User> page,User user);

    User selectById(String id);

    IPage<User> topBlogUserList(Page<User> page);

}
