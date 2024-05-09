package com.demos.service;

import com.demos.entity.Address;

import java.util.List;

public interface IAddressService {
    void addNewAddress(Integer uid, String username, Address address);

    /**
     * 根据用户的id查询用户的收获信息
     * @param uid  用户的id
     * @return  用户的收货信息
     */
    List<Address> getByUid(Integer uid);

    /**
     * 修改某个用户的某条收货地址为默认地址
     * @param aid 地址的id
     * @param uid  用户的id
     * @param name 用户的名字
     */
    void updateSetDefault(Integer aid,Integer uid,String name);

//    删除收货信息
    void delete(Integer aid,Integer uid,String username);

    //修改某个指定地址的抽象方法
    int updateOneAddress(Address address,String modifiedUser);

    //查询某条地址的抽象方法
    Address queryAddressByAid(Integer aid);

    /**
     * 根据地址id获取收货地址数据
     * @param aid
     * @return
     */
    Address getByAid(Integer aid,Integer uid);
}
