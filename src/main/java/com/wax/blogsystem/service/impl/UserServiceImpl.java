package com.wax.blogsystem.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wax.blogsystem.common.Encript;
import com.wax.blogsystem.common.JSONUtil;
import com.wax.blogsystem.common.SysCode;
import com.wax.blogsystem.mapper.UserMapper;
import com.wax.blogsystem.domain.User;
import com.wax.blogsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String saveOrUpdate(User user) {
        if(StringUtils.isEmpty(user.getId())){
            user.setDelTag(SysCode.DELTAG.WSC);
            user.setRoleCode(SysCode.ROLECODE.PTYH);
            user.setCreateTime(new Date());
            user.setPassword(Encript.md5(user.getPassword()));
            userMapper.insert(user);
            return JSONUtil.success(SysCode.TIPMESSAGE.SAVESUCCESS);
        }
        else {
            userMapper.updateById(user);
            return JSONUtil.success(SysCode.TIPMESSAGE.UPDATESUCCESS);
        }
    }

    @Override
    @Transactional
    public void deleteByPrimaryKeys(String ids) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        String[] id = ids.split(",");
        userMapper.updateBatchByIds(id);
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
        queryWrapper.eq("del_tag", SysCode.DELTAG.WSC);
        queryWrapper.orderByDesc("create_time");
        return userMapper.selectPage(page,queryWrapper);
    }

    @Override
    public User selectById(String id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        return userMapper.selectOne(queryWrapper);
    }
}
