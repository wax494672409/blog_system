package com.wax.blogsystem.service;

import com.wax.blogsystem.domain.BlogFavorite;

public interface BlogFavoriteService {

    void favorite(BlogFavorite blogFavorite);


    BlogFavorite selectByUserAndBlog(String userId,String blogId);

    void cancelFavorite(BlogFavorite blogFavorite);

}
