package com.demos.Controller;

import com.demos.entity.Order;
import com.demos.service.IOrderService;
import com.demos.utils.JsonResult;
import com.demos.vo.CartVO;
import com.demos.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController extends BaseController{
    @Autowired
    private IOrderService orderService;
    @RequestMapping("create")
    public JsonResult<Order> create(Integer aid, Integer[] cids, HttpSession session) {
        Order data = orderService.create(
                aid,
                cids,
                getUidFromSession(session),
                getUsernameFromSession(session));
        return new JsonResult<>(OK,data);
    }
    @RequestMapping({"", "/"})
    public JsonResult<List<OrderVO>> getOrderByUid(HttpSession session) {
        List<OrderVO> data = orderService.findOrderByUid(getUidFromSession(session));
        return new JsonResult<List<OrderVO>>(OK, data);
    }
}
