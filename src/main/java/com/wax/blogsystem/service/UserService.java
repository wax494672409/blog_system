package com.wax.blogsystem.service;

import com.wax.blogsystem.common.pojo.Page;
import com.wax.blogsystem.domain.User;

import java.util.List;

public interface UserService {

    void insertSelective(User user);


    List<User> selectByCondition(User user, Page page);

    void deleteByPrimaryKey(String id);

    void deleteByPrimaryKeys(String ids);

    int selectByConditionCount(User user);

    User selectByPrimaryKey(String id);

    User selectByUsername(String username);

    void updateByPrimaryKeySelective(User user);

}
