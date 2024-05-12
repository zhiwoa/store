package com.example.service;

import com.demos.entity.Order;
import com.demos.service.IOrderService;
import com.demos.vo.OrderVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest//表示标注当前的类是一个测试类，不会随同项目打包发送
public class OrderServiceTests {

    @Autowired
    private IOrderService orderService;

    @Test
    public void create(){
        Integer[] cids={13,11,14};
        Order order = orderService.create(25,cids,34,"mm");
        System.out.println(order);

    }
    @Test
    public void findall(){
        List<OrderVO> orderByUid = orderService.findOrderByUid(34);
        System.out.println(orderByUid);
    }
}
