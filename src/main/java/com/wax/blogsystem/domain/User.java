package com.wax.blogsystem.domain;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private String id;

    private String username;

    private String password;

    private String name;

    private String roleCode;

    private String delTag;

    private Date createTime;

}