package com.wax.blogsystem.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName(value = "sys_follow")
@Data
public class Follow {

    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;

    @TableField(value = "follower",exist = true)
    private String follower;

    @TableField(value = "follower_name",exist = true)
    private String followerName;

    @TableField(value = "follower_pic_url",exist = true)
    private String followerPicUrl;

    @TableField(value = "be_follower",exist = true)
    private String beFollower;

    @TableField(value = "be_follower_name",exist = true)
    private String beFollowerName;

    @TableField(value = "be_follower_pic_url",exist = true)
    private String beFollowerPicUrl;

    @TableField(value = "del_tag",exist = true)
    private String delTag;

    @TableField(value = "create_time",exist = true)
    private Date createTime;

}
