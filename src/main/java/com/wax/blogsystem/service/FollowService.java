package com.wax.blogsystem.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wax.blogsystem.domain.Follow;

public interface FollowService {


    void follow(Follow follow);


    Follow selectByFollowerAndBeFollower(String followerId,String beFollowerId);


    void cancelFollow(Follow follow);


    IPage<Follow> getBeFollowerList4Side(Page<Follow> page,String id);

    IPage<Follow> getFollowerList4Side(Page<Follow> page,String id);

}
