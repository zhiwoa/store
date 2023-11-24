package com.demos.mapper;

import com.demos.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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
}

