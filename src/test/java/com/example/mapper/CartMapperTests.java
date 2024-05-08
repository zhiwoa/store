package com.example.mapper;

import com.demos.entity.Cart;
import com.demos.mapper.CartMapper;
import com.demos.vo.CartVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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


    @Test
    public void findVOByUid() {
        List<CartVO> list = cartMapper.findVoByUid(11);
        System.out.println(list);
    }


    @Test
    public void findByCid() {
        System.out.println(cartMapper.findByCid(1));
    }

    @Test
    public void deleteBycid(){
        cartMapper.deleteByCid(7);
    }

//    @Test
//    public void deleteBycidS(){
//        List<Integer>cids=new ArrayList<>();
//        cids.add(1);
//        cids.add(2);
//        cartMapper.delByCidS(cids);
//    }

    @Test
    public void findVoByCid(){
        Integer []cids={1,2,5,6};
        System.out.println(cartMapper.findVoByCids(cids));
    }

}
