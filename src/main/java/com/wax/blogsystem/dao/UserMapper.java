package com.wax.blogsystem.dao;

import com.wax.blogsystem.common.pojo.Page;
import com.wax.blogsystem.domain.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectByCondition(User user, Page page);

    int selectByConditionCount(User user);
}