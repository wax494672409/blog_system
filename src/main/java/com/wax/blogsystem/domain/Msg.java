package com.wax.blogsystem.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sys_msg")
public class Msg {

    @TableId(value = "id")
    private String id;

    @TableField(value = "title",exist = true)
    private String title;

    @TableField(value = "content",exist = true)
    private String content;

    @TableField(value = "type",exist = true)
    private String type;

    @TableField(value = "receiver",exist = true)
    private String receiver;

    @TableField(value = "receiver_name",exist = true)
    private String receiverName;

    @TableField(value = "sender",exist = true)
    private String sender;

    @TableField(value = "sender_name",exist = true)
    private String senderName;

    @TableField(value = "create_time",exist = true)
    private Date createTime;

    @TableField(value = "del_tag_se",exist = true)
    private String delTagSe;

    @TableField(value = "del_tag_re",exist = true)
    private String delTagRe;

    @TableField(value = "is_read",exist = true)
    private String isRead;

    @TableField(exist = false)
    private String picUrl;

}
