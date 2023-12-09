package com.demos.service;

import com.demos.entity.Product;

import java.util.List;

public interface IProductService {
    /**
     * 查询热销商品的前四名
     * @return 热销商品前四名的集合
     */
    List<Product> findHotList();
}
