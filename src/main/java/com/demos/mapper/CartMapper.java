package com.demos.mapper;

import com.demos.entity.Cart;
import com.demos.vo.CartVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

//购物车的Mapper接口
public interface CartMapper {

    /**
     * 插入购物车数据
     * @param cart 购物车的数据
     * @return 行数
     */
    Integer insert(Cart cart);

    /**
     * 修改购物车数据中商品的数量(购物车商品已经存在就更新数据)
     * @param cid 购物车数据的id
     * @param num 新的数量
     * @param modifiedUser 修改执行人
     * @param modifiedTime 修改时间
     * @return 受影响的行数
     */
    Integer updateNumByCid(@Param("cid") Integer cid,
                           @Param("num") Integer num,
                           @Param("modifiedUser") String modifiedUser,
                           @Param("modifiedTime") Date modifiedTime);
    /**
     * 在插入或者更像具体执行某一条有语句取决数据库中是否有当前的购物车商品的数据
     * 根据用户id和商品id查询购物车中的数据
     * @param uid 用户id
     * @param pid 商品id
     * @return 匹配的购物车数据，如果该用户的购物车中并没有该商品，则返回null
     */
    Cart findByUidAndPid(
            @Param("uid") Integer uid,
            @Param("pid") Integer pid);


    /**
     * 查询某用户的购物车数据
     * @param uid 用户的id
     * @return 用户的购物车数据列表
     */
    List<CartVO>findVoByUid(Integer uid);


    /**
     * 根据商品cid查询商品
     * @param cid 商品的id
     * @return 购物车的数据列表
     */
    Cart findByCid(Integer cid);


}
