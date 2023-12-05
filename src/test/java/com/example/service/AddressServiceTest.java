package com.example.service;

import com.demos.entity.Address;
import com.demos.service.IAddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AddressServiceTest {
    @Autowired
    private IAddressService addressService;

    @Test
    public void addNewAddress(){
        Address address=new Address();
        address.setPhone("23568978");
        address.setName("什么啊");
        addressService.addNewAddress(35,"管理员",address);
    }
    @Test
    public void getByUid(){
        List<Address> byUid = addressService.getByUid(34);
        for(Address i:byUid){
            System.out.println(i+" ");
        }
    }
}
