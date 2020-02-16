package com.wax.blogsystem.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_comment")
public class Comment {

    @TableId(value = "ID",type = IdType.ASSIGN_UUID)
    private String id;

    @TableField(value = "CONTENT",exist = true)
    private String content;

    @TableField(value = "SENDER",exist = true)
    private String sender;

    @TableField(value = "RECEIVER",exist = true)
    private String receiver;

    @TableField(value = "BLOG_ID",exist = true)
    private String blogId;

    @TableField(value = "CREATE_TIME",exist = true)
    private String createTime;

    @TableField(value = "DEL_TAG",exist = true)
    private String delTag;

}
