package com.demos.service;

import com.demos.entity.District;

import java.util.List;

public interface IDistrictService {
    /**
     * 根据父代号来查询区域信息（省市区）
     * @param parent  父代码
     * @return   多个区域的信息
     */
    List<District> getByParent(String parent);
}
