package com.wax.blogsystem.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Clob;
import java.util.Date;

@Data
@TableName("sys_blog")
public class Blog {

    /**
     * 主键
     */
    @TableId(value = "ID",type = IdType.ASSIGN_UUID)
    public String id;

    /**
     * 标题
     */
    @TableField(value = "TITLE",exist = true)
    public String title;

    /**
     * 作者
     */
    @TableField(value = "AUTHOR",exist = true)
    public String author;

    /**
     * 作者名称
     */
    @TableField(value = "AUTHOR_NAME",exist = true)
    public String authorName;

    /**
     * 内容
     */
    @TableField(value = "CONTENT",exist = true)
    public String content;

    /**
     * 摘要
     */
    @TableField(value = "SUMMARY",exist = true)
    public String summary;

    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME",exist = true)
    public Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "UPDATE_TIME",exist = true)
    public Date updateTime;

    /**
     * 发布时间
     */
    @TableField(value = "RELEASE_TIME",exist = true)
    public Date releaseTime;

    /**
     * 状态
     */
    @TableField(value = "STATUS",exist = true)
    public String status;

    /**
     * 删除标识
     */
    @TableField(value = "DEL_TAG",exist = true)
    public String delTag;

    /**
     * 收藏数
     */
    @TableField(value = "COLLECT_NUM",exist = true)
    public int collectNum;

    /**
     * 评论数
     */
    @TableField(value = "COMMENT_NUM",exist = true)
    public int commentNum;

    /**
     * 阅读数
     */
    @TableField(value = "VIEW_NUM",exist = true)
    public int viewNum;

}
