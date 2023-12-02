package com.demos.mapper;

import com.demos.entity.Address;

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
}
