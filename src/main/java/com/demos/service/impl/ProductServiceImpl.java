package com.demos.service.impl;

import com.demos.entity.Product;
import com.demos.mapper.ProductMapper;
import com.demos.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

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
}
