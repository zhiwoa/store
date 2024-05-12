package com.demos.service;

import com.demos.entity.Address;
import com.demos.entity.Order;
import com.demos.vo.OrderVO;

import java.util.List;

/** 处理订单和订单数据的业务层接口 */
public interface IOrderService {
    /**
     * 创建订单
     * @param aid 收货地址的id
     * @param cids 即将购买的商品数据在购物车表中的id
     * @param uid 当前登录的用户的id
     * @param username 当前登录的用户名
     * @return 成功创建的订单数据
     */
    Order create(Integer aid, Integer[] cids, Integer uid, String username);

    /**
     * 展示完成支付之后的页面
     * @param uid 用户的id
     * @return  返回支付订单数据
     */
    List<OrderVO> findOrderByUid(Integer uid);
}
