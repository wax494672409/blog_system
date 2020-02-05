package com.wax.blogsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wax.blogsystem.domain.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    int deleteByPrimaryKey(String id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectAll();

    Role selectByCode(String code);

}