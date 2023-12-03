package com.demos.service.impl;

import com.demos.entity.District;
import com.demos.mapper.DistrictMapper;
import com.demos.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements IDistrictService {

    @Autowired
    private DistrictMapper districtMapper;
    @Override
    public List<District> getByParent(String parent) {
        List<District> list = districtMapper.findByparent(parent);
        //在进行网络数据传输时候，为了尽量避免无效数据的传递，可以将无效数据设置为null
        for(District d : list){
            d.setId(null);
            d.setParent(null);
        }

        return list;
    }

    @Override
    public String getNameByCode(String code) {
        return districtMapper.findNameByCode(code);
    }
}
