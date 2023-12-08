package com.demos.Controller;

import com.demos.entity.Address;
import com.demos.service.IAddressService;
import com.demos.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController//== @Controller + @RequestBody 表示此方法的响应结果以json格式进行数据的响应到前端
@RequestMapping("addresses")
public class AddressController extends BaseController{
    @Autowired
    private IAddressService addressService;

    @RequestMapping("add_new_address")
    public JsonResult<Void> addNewAddress(Address address, HttpSession session){
        Integer uid =getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.addNewAddress(uid,username,address);
        return new JsonResult<>(OK);
    }
    @RequestMapping({"","/"})
    public JsonResult<List<Address>> getByUid(Address address, HttpSession session){
        Integer uid = getUidFromSession(session);
        List<Address> data = addressService.getByUid(uid);
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("{aid}/set_default")
    public JsonResult<Void> setDefalt(@PathVariable("aid")Integer aid,HttpSession session){
        addressService.updateSetDefault(
                aid,
                getUidFromSession(session),
                getUsernameFromSession(session)
        );
        return new JsonResult<>(OK);
    }

    @RequestMapping("{aid}/delete")
    public JsonResult<Void> delete(@PathVariable("aid")Integer aid,HttpSession session){
       addressService.delete(
               aid,
               getUidFromSession(session),
               getUsernameFromSession(session)
       );
        return new JsonResult<>(OK);
    }


}
