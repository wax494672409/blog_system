package com.wax.blogsystem.common.pojo;

import lombok.Data;

@Data
public class Page {

    private int page;

    private int limit;

    private int startIndex;

    private int endIndex;

    //是否是搜索
    private String isSearch;

}
