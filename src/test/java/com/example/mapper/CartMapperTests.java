package com.example.mapper;

import com.demos.entity.Cart;
import com.demos.mapper.CartMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest//表示标注当前的类是一个测试类，不会随同项目打包发送
public class CartMapperTests {
   @Autowired
    private CartMapper cartMapper;
    @Test
    public void insert() {
        Cart cart = new Cart();
        cart.setUid(11);
        cart.setPid(10000001);
        cart.setNum(3);
        cart.setPrice(4L);//长整型
        cartMapper.insert(cart);
    }

    @Test
    public void updateNumByCid() {
        cartMapper.updateNumByCid(1, 4, "张三", new Date());
    }
    @Test
    public void findByUidAndPid() {
        Cart cart = cartMapper.findByUidAndPid(11, 10000001);
        System.out.println(cart);
    }
}
