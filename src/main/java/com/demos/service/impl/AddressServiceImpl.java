package com.demos.service.impl;

import com.demos.entity.Address;
import com.demos.mapper.AddressMapper;
import com.demos.service.IAddressService;
import com.demos.service.IDistrictService;
import com.demos.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {
    //删除收货信息
    @Override
    public void delete(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if(result == null){
            throw new AddressNotFoundException("收货地址不存在");
        }
        //检测当前获取到的收货地址是数据归属
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问");
        }
        Integer rows = addressMapper.deleteByAid(aid);
        if (rows != 1){
            throw new DeleteException("删除时候产生未知的异常");
        }
        Integer count = addressMapper.countByUid(uid);
        if(count == 0){
            return;//收货地址为0 ，删除操作停止
        }

        //如果删除的是默认地址，才会把最近修改的收货信息改为默认地址
        if(result.getIsDefault() ==1){
            //将这条数据中的isdefault字符设置为1
            Address  lastModefied= addressMapper.findLastModefied(uid);
            rows = addressMapper.updateDefaultByAid(lastModefied.getAid(),username,new Date());
        }
        if ( rows != 1){
            throw new UpdateException("数据更新时候产生位置的异常");
        }

    }

    @Autowired
    private AddressMapper addressMapper;

    //再添加用户的收货地址业务层依赖于IDistrictService
    @Autowired
    private IDistrictService districtService;
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

        //对address对象中的数据进行补全：省市区
        String ProvinceName = districtService.getNameByCode(address.getProvinceCode());
        String CityName = districtService.getNameByCode(address.getCityCode());
        String AreaName = districtService.getNameByCode(address.getAreaCode());
        address.setProvinceName(ProvinceName);
        address.setCityName(CityName);
        address.setAreaName(AreaName);


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

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list = addressMapper.findyByUid(uid);
        //前端只展示地址类型 收货姓名，详细地址，联系电话
        for(Address a:list){

//            a.setAid(null);
//            a.setUid(null);
            a.setProvinceCode(null);
            a.setCityCode(null);
            a.setAreaCode(null);
            a.setTel(null);
            a.setIsDefault(null);
            a.setCreatedUser(null);
            a.setCreatedTime(null);
            a.setModifiedTime(null);
            a.setModifiedUser(null);
        }

        return list;
    }

    @Override
    public void updateSetDefault(Integer aid, Integer uid, String name) {
        Address result = addressMapper.findByAid(aid);
        if( result == null){
            throw new AddressNotFoundException("收获地址不存在");
        }
        //检测当前获取到的收货地址是数据归属
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问");
        }
//        先将所有的收货地址设置为默认
        Integer rows = addressMapper.updateNonDefault(uid);
        if(rows < 1){
            throw new UpdateException("数据更新时候产生位置的异常");
        }
        //现将用户选中的某条数据设置为默认收货地址
        rows = addressMapper.updateDefaultByAid(aid,name,new Date());
        if(rows < 1){
            throw new UpdateException("数据更新时候产生位置的异常");
        }

    }
}
