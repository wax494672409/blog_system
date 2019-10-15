package com.wax.blogsystem.service;

import com.wax.blogsystem.domain.User;

import java.util.List;

public interface UserService {

    void insert(User user);


    List<User> selectByCondition(User user);


    int selectByConditionCount(User user);

}
