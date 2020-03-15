package com.wax.blogsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wax.blogsystem.common.SysCode;
import com.wax.blogsystem.domain.Follow;
import com.wax.blogsystem.mapper.FollowMapper;
import com.wax.blogsystem.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowMapper followMapper;

    @Override
    public void follow(Follow follow) {
        follow.setCreateTime(new Date());
        follow.setDelTag(SysCode.DELTAG.WSC);
        followMapper.insert(follow);
    }

    @Override
    public Follow selectByFollowerAndBeFollower(String followerId, String beFollowerId) {
        QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("follower",followerId);
        queryWrapper.eq("be_follower",beFollowerId);
        queryWrapper.eq("del_tag", SysCode.DELTAG.WSC);
        return followMapper.selectOne(queryWrapper);
    }

    @Override
    public void cancelFollow(Follow follow) {
        follow = selectByFollowerAndBeFollower(follow.getFollower(),follow.getBeFollower());
        follow.setDelTag(SysCode.DELTAG.YSC);
        followMapper.updateById(follow);
    }

    @Override
    public IPage<Follow> getBeFollowerList4Side(Page<Follow> page,String id) {
        QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_tag",SysCode.DELTAG.WSC);
        queryWrapper.eq("follower",id);
        return followMapper.selectPage(page,queryWrapper);
    }

    @Override
    public IPage<Follow> getFollowerList4Side(Page<Follow> page, String id) {
        QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_tag",SysCode.DELTAG.WSC);
        queryWrapper.eq("be_follower",id);
        return followMapper.selectPage(page,queryWrapper);
    }
}
