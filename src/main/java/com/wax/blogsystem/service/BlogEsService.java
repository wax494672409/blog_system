package com.wax.blogsystem.service;

import com.wax.blogsystem.domain.BlogEs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogEsService {

    Page<BlogEs> allSearch(String str, Pageable pageable);

    Page<BlogEs> searchByContent(String str);

    void addBlogEs(BlogEs blogEs);

    void updateBlogEs(BlogEs blogEs);

}
