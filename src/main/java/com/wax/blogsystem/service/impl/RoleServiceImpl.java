package com.wax.blogsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wax.blogsystem.mapper.RoleMapper;
import com.wax.blogsystem.domain.Role;
import com.wax.blogsystem.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;


    @Override
    public List<Role> selectAll() {
        return roleMapper.selectList(null);
    }

    @Override
    public Role selectOneByCode(String code) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code",code);
        return roleMapper.selectOne(queryWrapper);
    }


}
