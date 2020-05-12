package com.wax.blogsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wax.blogsystem.domain.Msg;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface MsgMapper extends BaseMapper<Msg> {

    @Select("SELECT sys_msg.*,user.pic_url `picUrl` FROM sys_msg,user " +
            "WHERE sys_msg.sender=user.id " +
            " and sys_msg.id = #{id} ")
    Msg selectById(@Param("id") String id);

}
