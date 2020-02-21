package com.wax.blogsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wax.blogsystem.domain.Activity;
import com.wax.blogsystem.mapper.ActivityMapper;
import com.wax.blogsystem.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public List<Activity> getActitvityList(Page<Activity> page,String id) {
        QueryWrapper<Activity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",id);
        queryWrapper.orderByDesc("create_time");
        IPage<Activity> activityIPage = activityMapper.selectPage(page,queryWrapper);
        return activityIPage.getRecords();
    }

    @Override
    public void addActivity(Activity activity) {
        activityMapper.insert(activity);
    }
}
