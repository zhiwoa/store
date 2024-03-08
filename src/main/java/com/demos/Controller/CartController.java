package com.demos.Controller;

import com.demos.entity.BaseEntity;
import com.demos.service.ICartService;
import com.demos.utils.JsonResult;
import com.demos.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("carts")
public class CartController extends BaseController {
    @Autowired
    private ICartService cartService;

    //加入购物车
    @RequestMapping("add_to_cart")
    public JsonResult<Void> addToCart(Integer pid, Integer amount, HttpSession session){
        cartService.addToCart(
                getUidFromSession(session),
                pid,
                amount,
                getUsernameFromSession(session));
                return new JsonResult<Void>(OK);
    }

    //展示购物车
    @RequestMapping({"", "/"})
    public JsonResult<List<CartVO>> getVOByUid(HttpSession session) {
        List<CartVO> data = cartService.getBoByUid(getUidFromSession(session));
        return new JsonResult<List<CartVO>>(OK, data);
    }

    //更新（增加）购物车某一商品的数量和价格
    @RequestMapping("{cid}/num/add")
    public JsonResult<Integer> addNum(@PathVariable("cid") Integer cid, HttpSession session) {
//        System.out.println(cid+"46456465465456456");
        Integer data = cartService.addNum(
                cid,
                getUidFromSession(session),
                getUsernameFromSession(session));
        return new JsonResult<Integer>(OK, data);
    }

    //更新（减少）购物车某一商品的数量和价格
    @RequestMapping("{cid}/num/sub")
    public JsonResult<Integer> subNum(@PathVariable("cid") Integer cid, HttpSession session) {
        Integer data = cartService.subNum(
                cid,
                getUidFromSession(session),
                getUsernameFromSession(session));
        return new JsonResult<Integer>(OK, data);
    }

    @RequestMapping("{cid}/delete")
    public JsonResult<Void> delete(@PathVariable("cid")Integer cid,HttpSession session){
        cartService.deleteBycidS(cid);
        return new JsonResult<>(OK);
    }
    @RequestMapping("{cid}/deleteS")
    public JsonResult<Void> deleteS(Integer[] cids){
        cartService.deleteBycidAdd(cids);
        return new JsonResult<>(OK);
    }
}
