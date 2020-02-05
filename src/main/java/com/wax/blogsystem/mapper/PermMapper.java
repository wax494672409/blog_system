package com.wax.blogsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wax.blogsystem.domain.Perm;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PermMapper extends BaseMapper<Perm> {
    int deleteByPrimaryKey(String id);

    int insert(Perm record);

    int insertSelective(Perm record);

    Perm selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Perm record);

    int updateByPrimaryKey(Perm record);

    List<Perm> selectByRoleId(String roleId);

}