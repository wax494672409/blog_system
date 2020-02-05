package com.wax.blogsystem.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Clob;
import java.util.Date;

@Data
@TableName
public class Blog {

    /**
     * 主键
     */
    public String id;

    /**
     * 标题
     */
    public String title;

    /**
     * 作者
     */
    public String author;

    /**
     * 内容
     */
    public Clob content;

    /**
     * 创建时间
     */
    public Date createTime;

    /**
     * 发布时间
     */
    public Date releaseTime;

    /**
     * 状态
     */
    public String status;

    /**
     * 删除标识
     */
    public String delTag;

    /**
     * 收藏数
     */

    public int collectNum;

    /**
     * 评论数
     */
    public int commentNum;



}
