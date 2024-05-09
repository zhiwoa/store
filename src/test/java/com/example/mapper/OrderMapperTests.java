package com.example.mapper;

import com.demos.entity.Address;
import com.demos.entity.Order;
import com.demos.entity.OrderItem;
import com.demos.mapper.AddressMapper;
import com.demos.mapper.OrderMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest//表示标注当前的类是一个测试类，不会随同项目打包发送
//@RunWith(SpringRunner.class)
public class OrderMapperTests {
    //userMapper  会报错：idea有检测的功能，接口是不能直接创建bean的（动态代理技术来解决）
    //@Repository  在接口mapper加这个注解就不会报错；将持久层接口的实现类交给spring管理，并封装数据访问异常
    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void insertOrder(){
        Order order=new Order();
        order.setUid(22);
        order.setRecvName("mingmign");
        order.setRecvPhone("13248677");
        orderMapper.insertOrder(order);

    }
    @Test
    public void insertOrderItem(){
        OrderItem order=new OrderItem();
        order.setOid(1);
        order.setPid(100003);
        order.setTitle("ahjsdkhaskjdh");
        orderMapper.insertOrderItem(order);

    }
}
