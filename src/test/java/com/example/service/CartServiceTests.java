package com.example.service;

import com.demos.service.ICartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CartServiceTests {
    @Autowired
    private ICartService cartService;

    //要测试两个  一个是没有数据时候  一个是有该数据的时候，商品的数量是否会相加
    @Test
    public void addToCart() {
        cartService.addToCart(11, 10000002, 3, "Tom");
    }


    @Test
    public void addNum() {
        Integer integer = cartService.addNum(1,11,"123");
        System.out.println(integer);
    }

    @Test
    public void de(){
       cartService.deleteBycidS(6);
    }
}
