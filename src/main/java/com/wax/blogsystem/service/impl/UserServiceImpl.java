package com.wax.blogsystem.service.impl;

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
    public void insert(User user) {
        userMapper.insertSelective(user);
    }


    @Override
    public List<User> selectByCondition(User user) {
       return  userMapper.selectByCondition(user);
    }

    @Override
    public int selectByConditionCount(User user) {
        return userMapper.selectByConditionCount(user);
    }
}
