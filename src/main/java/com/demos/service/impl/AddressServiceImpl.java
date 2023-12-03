package com.demos.service.impl;

import com.demos.entity.Address;
import com.demos.entity.BaseEntity;
import com.demos.mapper.AddressMapper;
import com.demos.service.IAddressService;
import com.demos.service.ex.AddressCountLimitException;
import com.demos.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Value("${user.address.max-count}")
    private Integer maxCount;

    //新增收货地址的实现
    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        //调用收获地址统计的方法
        Integer count = addressMapper.countByUid(uid);
        if (count >= maxCount){
            throw new AddressCountLimitException("收获地址超出上限额");
        }
        address.setUid(uid);
        Integer isDefault = count == 0 ? 1 : 0;//0不默认，1-默认
        address.setIsDefault(isDefault);//当记录条数为0时候。用户所增加的第一条收货地址设置为默认收货地址

        //补全4项日志
        address.setCreatedUser(username);//日志创建者
        address.setModifiedUser(username);//日志最后执行者
        Date date =  new Date();
        address.setCreatedTime(date);
        address.setModifiedTime(date);

        //插入收货地址的方法
        Integer rows = addressMapper.insert(address);
        if(rows != 1){
            throw new InsertException("插入用户的收货地址产生未知的异常");
        }

    }
}
