package com.demos.mapper;

import com.demos.entity.Product;

import java.util.List;

//商品的抽象方法
public interface ProductMapper {

    /**
     * 查询热销商品的前四名
     * @return
     */
    List<Product> findHotList();
}
