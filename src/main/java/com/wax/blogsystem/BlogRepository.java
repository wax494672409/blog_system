package com.wax.blogsystem;

import com.wax.blogsystem.domain.BlogEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends ElasticsearchRepository<BlogEs,String> {



}
