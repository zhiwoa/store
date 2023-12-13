package com.demos.service;

import com.demos.vo.CartVO;

import java.util.List;

public interface ICartService {
    /**
     * 将商品添加到购物车
     * @param uid 当前登录用户的id
     * @param pid 商品的id
     * @param amount 增加的数量
     * @param username 当前登录的用户名
     */
    void addToCart(Integer uid, Integer pid, Integer amount, String username);


    /**
     * 查询某用户的购物车数据
     * @param uid 用户的id
     * @return 用户的购物车数据
     */
    List<CartVO>getBoByUid(Integer uid);

    /**
     * 根据用户购物车数据的数量
     * @param cid
     * @param uid
     * @param username
     * @return  增加成功之后新的数量
     */
    /**
     * 该方法返回值void.这样的话就需要在前端页面加location.href使该页面自己跳转到自己,实现刷新页面(不建议,每次都加载整个页面,数据量太大了)
     * 返回值是Integer类型.这样的话就把数据库中更新后的数量层层传给前端,前端接收后填充到控件中就可以了8*/
    Integer addNum(Integer cid,Integer uid, String username);

}
