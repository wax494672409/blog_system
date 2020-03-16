package com.wax.blogsystem.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wax.blogsystem.common.JSONUtil;
import com.wax.blogsystem.domain.Follow;
import com.wax.blogsystem.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/follow")
public class FollowController {

    @Autowired
    private FollowService followService;

    @PostMapping("/follow.do")
    @ResponseBody
    public String follow(Follow follow){
        followService.follow(follow);
        return JSONUtil.success("关注成功");
    }


    @PostMapping("/cancelFollow.do")
    @ResponseBody
    public String cancelFollow(Follow follow){
        followService.cancelFollow(follow);
        return JSONUtil.success("已取消关注");
    }


    @PostMapping("/getBeFollowerList4Side.do")
    public String getBeFollowerList4Side(Page<Follow> page,String id,Model model){
        IPage<Follow> iPage = followService.getBeFollowerList4Side(page,id);
        model.addAttribute("beFollowerSideList",iPage.getRecords());
        return "/user/personal::be_follower_list_side";
    }


    @PostMapping("/getFollowerList4Side.do")
    public String getFollowerList4Side(Page<Follow> page,String id,Model model){
        IPage<Follow> iPage = followService.getFollowerList4Side(page,id);
        model.addAttribute("followerSideList",iPage.getRecords());
        return "/user/personal::follower_list_side";
    }


    @PostMapping("/getBeFollowerList.do")
    public String getBeFollowerList(Page<Follow> page,String id,Model model){
        IPage<Follow> iPage = followService.getBeFollowerList4Side(page,id);
        model.addAttribute("beFollowerList",iPage.getRecords());
        model.addAttribute("followerTotalNum",iPage.getTotal());
        return "/personal/follower::be_follower_list";
    }

    @PostMapping("/getFollowerList.do")
    public String getFollowerList(Page<Follow> page,String id,Model model){
        IPage<Follow> iPage = followService.getFollowerList4Side(page,id);
        model.addAttribute("followerList",iPage.getRecords());
        model.addAttribute("followerTotalNum",iPage.getTotal());
        return "/personal/follower::follower_list";
    }

}
