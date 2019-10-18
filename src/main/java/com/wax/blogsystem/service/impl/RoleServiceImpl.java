package com.wax.blogsystem.service.impl;

import com.wax.blogsystem.dao.RoleMapper;
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
        return roleMapper.selectAll();
    }
}
