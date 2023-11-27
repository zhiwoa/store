package com.demos.Controller;

import com.demos.entity.User;
import com.demos.service.IUserService;
import com.demos.service.ex.PasswordNotMatchException;
import com.demos.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

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

    /*1、接受数据方式：请求处理的方法的参数列表设置为pojo类型来接受前端的数据
    SpringBoot会将前端的Url地址中的参数名和pojo类的属性名进行比较，如果
    这两个名称项目，则将值注入到pojo对应属性上
    * */
    @RequestMapping("reg")
    public JsonResult<Void> reg(User user,HttpSession session){
        userService.reg(user);
        return new JsonResult<>(OK);
    }

    /*2、接受数据方式：请求处理的方法的参数列表设置为非pojo类型来接受前端的数据
SpringBoot会将前端的Url地址中的参数名和pojo类的属性名进行比较，如果
名称相同，则自动完成值的依赖注入
* */
    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session){
        User data = userService.login(username,password);
        //向session对象中完成数据的绑定（session全局的）
        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());

        //获取session中绑定的数据
//        System.out.println(getUidFromSession(session));
//        System.out.println(getUsernameFromSession(session));
        return new JsonResult<User>(OK,data);
    }

    // 修改密码
    @RequestMapping("change_Password")
    public JsonResult<Void> changePassword(String oldPassword,String newPassword,
                                           HttpSession session){
        Integer uid =getUidFromSession(session);//获取之前登录时候存入的uid session
        String username=getUsernameFromSession(session);//获取之前登录时候存入的username session
        userService.changePassword(uid,username,oldPassword,newPassword);
        return new JsonResult<>(OK);
    }
}
