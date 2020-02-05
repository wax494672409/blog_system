package com.wax.blogsystem.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wax.blogsystem.common.Encript;
import com.wax.blogsystem.mapper.UserMapper;
import com.wax.blogsystem.domain.User;
import com.wax.blogsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void insertSelective(User user) {
        user.setPassword(Encript.md5(user.getPassword()));
        userMapper.insertSelective(user);
    }

    @Override
    public void deleteByPrimaryKey(String id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void deleteByPrimaryKeys(String ids) {
        String[] id = ids.split(",");
        for(int i=0;i<id.length;i++){
            deleteByPrimaryKey(id[i]);
        }
    }

    @Override
    public User selectOne(QueryWrapper queryWrapper) {
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public IPage<User> selectPage(Page<User> page, User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(user.getUsername())){
            queryWrapper.like("username", user.getUsername());
        }
        if(!StringUtils.isEmpty(user.getRoleCode())){
            queryWrapper.eq("role_code",user.getRoleCode());
        }
        return userMapper.selectPage(page,queryWrapper);
    }
}
