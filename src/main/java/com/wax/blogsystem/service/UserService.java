package com.wax.blogsystem.service;

import com.wax.blogsystem.common.pojo.Page;
import com.wax.blogsystem.domain.User;

import java.util.List;

public interface UserService {

    void insertSelective(User user);


    List<User> selectByCondition(User user, Page page);


    int selectByConditionCount(User user);

    User selectByPrimaryKey(String id);

    void updateByPrimaryKeySelective(User user);

}
