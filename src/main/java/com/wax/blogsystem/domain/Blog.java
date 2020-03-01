package com.wax.blogsystem.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.annotations.Select;

import java.beans.Transient;
import java.sql.Clob;
import java.util.Date;

@Data
@TableName("sys_blog")
public class Blog {

    /**
     * 主键
     */
    @TableId(value = "ID",type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 标题
     */
    @TableField(value = "TITLE",exist = true)
    private String title;

    /**
     * 作者
     */
    @TableField(value = "AUTHOR",exist = true)
    private String author;

    /**
     * 作者名称
     */
    @TableField(value = "AUTHOR_NAME",exist = true)
    private String authorName;

    /**
     * 内容
     */
    @TableField(value = "CONTENT",exist = true)
    private String content;

    /**
     * 摘要
     */
    @TableField(value = "SUMMARY",exist = true)
    private String summary;

    /**
     * 标签
     */
    @TableField(value = "LABEL",exist = true)
    private String label;

    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME",exist = true)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "UPDATE_TIME",exist = true)
    private Date updateTime;

    /**
     * 发布时间
     */
    @TableField(value = "RELEASE_TIME",exist = true)
    private Date releaseTime;

    /**
     * 状态
     */
    @TableField(value = "STATUS",exist = true)
    private String status;

    /**
     * 删除标识
     */
    @TableField(value = "DEL_TAG",exist = true)
    private String delTag;

    /**
     * 收藏数
     */
    @TableField(value = "COLLECT_NUM",exist = true)
    private int collectNum;

    /**
     * 评论数
     */
    @TableField(value = "COMMENT_NUM",exist = true)
    private int commentNum;

    /**
     * 阅读数
     */
    @TableField(value = "VIEW_NUM",exist = true)
    private int viewNum;

    /**
     * 点赞数
     */
    @TableField(value = "LIKE_NUM",exist = true)
    private int likeNum;

    /**
     * 作者头像
     */
    @TableField(exist = false)
    private String authorPicUrl;

}
