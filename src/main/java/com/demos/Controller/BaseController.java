package com.demos.Controller;

import com.demos.Controller.ex.*;
import com.demos.service.ex.*;
import com.demos.utils.JsonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

//控制层 异常的基类
public class BaseController {
    public static final int OK=200;
    //请求处理方法：这个方法的返回值就是需要传递给前端的数据
    //自动将异常对象传递给此方法的阐述列表上
    //当项目中产生了异常，被统一拦截到此方法中，这个方法此时就充当的是请求处理方法，方法的返回值返回到前端中
    @ExceptionHandler({ServiceException.class,FileUploadException.class})
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result =new JsonResult<>(e);
        //如果异常数据 该类型  就做下列操作
        if(e instanceof UsernameDuplicateException){
            result.setState(4000);
            result.setMessage("用户名被占用");
        }else if(e instanceof UserNotFoundException){
            result.setState(5001);
            result.setMessage("用户不存在");
        }else if(e instanceof PasswordNotMatchException){
            result.setState(5002);
            result.setMessage("用户的密码错误异常");
        }
        else if(e instanceof ServiceException){
            result.setState(5003);
            result.setMessage("注册时候产生位置的异常");
        } else if(e instanceof UpdateException){
            result.setState(5004);
            result.setMessage("更新数据时产生未知的异常");
        }

        else if(e instanceof FileEmptyException){
            result.setState(6000);
        }else if(e instanceof FileSizeException){
            result.setState(6001);
        }else if(e instanceof FileTypexception){
            result.setState(6002);
        }else if(e instanceof FileStateException){
            result.setState(6003);
        }else if(e instanceof FileUploadIOException){
            result.setState(6004);
        }
        return result;
    }

    //当前用户登录的数据设置，设置到全局的session中
    protected final Integer getUidFromSession(HttpSession session){
       return Integer.valueOf(session.getAttribute("uid").toString());
    }
    protected final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }

}
