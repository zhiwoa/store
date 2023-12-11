package com.demos.service.impl;

import com.demos.entity.Cart;
import com.demos.entity.Product;
import com.demos.mapper.CartMapper;
import com.demos.mapper.ProductMapper;
import com.demos.service.ICartService;
import com.demos.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;//获取price时候用的


    @Override
    public void addToCart(Integer uid, Integer pid, Integer amount, String username) {
        //根据参数pid 和uid 查询购物车中该商品是否存在
        Cart result = cartMapper.findByUidAndPid(uid,pid);


        Date date = new Date();
        //如果不为空，就执行插入数据
        if(result == null){
            Cart cart = new Cart();

            //封装数据：uid,pid,amount
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);//注意前端传来amount时并没有和数据库商品数量进行求和

            //查询商品数据，得到商品价格并封装
            Product product = productMapper.findById(pid);
            cart.setPrice(product.getPrice());

            //封装数据：4个日志
            cart.setCreatedUser(username);
            cart.setCreatedTime(date);
            cart.setModifiedUser(username);
            cart.setModifiedTime(date);

            Integer rows = cartMapper.insert(cart);
            if (rows != 1) {
                throw new InsertException("插入数据时出现未知异常");
            }
        }else{
            //从查询结果中取出原数量，与参数amount相加，得到新的数量
            Integer cid=result.getCid();
            Integer num = result.getNum() + amount;//加入购物车时只会有+不可能有-
            Integer rows = cartMapper.updateNumByCid(
                    result.getCid(),
                    num,
                    username,
                    date);
            if (rows != 1) {
                throw new InsertException("更新数据时产生未知异常");
            }
        }


    }
}
