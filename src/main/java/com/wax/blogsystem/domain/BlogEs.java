package com.wax.blogsystem.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Document(indexName = "blog",type = "doc",shards = 1,replicas = 0,refreshInterval = "-1")
@Data
public class BlogEs implements Serializable {

    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String titile;

    @Field(type = FieldType.Text)
    private String summary;
}
