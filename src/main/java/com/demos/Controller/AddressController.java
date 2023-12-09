package com.demos.Controller;

import com.demos.entity.Address;
import com.demos.service.IAddressService;
import com.demos.service.ex.InsertException;
import com.demos.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
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
    @RequestMapping("queryOneAddress")
    public JsonResult<Address> queryOneAddress(Integer aid){
        Address address = addressService.queryAddressByAid(aid);
//        //过滤部分不需要的字段
        address.setModifiedTime(null);
        address.setModifiedUser(null);
        address.setCreatedTime(null);
        address.setCreatedUser(null);
        address.setIsDefault(0);
        return new JsonResult<>(OK,address);
    }

    @RequestMapping("updateAddress")
    public JsonResult<Void> updateOneAddress(Address address,HttpSession session){

        //取出session中用户名
        String modifiedUser = getUsernameFromSession(session);

        int result = addressService.updateOneAddress(address, modifiedUser);

        if (result == 0){
            throw new InsertException("修改地址时，服务器或数据库异常");
        }
        return new JsonResult<>(OK);
    }
}
