package com.demos.mapper;

import com.demos.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 收获地址持久层接口
 */
public interface AddressMapper {
    /**
     * 插入用户的收获地址
     * @param address 收货地址数据
     * @return 受影响的行数
     */
    Integer insert(Address address);

    /**
     * 根据用户的id查询收获地址数量 20条-MAX
     * @param uid  用户的uid
     * @return  当前用户的收货地址总数
     */
    Integer countByUid(Integer uid);

    /**
     * 根据用户的id查询用户的收货地址数据
     * @param uid  用户的id
     * @return   收货地址
     */
    List<Address> findyByUid(Integer uid);

    /**
     * 根据aid 查询收获地址数据（在处理默认地址时候使用）
     * @param aid  收获地址id
     * @return  收货地址数据，，没有则返回null
     */
    Address findByAid(Integer aid);

    /**
     * 根据用户的uid来修改用户的收货地址设置为非默认在设置默认地址时候使用）
     * @param uid 用户的id值
     * @return 受影响的行数
     */
    Integer updateNonDefault(Integer uid);

    /**
     * 核心方法（在设置默认地址时候使用）
     * @param aid 用户的id值
     * @return 受影响的行数
     */
    Integer updateDefaultByAid(@Param("aid") Integer aid,
                               @Param("modifiedUser") String modifiedUser,
                               @Param("modifiedTime") Date modifiedTime);
}
