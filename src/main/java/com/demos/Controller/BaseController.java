package com.demos.Controller;

import com.demos.service.ex.ServiceException;
import com.demos.service.ex.UsernameDuplicateException;
import com.demos.utils.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

//控制层的基类
public class BaseController {
    public static final int OK=200;
    //请求处理方法：这个方法的返回值就是需要传递给前端的数据
    //自动将异常对象传递给此方法的阐述列表上
    //当项目中产生了异常，被统一拦截到此方法中，这个方法此时就充当的是请求处理方法，方法的返回值返回到前端中
    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result =new JsonResult<>(e);
        //如果异常数据 该类型  就做下列操作
        if(e instanceof UsernameDuplicateException){
            result.setState(4000);
            result.setMessage("用户名被占用");
        }else if(e instanceof ServiceException){
            result.setState(5000);
            result.setMessage("注册时候产生位置的异常");
        }
        return result;
    }
}
