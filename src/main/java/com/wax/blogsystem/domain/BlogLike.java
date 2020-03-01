package com.wax.blogsystem.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName(value = "sys_blog_like")
@Data
public class BlogLike {

    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;

    @TableField(value = "user_id",exist = true)
    private String userId;

    @TableField(value = "blog_id",exist = true)
    private String blogId;

    @TableField(value = "create_time",exist = true)
    private Date createTime;

    @TableField(value = "del_tag",exist = true)
    private String delTag;

    @TableField(exist = false)
    private Blog blog;

}
