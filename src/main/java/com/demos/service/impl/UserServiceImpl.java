package com.demos.service.impl;

import com.demos.entity.User;
import com.demos.mapper.UserMapper;
import com.demos.service.IUserService;
import com.demos.service.ex.InsertException;
import com.demos.service.ex.PasswordNotMatchException;
import com.demos.service.ex.UpdateException;
import com.demos.service.ex.UsernameDuplicateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    //密码的修改
    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User result = userMapper.findByUid(uid);
        if(result == null || result.getIsDelete() ==1){
            throw  new UsernameDuplicateException("用户数据不存在");
        }
        //在页面写入的密码和数据库汇总的密码进行比较
        String oldMd5Password =getMD5Password(oldPassword,result.getSalt());
        if(!result.getPassword().equals(oldMd5Password)){
            throw new PasswordNotMatchException("密码错误");
        }
        //将新密码设置到数据中,将新的密码进行加密再去更新
        String newMd5Password = getMD5Password(newPassword,result.getSalt());
       Integer rows= userMapper.updatePasswordByUid(uid,newMd5Password,username,new Date());
       if(rows !=1){
           throw new UpdateException("更新数据时候产生未知的异常");
       }
    }

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


    @Override
    public User login(String username, String passwod) {
        //1根据用户名称来查询用户的数据是否存在，如果不存在则抛出异常
         User result = userMapper.findByusername(username);
         if(result == null){
             throw new UsernameDuplicateException("用户数据不存在");
         }
         //2检测用户的密码是否匹配
        //2.1 获取到数据库中加密之后的密码
        String oldPassword = result.getPassword();
//        System.out.println(oldPassword);
        //2.2 先获取盐值：上次再注册时所自动生成的盐值
        String salt=result.getSalt();
        //2.3 将用户的面按照相同的md5算法规则进行加密 这个的密码是在用户登录页面获取的
        String newMd5Password =getMD5Password(passwod,salt);
//        System.out.println(newMd5Password);
        //3 将密码进行比较
        if(!newMd5Password.equals(oldPassword)){
            throw  new PasswordNotMatchException("用户密码错误");
        }

        //判断is_delete 字段是否为1 表示标记被删除
        if(result.getIsDelete() == 1){
            throw new UsernameDuplicateException("用户数据不存在");
        }
        //调用mapper层的findByUsername来查询数据 ，可以减少层与层之间的交互，提供了系统的性能
        User user =new User();
        user.setUid(result.getUid());//id
        user.setUsername(result.getUsername());//用户名
        user.setAvatar(result.getAvatar());//头像

        return user;
    }

}

