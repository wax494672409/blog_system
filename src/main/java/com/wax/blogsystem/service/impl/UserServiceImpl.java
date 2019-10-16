package com.wax.blogsystem.service.impl;

import com.wax.blogsystem.common.pojo.Page;
import com.wax.blogsystem.dao.UserMapper;
import com.wax.blogsystem.domain.User;
import com.wax.blogsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public void insertSelective(User user) {
        userMapper.insertSelective(user);
    }


    @Override
    public List<User> selectByCondition(User user, Page page) {
       return  userMapper.selectByCondition(user,page);
    }

    @Override
    public int selectByConditionCount(User user) {
        return userMapper.selectByConditionCount(user);
    }


    @Override
    public User selectByPrimaryKey(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateByPrimaryKeySelective(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }
}
