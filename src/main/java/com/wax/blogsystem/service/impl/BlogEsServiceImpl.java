package com.wax.blogsystem.service.impl;

import com.wax.blogsystem.BlogRepository;
import com.wax.blogsystem.domain.BlogEs;
import com.wax.blogsystem.service.BlogEsService;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class BlogEsServiceImpl implements BlogEsService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public Page<BlogEs> allSearch(String str, Pageable pageable) {
        QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        return blogRepository.search(queryBuilder, pageable);
    }

    @Override
    public Page<BlogEs> searchByContent(String str) {
        QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(str,"summary","title");
        return blogRepository.search(queryBuilder, Pageable.unpaged());
    }

}
