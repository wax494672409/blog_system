package com.wax.blogsystem.service;

import com.wax.blogsystem.domain.BlogEs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;

import java.util.Date;
import java.util.List;

public interface BlogEsService {

    AggregatedPage<BlogEs> allSearch(String str, Pageable pageable, Integer likeLimit, Integer viewLimit, Date dateLimit);

    Page<BlogEs> searchByContent(String str);

    void addBlogEs(BlogEs blogEs);

    void updateBlogEs(BlogEs blogEs);

}
