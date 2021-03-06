package com.wax.blogsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wax.blogsystem.common.SysCode;
import com.wax.blogsystem.domain.Msg;
import com.wax.blogsystem.domain.User;
import com.wax.blogsystem.mapper.MsgMapper;
import com.wax.blogsystem.service.MsgService;
import com.wax.blogsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class MsgServiceImpl implements MsgService {

    @Autowired
    private MsgMapper msgMapper;

    @Autowired
    private UserService userService;

    @Override
    public void addMsg(Msg msg) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",msg.getReceiverName());
        User user = userService.selectOne(queryWrapper);
        msg.setCreateTime(new Date());
        msg.setDelTagRe(SysCode.DELTAG.WSC);
        msg.setDelTagSe(SysCode.DELTAG.WSC);
        msg.setIsRead(SysCode.IS_READ.NO);
        msg.setReceiver(user.getId());
        msgMapper.insert(msg);
    }

    @Override
    public IPage<Msg> inList(Page<Msg> page, User user) {
        QueryWrapper<Msg> queryWrapper = new QueryWrapper();
        queryWrapper.eq("del_tag_re",SysCode.DELTAG.WSC);
        queryWrapper.eq("receiver",user.getId());
        queryWrapper.orderByDesc("create_time");
        return msgMapper.selectPage(page,queryWrapper);
    }

    @Override
    public IPage<Msg> outList(Page<Msg> page, User user) {
        QueryWrapper<Msg> queryWrapper = new QueryWrapper();
        queryWrapper.eq("del_tag_se",SysCode.DELTAG.WSC);
        queryWrapper.eq("sender",user.getId());
        queryWrapper.orderByDesc("create_time");
        return msgMapper.selectPage(page,queryWrapper);
    }

    @Override
    public IPage<Msg> unReadList(Page<Msg> page, User user) {
        QueryWrapper<Msg> queryWrapper = new QueryWrapper();
        queryWrapper.eq("del_tag_re",SysCode.DELTAG.WSC);
        queryWrapper.eq("receiver",user.getId());
        queryWrapper.eq("is_read",SysCode.IS_READ.NO);
        queryWrapper.orderByDesc("create_time");
        return msgMapper.selectPage(page,queryWrapper);
    }

    @Override
    public Msg getDetailById(String id) {
        Msg msg = msgMapper.selectById(id);
        read(msg);
        return msg;
    }

    @Override
    public void read(Msg msg) {
        msg.setIsRead(SysCode.IS_READ.YES);
        msgMapper.updateById(msg);
    }

    @Override
    public void addMsgWhenRegister(User user) {
        Msg msg = new Msg();
        msg.setIsRead(SysCode.IS_READ.NO);
        msg.setDelTagRe(SysCode.DELTAG.WSC);
        msg.setCreateTime(new Date());
        msg.setDelTagSe(SysCode.DELTAG.WSC);
        msg.setSenderName("系统");
        msg.setContent("<p>恭喜你成为i博客的一员！</p>");
        String id = UUID.randomUUID().toString();
        msg.setId(id);
        msg.setReceiverName(user.getUsername());
        msg.setReceiver(user.getId());
        msg.setTitle("关于新成员的通知");
        msg.setSender("e15fc129f3dfef9cf265fabf38cf2cc3");
        msg.setType("通知");
        msgMapper.insert(msg);
    }


}
