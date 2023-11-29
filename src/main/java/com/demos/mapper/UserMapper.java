package com.demos.mapper;

import com.demos.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * 用户模块的持久层接口**/
@Repository
public interface UserMapper {
    /**
     * 插入用户的数据
     * user 用户的数据
     * return 受影响的行数（增删改都收影响）
     * **/
    Integer insert(User user);

    User findByusername(String username);


    //开发时候遇到问题：多参数时候需要使用@Param("uid") 里面的参数需要与实体类的一样
    Integer updatePasswordByUid(
            @Param("uid") Integer uid, @Param("password")String password,
            @Param("modifiedUser") String modifiedUser,@Param("modifiedTime") Date modifiedTime);//根据用户的uid 来修改用密码

    User findByUid(Integer uid);//根据用户的id查询数据

    Integer UpdateInfoByUid(User user);//修改个人资料

    Integer UpdateAvatarByUid(@Param("uid") Integer uid,
                              @Param("avatar")String avatar,
                              @Param("modifiedUser") String modifiedUser,
                              @Param("modifiedTime") Date modifiedTime);//根据用户的uid来修改用户的头像
}

