package com.wax.blogsystem.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName(value = "sys_blog_category")
@Data
public class BlogCategory {

    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;

    @TableField(value = "name",exist = true)
    private String name;

    @TableField(value = "del_tag",exist = true)
    private String delTag;

    @TableField(value = "create_time",exist = true)
    private Date createTime;



}
