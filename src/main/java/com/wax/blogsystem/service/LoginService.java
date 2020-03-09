package com.wax.blogsystem.service;

import com.wax.blogsystem.domain.User;

public interface LoginService {

    String login(String username,String password);


    void logout();


    void register(User user);


    boolean checkCode(String code);


}
