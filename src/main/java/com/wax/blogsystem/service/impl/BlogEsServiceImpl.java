package com.wax.blogsystem.service.impl;

import com.wax.blogsystem.BlogRepository;
import com.wax.blogsystem.common.SysCode;
import com.wax.blogsystem.domain.BlogEs;
import com.wax.blogsystem.service.BlogEsService;
import org.apache.http.client.utils.DateUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.bytes.BytesReference;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.MAX_VALUE;

@Service
public class BlogEsServiceImpl implements BlogEsService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public AggregatedPage<BlogEs> allSearch(String str, Pageable pageable, Integer likeLimit, Integer viewLimit, Date dateLimit) {
        String preTag = "<strong>";//google的色值
        String postTag = "</strong>";
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        QueryBuilder queryBuilder = handelParam(boolQueryBuilder,str,likeLimit,viewLimit,dateLimit);
//        return blogRepository.search(queryBuilder, pageable);
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().
                withQuery(queryBuilder).
                withHighlightFields(new HighlightBuilder.Field("title").preTags(preTag).postTags(postTag),
                        new HighlightBuilder.Field("summary").preTags(preTag).postTags(postTag)
                ).
                build();
        searchQuery.setPageable(pageable);
        // 高亮字段
        AggregatedPage<BlogEs> ideas = elasticsearchTemplate.queryForPage(searchQuery, BlogEs.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                List<BlogEs> chunk = new ArrayList<>();
                for (SearchHit searchHit : response.getHits()) {
                    if (response.getHits().getHits().length <= 0) {
                        return null;
                    }
                    Map<String,Object> map = searchHit.getSourceAsMap();
                    BlogEs blogEs = new BlogEs();
                    //name or memoe
                    HighlightField title = searchHit.getHighlightFields().get("title");
                    if (title != null) {
                        blogEs.setTitle(title.fragments()[0].toString());
                    }
                    HighlightField summary = searchHit.getHighlightFields().get("summary");
                    if (summary != null) {
                        blogEs.setSummary(summary.fragments()[0].toString());
                    }
                    blogEs.setViewNum((Integer)map.get("viewNum"));
                    blogEs.setReleaseTime(new Date((Long)map.get("releaseTime")));
                    blogEs.setId((String)map.get("id"));
                    chunk.add(blogEs);
                }
                if (chunk.size() > 0) {
                    return new AggregatedPageImpl<>((List<T>) chunk);
                }
                return null;
            }
        });
        return ideas;
    }

    @Override
    public Page<BlogEs> searchByContent(String str) {
        QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(str,"summary","title");
        return blogRepository.search(queryBuilder, Pageable.unpaged());
    }

    @Override
    public void addBlogEs(BlogEs blogEs) {
        blogRepository.save(blogEs);
    }

    @Override
    public void updateBlogEs(BlogEs blogEs) {
        blogRepository.save(blogEs);
    }

    public QueryBuilder handelParam(BoolQueryBuilder queryBuilder,String str,Integer likeLimit, Integer viewLimit, Date dateLimit){
        QueryBuilder strQueryBuilder = QueryBuilders.multiMatchQuery(str,"title","summary","content");
        queryBuilder = queryBuilder.must(strQueryBuilder);
        if(!(likeLimit==null)){
            RangeQueryBuilder likeRangeBuilder = QueryBuilders
                    //传入时间，目标格式2020-01-02T03:17:37.638Z
                    .rangeQuery("likeNum")
                    .from(likeLimit).to(MAX_VALUE);
            queryBuilder.must(likeRangeBuilder);
        }
        if(!(viewLimit==null)){
            RangeQueryBuilder viewRangeBuilder = QueryBuilders
                    //传入时间，目标格式2020-01-02T03:17:37.638Z
                    .rangeQuery("viewNum")
                    .from(viewLimit).to(MAX_VALUE);
            queryBuilder.must(viewRangeBuilder);
        }
        if(dateLimit!=null){
            String date = DateUtils.formatDate(new Date(),"yyyy-MM-dd'T'HH:mm:ss'Z'");
            String dateL = DateUtils.formatDate(dateLimit,"yyyy-MM-dd'T'HH:mm:ss'Z'");
            RangeQueryBuilder dateRangeBuilder = QueryBuilders
                    //传入时间，目标格式2020-01-02T03:17:37.638Z
                    .rangeQuery("releaseTime")
                    .from(dateL).to(date);
            queryBuilder.must(dateRangeBuilder);
        }
        queryBuilder.must(QueryBuilders.matchQuery("delTag",SysCode.DELTAG.WSC))
                    .must(QueryBuilders.matchQuery("status",SysCode.BLOG_STATUS.RELEASED));
        return queryBuilder;
    }

}
