package com.wax.blogsystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wax.blogsystem.domain.User;

import java.util.List;

public interface UserService {

    void insertSelective(User user);


    void deleteByPrimaryKey(String id);

    void deleteByPrimaryKeys(String ids);


    User selectOne (QueryWrapper queryWrapper);

    IPage<User> selectPage(Page<User> page,User user);

}
