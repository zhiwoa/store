package com.example.mapper;

import com.demos.entity.User;
import com.demos.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest//表示标注当前的类是一个测试类，不会随同项目打包发送
//@RunWith(SpringRunner.class)
public class UserMapperTests {
    //userMapper  会报错：idea有检测的功能，接口是不能直接创建bean的（动态代理技术来解决）
    //@Repository  在接口mapper加这个注解就不会报错；将持久层接口的实现类交给spring管理，并封装数据访问异常
    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert(){
        User user=new User();
        user.setUsername("tom");
        user.setPassword("123");
        Integer row = userMapper.insert(user);
        System.out.println(row);

    }
    @Test
    public void findByusername(){

        User username= userMapper.findByusername("tom");
        System.out.println(username.toString());

    }
    @Test
    public void updatePasswordByUid(){
        userMapper.updatePasswordByUid(12,"321","管理员",new Date());

    }
    @Test
    public void findByUid(){
        System.out.println(userMapper.findByUid(12));
    }


    @Test
    public void UpdateInfoByUid(){
      User user=new User();
      user.setUid(31);
      user.setPhone("123456798");
      user.setEmail("test5@qq.com");
      user.setGender(1);
      userMapper.UpdateInfoByUid(user);
    }


//    @Test
//    public void UpdateAvatarByUid(){
//        userMapper.UpdateAvatarByUid(
//                33,"/upload/avatar.png","管理员",new Date()
//        );
//    }
}
