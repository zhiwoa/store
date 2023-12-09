package com.demos.service.impl;

import com.demos.entity.Product;
import com.demos.mapper.ProductMapper;
import com.demos.service.IProductService;
import com.demos.service.ex.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    //查找热销的前四名
    @Override
    public List<Product> findHotList() {
        List<Product> list = productMapper.findHotList();
        for(Product p:list){
            p.setPriority(null);//显示优先级
            p.setCreatedUser(null);
            p.setCreatedTime(null);
            p.setModifiedUser(null);
            p.setModifiedTime(null);
        }
        return list;
    }
    //查询最近新增加的商品的前四个
    @Override
    public List<Product> findNewList() {
        List<Product> newList = productMapper.findNewList();
        for(Product p:newList){
            p.setPriority(null);//显示优先级
            p.setCreatedUser(null);
            p.setCreatedTime(null);
            p.setModifiedUser(null);
            p.setModifiedTime(null);
        }
        return newList;
    }

    //根据商品的id查询商品详情
    @Override
    public Product findById(Integer id) {
        Product product = productMapper.findById(id);
        if (product == null) {
            throw new ProductNotFoundException("尝试访问的商品数据不存在");
        }
        // 将查询结果中的部分属性设置为null
        product.setPriority(null);
        product.setCreatedUser(null);
        product.setCreatedTime(null);
        product.setModifiedUser(null);
        product.setModifiedTime(null);

        return product;
    }
}
