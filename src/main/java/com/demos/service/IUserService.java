package com.demos.service;

import com.demos.entity.User;

public interface IUserService {
    void reg(User user);//用户插入

    //实现登录功能
    User login(String username,String passwod);

}
