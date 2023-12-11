package com.demos.Controller;

import com.demos.entity.BaseEntity;
import com.demos.service.ICartService;
import com.demos.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("carts")
public class CartController extends BaseController {
    @Autowired
    private ICartService cartService;

    @RequestMapping("add_to_cart")
    public JsonResult<Void> addToCart(Integer pid, Integer amount, HttpSession session){
        cartService.addToCart(
                getUidFromSession(session),
                pid,
                amount,
                getUsernameFromSession(session));
                return new JsonResult<Void>(OK);
    }
}
