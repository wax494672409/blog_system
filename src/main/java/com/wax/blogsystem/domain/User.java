package com.wax.blogsystem.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user")
public class User {

    @TableId(value = "ID",type = IdType.ASSIGN_UUID)
    private String id;

    @TableField(value = "USERNAME",exist = true)
    private String username;

    @TableField(value = "PASSWORD",exist = true)
    private String password;

    @TableField(value = "NAME",exist = true)
    private String name;

    @TableField(value = "ROLE_CODE",exist = true)
    private String roleCode;

    @TableField(value = "DEL_TAG",exist = true)
    private String delTag;

    @TableField(value = "CREATE_TIME",exist = true)
    private Date createTime;

    @TableField(value = "follow_num",exist = true)
    private int followNum;

    @TableField(value = "pic_url",exist = true)
    private String picUrl;

}