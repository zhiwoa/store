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
}
