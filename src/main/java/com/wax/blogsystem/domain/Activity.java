package com.wax.blogsystem.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName(value = "sys_activity")
@Data
public class Activity {

    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;

    @TableField(value = "category",exist = true)
    private String category;

    @TableField(value = "blog_title",exist = true)
    private String blogTitle;

    @TableField(value = "blog_id",exist = true)
    private String blogId;

    @TableField(value = "content",exist = true)
    private String content;

    @TableField(value = "del_tag",exist = true)
    private String delTag;

    @TableField(value = "create_time",exist = true)
    private Date createTime;

    @TableField(value = "user_id",exist = true)
    private String userId;

}
