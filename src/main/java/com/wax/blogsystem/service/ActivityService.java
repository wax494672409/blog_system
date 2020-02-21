package com.wax.blogsystem.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wax.blogsystem.domain.Activity;

import java.util.List;

public interface ActivityService {

    List<Activity> getActitvityList(Page<Activity> page,String userId);


    void addActivity(Activity activity);


}
