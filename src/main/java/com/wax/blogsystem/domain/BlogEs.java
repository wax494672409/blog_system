package com.wax.blogsystem.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

@Document(indexName = "blog",type = "doc",shards = 1,replicas = 0,refreshInterval = "-1")
@Data
public class BlogEs implements Serializable {

    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 标题
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title;

    /**
     * 作者
     */
    @Field(type = FieldType.Text)
    private String author;

    /**
     * 作者名称
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String authorName;

    /**
     * 内容
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String content;

    /**
     * 摘要
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String summary;

    /**
     * 创建时间
     */
    @Field(type = FieldType.Date)
    private Date createTime;

    /**
     * 更新时间
     */
    @Field(type = FieldType.Date)
    private Date updateTime;

    /**
     * 发布时间
     */
    @Field(type = FieldType.Date)
    private Date releaseTime;

    /**
     * 状态
     */
    @Field(type = FieldType.Text)
    private String status;

    /**
     * 删除标识
     */
    @Field(type = FieldType.Text)
    private String delTag;

    /**
     * 收藏数
     */
    @Field(type = FieldType.Integer)
    private int collectNum;

    /**
     * 评论数
     */
    @Field(type = FieldType.Integer)
    private int commentNum;

    /**
     * 阅读数
     */
    @Field(type = FieldType.Integer)
    private int viewNum;

    /**
     * 点赞数
     */
    @Field(type = FieldType.Integer)
    private int likeNum;

    /**
     * 是否被编辑推荐
     */
    @Field(type = FieldType.Text)
    private String isRecommend;

    /**
     * 栏目
     */
    @Field(type = FieldType.Text)
    private String category;


}
