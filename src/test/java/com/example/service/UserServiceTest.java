package com.example.service;

import com.demos.entity.User;
import com.demos.mapper.UserMapper;
import com.demos.service.IUserService;
import com.demos.service.ex.ServiceException;
import com.demos.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private IUserService iUserService;

    @Test
    public void reg(){
        try{
            User user =new User();
            user.setUsername("BWGL2");
            user.setPassword("123");
            iUserService.reg(user);
            System.out.println("ok");
        }catch (ServiceException e){
            System.out.println(e.getClass().getSimpleName());//获取类的对象，在获取类的名称
            System.out.println(e.getMessage());//获取描述异常的信息
        }

    }

//测试登录功能
    @Test
    public void login(){
        //一开始出现了一个问题：就是数据库中有的密码是没有加密过的，这个只能作为加密之后的校验
        User user = iUserService.login("laji","123");
        System.out.println(user);
    }

}
