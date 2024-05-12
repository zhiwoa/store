package com.demos.mapper;

import com.demos.entity.Order;
import com.demos.entity.OrderItem;
import com.demos.vo.CartVO;
import com.demos.vo.OrderVO;

import java.util.List;

/**
 * 订单的持久层接口
 */
public interface OrderMapper {

    /**
     * 插入订单数据
     * @param order 订单数据
     * @return 受影响的行分数
     */
    Integer insertOrder(Order order);
    /**
     * 插入订单商品数据
     * @param orderItem 订单商品数据
     * @return 受影响的行数
     */
    Integer insertOrderItem(OrderItem orderItem);

    /**
     * 展示完成支付之后的页面
     * @param uid 用户的id
     * @return  返回支付订单数据
     */
    List<OrderVO>findOrderByUid(Integer uid);
}
