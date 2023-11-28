package com.demos.service;

import com.demos.entity.User;

public interface IUserService {
    void reg(User user);//用户插入

    //实现登录功能
    User login(String username,String passwod);

    void changePassword(Integer uid,String username,String oldPassword,String newPassword);

    User getByuid(Integer uid);//根据uid来查询用户信息

    void changeInfo(Integer uid,String username,User user);//session提供的uid和username 来显示修改信息页面
}
