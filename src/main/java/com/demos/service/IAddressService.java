package com.demos.service;

import com.demos.entity.Address;

public interface IAddressService {
    void addNewAddress(Integer uid, String username, Address address);
}
