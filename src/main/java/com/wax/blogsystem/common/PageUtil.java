package com.wax.blogsystem.common;

import com.wax.blogsystem.common.pojo.Page;
import lombok.Data;

@Data
public class PageUtil {

    public static Page queryScope(Page page){
        page.setStartIndex((page.getPage()-1)*page.getLimit());
        page.setEndIndex(page.getPage()*page.getLimit());
        return page;
    }
}
