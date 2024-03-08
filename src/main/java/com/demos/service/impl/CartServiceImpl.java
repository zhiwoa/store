package com.demos.service.impl;

import com.demos.entity.Cart;
import com.demos.entity.Product;
import com.demos.mapper.CartMapper;
import com.demos.mapper.ProductMapper;
import com.demos.service.ICartService;
import com.demos.service.ex.*;
import com.demos.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;//获取price时候用的


    //加入购物车
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

    //展示购物车
    @Override
    public List<CartVO> getBoByUid(Integer uid) {
        return cartMapper.findVoByUid(uid);
    }

    //增加购物车数量
    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
        Cart result = cartMapper.findByCid(cid);
        if (result == null) {
            throw new CartNotFoundException("数据不存在");
        }
        if (!result.getUid().equals(uid)) {
            throw new AccessDeniedException("数据非法访问");
        }
        Integer num = result.getNum() + 1;
        Integer rows = cartMapper.updateNumByCid(cid, num, username, new Date());
        if (rows != 1) {
            throw new UpdateException("更新数据时产生未知异常");
        }
        return num;
    }

    @Override
    public Integer subNum(Integer cid, Integer uid, String username) {
        Cart result = cartMapper.findByCid(cid);
        if (result == null) {
            throw new CartNotFoundException("数据不存在");
        }
        if (!result.getUid().equals(uid)) {
            throw new AccessDeniedException("数据非法访问");
        }
        Integer num = result.getNum() - 1;
        Integer rows = cartMapper.updateNumByCid(cid, num, username, new Date());
        if (rows != 1) {
            throw new UpdateException("更新数据时产生未知异常");
        }
        return num;
    }

    @Override
    public void deleteBycidS(Integer cid) {

        Cart result = cartMapper.findByCid(cid);
        if(result == null){
            throw new CartNotFoundException("找不到商品");
        }

        //检测当前获取到的收货地址是数据归属
        if(!result.getCid().equals(cid)){
            throw new AccessDeniedException("非法访问");
        }

        Integer row = cartMapper.deleteByCid(cid);
        if(row != 1){
            throw new DeleteException("删除时候产生未知的异常");
        }
        Integer count = cartMapper.countBycid(cid);
        if(count == 0){
            return;
        }
    }
}
