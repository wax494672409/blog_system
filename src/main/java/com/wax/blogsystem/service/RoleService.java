package com.wax.blogsystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wax.blogsystem.domain.Role;

import java.util.List;

public interface RoleService {

    List<Role> selectAll();


    Role selectOneByCode(String code);

}
