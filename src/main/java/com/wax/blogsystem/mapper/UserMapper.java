package com.wax.blogsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wax.blogsystem.common.pojo.Page;
import com.wax.blogsystem.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    User selectByUsername(String username);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectByCondition(@Param("user") User user, @Param("page") Page page);

    int selectByConditionCount(User user);
}