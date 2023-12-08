package com.example.service;

import com.demos.entity.Address;
import com.demos.service.IAddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
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

    @Test
    public  void findByAid(){
        addressService.updateSetDefault(10,34,"aaaa");
    }

    @Test
    public void delete(){
//        测试包括：不是默认地址的也能正常删除，是默认地址的，删除之后把最近修改的一个作为新的默认地址
        addressService.delete(12,34,"23");
    }
}
