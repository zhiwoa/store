package com.example.service;

import com.demos.entity.Address;
import com.demos.entity.District;
import com.demos.service.IAddressService;
import com.demos.service.IDistrictService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DistictServiceTest {
    @Autowired
    private IDistrictService districtService;

    @Test
    public void addNewAddress(){
        //86代表中国，所有省的父代号
        List<District> list = districtService.getByParent("86");
        for(District d: list){
            System.out.println(d);
        }
    }

    @Test
    public void findNameByCode() {
        System.out.println(districtService.getNameByCode("610000"));
    }
}
