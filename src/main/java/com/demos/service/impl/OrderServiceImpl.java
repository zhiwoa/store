package com.demos.service.impl;

import com.demos.entity.Address;
import com.demos.entity.Order;
import com.demos.entity.OrderItem;
import com.demos.mapper.OrderMapper;
import com.demos.service.IAddressService;
import com.demos.service.ICartService;
import com.demos.service.IOrderService;
import com.demos.service.IUserService;
import com.demos.service.ex.InsertException;
import com.demos.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;

    //需要调用业务层的getByAid方法
    @Autowired
    private IAddressService addressService;

    //需要调用业务层的getVOByCids方法
    @Autowired
    private ICartService cartService;

    //需要调用业务层的getByUid方法
    private IUserService userService;
    /**
     * 创建订单
     *
     * @param aid      收货地址的id
     * @param cids     即将购买的商品数据在购物车表中的id
     * @param uid      当前登录的用户的id
     * @param username
     * @return 成功创建的订单数据
     */
    @Override
    public Order create(Integer aid, Integer[] cids, Integer uid, String username) {
        //返回的列表中的对象都是即将下单的
        List<CartVO> list = cartService.getBoBycids(cids,uid);

        long totalPrice = 0L;
        for (CartVO cartVO : list) {
            totalPrice += cartVO.getRealPrice()*cartVO.getNum();

        }
        Address address = addressService.getByAid(aid, uid);
        Order order = new Order();
        order.setUid(uid);

        //封装收货地址
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());

        //封装创建时间,支付状态和总价
        order.setOrderTime(new Date());
        order.setStatus(0);
        order.setTotalPrice(totalPrice);

        //封装四个日志
        order.setCreatedUser(username);
        order.setCreatedTime(new Date());
        order.setModifiedUser(username);
        order.setModifiedTime(new Date());
        Integer rows = orderMapper.insertOrder(order);
        if (rows != 1) {
            throw new InsertException("插入数据时产生未知的异常");
        }

        //插入数据——将某条订单的所有商品的详细数据插入
        for (CartVO cartVO : list) {
            OrderItem orderItem = new OrderItem();

            /**
             * 此时获取的oid不为空,因为在配置文件里面开启了oid主
             * 键自增,所以上面的代码执行插入时就自动将oid赋值了
             */
            orderItem.setOid(order.getOid());

            orderItem.setPid(cartVO.getPid());
            orderItem.setTitle(cartVO.getTitle());
            orderItem.setImage(cartVO.getImage());
            orderItem.setPrice(cartVO.getRealPrice());
            orderItem.setNum(cartVO.getNum());

            orderItem.setCreatedUser(username);
            orderItem.setCreatedTime(new Date());
            orderItem.setModifiedUser(username);
            orderItem.setModifiedTime(new Date());

            rows = orderMapper.insertOrderItem(orderItem);
            if (rows != 1) {
                throw new InsertException("插入数据时产生未知的异常");
            }
        }
        return order;
    }

//
//    @Autowired
//    private OrderMapper orderMapper;
//    @Autowired
//    private IAddressService addressService;
//    @Autowired
//    private ICartService cartService;
//
//    /**
//     * 创建订单
//     *
//     * @param aid      收货地址的id
//     * @param cids     即将购买的商品数据在购物车表中的id
//     * @param uid      当前登录的用户的id
//     * @param name 当前登录的用户名
//     * @return 成功创建的订单数据
//     */
//    @Override
//    public Order create(Integer aid, Integer uid, String name, Integer[] cids) {
//       //获取即将下单的列表
//        List<CartVO> list = cartService.getBoBycids(cids, uid);
//        //计算产品总价
//        Long totalPrice=0L;
//
//        //创建订单详细项
//        for(CartVO c:list){
//            totalPrice+=c.getRealPrice()*c.getNum();
//        }
//        Address address = addressService.getByAid(aid, uid);
//
//        Order order=new Order();
//        //收货地址
//        order.setUid(uid);
//        order.setRecvName(address.getName());
//        order.setRecvPhone(address.getPhone());
//        order.setRecvProvince(address.getProvinceName());
//        order.setRecvCity(address.getCityName());
//        order.setRecvArea(address.getAreaName());
//        order.setRecvAddress(address.getAddress());
//        //支付、总价、时间
//        order.setStatus(0);
//        order.setTotalPrice(totalPrice);
//        order.setOrderTime(new Date());
//        //日志
//        order.setCreatedUser(name);
//        order.setCreatedTime(new Date());
//        order.setModifiedUser(name);
//        order.setCreatedTime(new Date());
//
//        //查询数据
//        Integer rows = orderMapper.insertOrder(order);
//        if(rows != 1){
//            throw new InsertException("插入数据产生异常");
//        }
//
//        //创建订单详细项
//        for(CartVO c:list){
//            //创建订单项数据对象
//            OrderItem orderItem=new OrderItem();
//            orderItem.setOid(order.getOid());
//            orderItem.setPid(c.getPid());
//            orderItem.setTitle(c.getTitle());
//            orderItem.setImage(c.getImage());
//            orderItem.setPrice(c.getRealPrice());
//            orderItem.setNum(c.getNum());
//            //日志
//            orderItem.setCreatedUser(name);
//            orderItem.setCreatedTime(new Date());
//            orderItem.setModifiedUser(name);
//            orderItem.setModifiedTime(new Date());
//            //插入数据操作
//             rows = orderMapper.insertOrderItem(orderItem);
//            if(rows !=1 ){
//                throw new InsertException("插入数据异常");
//            }
//        }
//        return order;
//    }
}
