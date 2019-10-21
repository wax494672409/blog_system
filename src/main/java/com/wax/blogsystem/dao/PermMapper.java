package com.wax.blogsystem.dao;

import com.wax.blogsystem.domain.Perm;

import java.util.List;

public interface PermMapper {
    int deleteByPrimaryKey(String id);

    int insert(Perm record);

    int insertSelective(Perm record);

    Perm selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Perm record);

    int updateByPrimaryKey(Perm record);

    List<Perm> selectByRoleId(String roleId);

}