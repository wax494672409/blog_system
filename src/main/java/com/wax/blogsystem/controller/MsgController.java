package com.wax.blogsystem.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wax.blogsystem.common.JSONUtil;
import com.wax.blogsystem.domain.Msg;
import com.wax.blogsystem.domain.User;
import com.wax.blogsystem.service.MsgService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/msg")
public class MsgController {

    @Autowired
    private MsgService msgService;

    @PostMapping("/addMsg.do")
    @ResponseBody
    public String addMsg(Msg msg){
        msgService.addMsg(msg);
        return JSONUtil.success("发送成功");
    }


    @PostMapping("/inList.do")
    public String inList(Page<Msg> page, Model model){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        IPage<Msg> iPage = msgService.inList(page,user);
        model.addAttribute("msgList",iPage.getRecords());
        model.addAttribute("msgTotalNum",iPage.getTotal());
        return "/personal/msg::msg_list";
    }

    @PostMapping("/outList.do")
    public String outList(Page<Msg> page,Model model){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        IPage<Msg> iPage = msgService.outList(page,user);
        model.addAttribute("msgList",iPage.getRecords());
        model.addAttribute("msgTotalNum",iPage.getTotal());
        return "/personal/msg::out_list";
    }

    @PostMapping("/unReadList.do")
    public String unReadList(Page<Msg> page,Model model){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        IPage<Msg> iPage = msgService.unReadList(page,user);
        model.addAttribute("msgList",iPage.getRecords());
        model.addAttribute("msgTotalNum",iPage.getTotal());
        return "/personal/msg::msg_list";
    }

    @PostMapping("/getDetail.do")
    @ResponseBody
    public String getDetail(String id){
        Msg msg = msgService.getDetailById(id);
        return JSONUtil.result(true,msg,null);
    }

}
