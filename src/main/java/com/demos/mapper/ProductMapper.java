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

    /**
     * 根据商品的id查询商品详情
     * @param id  商品的id
     * @return 匹配的商品详情，如果没有匹配的数据则返回null
     */
    Product findById(Integer id);

    /**
     * 查询最近创建时间排后的 商品的前四个
     * @return 商品
     */
    List<Product> findNewList();
}
