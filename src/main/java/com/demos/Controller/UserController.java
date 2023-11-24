package com.demos.Controller;

import com.demos.entity.User;
import com.demos.service.IUserService;
import com.demos.service.ex.InsertException;
import com.demos.service.ex.UsernameDuplicateException;
import com.demos.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController// == @Controller + @RequestBody 表示此方法的响应结果以json格式进行数据的响应到前端
@RequestMapping("users")//用于将任意HTTP 请求映射到控制器方法上。
public class UserController extends BaseController{
    @Autowired
    private IUserService userService;

//    @RequestMapping("reg")
//    public JsonResult<Void> reg(User user){
//        //创建响应结果对象
//        JsonResult<Void> result =new JsonResult<>();
//        try{
//            userService.reg(user);
//            result.setState(200);
//            result.setMessage("用户注册成功");
//        }catch(UsernameDuplicateException e){
//            result.setState(4000);
//            result.setMessage("用户名被占用");
//        }catch(InsertException e){
//            result.setState(5000);
//            result.setMessage("注册时产生位置的异常");
//        }
//        return result;

    @RequestMapping("reg")
    public JsonResult<Void> reg(User user){
        userService.reg(user);
        return new JsonResult<>(OK);
    }
}
