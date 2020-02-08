package com.wax.blogsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wax.blogsystem.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;


import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    /**
     *
     * @param ids
     */
    @Update({
            "<script>",
                "update",
                "user",
                "set del_tag = '0' ",
                "where id in",
                "<foreach collection='ids' item='id' open='(' separator=',' close=')'>",
                    "#{id}",
                "</foreach>",
            "</script>"
    })
    void updateBatchByIds(@Param("ids") String[] ids);


}