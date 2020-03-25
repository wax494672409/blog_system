package com.wax.blogsystem.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wax.blogsystem.domain.Msg;
import com.wax.blogsystem.domain.User;

public interface MsgService {

    void addMsg(Msg msg);

    IPage<Msg> inList(Page<Msg> page, User user);

    IPage<Msg> outList(Page<Msg> page, User user);

    IPage<Msg> unReadList(Page<Msg> page, User user);

}
