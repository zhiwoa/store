package com.demos.service.impl;

import com.demos.entity.User;
import com.demos.mapper.UserMapper;
import com.demos.service.IUserService;
import com.demos.service.ex.InsertException;
import com.demos.service.ex.UsernameDuplicateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
  @Autowired
  private UserMapper userMapper;
  //数据的插入
    @Override
    public void reg(User user) {
        String username=user.getUsername();//通过user 参数来获取传递过来的username
        User result = userMapper.findByusername(username);//判断用户是否被注册过（占用）
        if(result != null ){
            //抛出异常
            throw new UsernameDuplicateException("用户名被占用");
        }
        //密码加密处理：md5算法的形式   串+ password + 串 ---- md5算法加密  连续加密三次
        //盐值+ password + 盐值 ---- md5算法加密   盐值是一个随机的串

        String oldPassword = user.getPassword();
        //随机生成盐值
        String salt = UUID.randomUUID().toString().toUpperCase();
        //补全盐值
        user.setSalt(salt);
        //将密码和盐值作为一个整体进行加密处理,优点：忽略原有密码的强度
        String md5password = getMD5Password(oldPassword,salt);
        //将加密好的密码重新补全到user对象中
        user.setPassword(md5password);

        //补全数据
        user.setIsDelete(0);//是否删除
        user.setCreatedUser(user.getUsername());//日志创建者
        user.setModifiedUser(user.getUsername());//日志最后执行者
        Date date =  new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);


        //执行注册业务功能的实现
        Integer row = userMapper.insert(user);
        if(row != 1){
            throw  new InsertException("在用户注册过程中产生了未知的异常");
        }
    }
    //定义一个md5的加密处理
    private String getMD5Password(String password,String salt){
        //md5加密算法方法的调用(三次加密)
        for(int i=0;i<3;i++){
       password  = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
