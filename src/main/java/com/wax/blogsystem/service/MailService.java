package com.wax.blogsystem.service;

public interface MailService {


    /**
     *  发送多媒体类型邮件
     * @param to
     * @param subject
     * @param content
     */
    void sendMimeMail(String to, String subject, String content);

}
