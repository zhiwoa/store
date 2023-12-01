package com.demos.Controller;

import com.demos.Controller.ex.*;
import com.demos.entity.User;
import com.demos.service.IUserService;
import com.demos.service.ex.PasswordNotMatchException;
import com.demos.utils.JsonResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    public JsonResult<Void> reg(User user,
                                HttpSession session,
                                @RequestParam("password") String password,
                                @RequestParam("registernewpassword") String registernewpassword){
        JsonResult<Void> result =new JsonResult<>();

        JsonResult<Void> voidJsonResult = handleException(new Throwable());
        System.out.println(voidJsonResult.getState());
        if(!password.equals(registernewpassword)){
            result.setState(123);
            return new JsonResult<>(result.getState());
        }else{
            userService.reg(user);
        }

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
                                           HttpSession session, @RequestParam("newPassword") String password,
                                           @RequestParam("midifynewpassword") String midifynewpassword){
        if(!password.equals(midifynewpassword)){
            throw new PasswordNotMatchException("密码不匹配");
        }else{
            Integer uid =getUidFromSession(session);//获取之前登录时候存入的uid session
            String username=getUsernameFromSession(session);//获取之前登录时候存入的username session
            userService.changePassword(uid,username,oldPassword,newPassword);
        }
        return new JsonResult<>(OK);
    }

    //用过uid获取数据显示到修改个人信息的表单中
    @RequestMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session){
        User data = userService.getByuid(getUidFromSession(session));
//        System.out.println(getUidFromSession(session));
        //http://localhost:8080/users/get_by_uid  测试访问查看页面是否有数据
        return new JsonResult<>(OK,data);
    }

    //完成个人信息修改操作
    @RequestMapping("change_info")
    public JsonResult<User> changeInfo(User user,HttpSession session){
        //user 对象有username，phone，email。gender
        // uid 数据需要再次封装到user对象中
        Integer uid =getUidFromSession(session);
        String username=getUsernameFromSession(session);
        userService.changeInfo(uid,username,user);

        //写完程序之后测试一下
//        http://localhost:8080/users/change_info?phone=987654321&email=test7@aaq&gender=0
        return new JsonResult<>(OK);
    }

    //上传头像
    //MultipartFile  是SpringMvc提供的一个接口，包装了获取文件类型的数据（任何类型file都能接收）
    public static final int AVATAR_MAX_SIZE=10*1024;//设置上传文件的最大值
    public static final List<String>  AVATAR_TYPE = new ArrayList<>();
    static {
//        需要s  没有s运行不成功
        AVATAR_TYPE.add("images/jpeg");
        AVATAR_TYPE.add("images/png");
        AVATAR_TYPE.add("images/bmp");
        AVATAR_TYPE.add("images/gif");
    }
    @RequestMapping("change_avatar")
    public JsonResult<String>changeAvatar(HttpSession session,
                                       @RequestParam("file") MultipartFile file){

        //判断文件是否为空
        if(file.isEmpty()){
            throw new FileEmptyException("文件为空");
        }
        //判断大小是否超出限制
        if(file.getSize() > AVATAR_MAX_SIZE){
            throw new FileSizeException("文件超出限制");
        }
        //文件的类型是否是我们规定的后缀类型
        String contenType = file.getContentType();
        if(AVATAR_TYPE .contains(contenType)){
            throw new FileTypexception("文件类型不支持");
        }
        //上传的文件.../upload/文件/.png
        String parent = session.getServletContext().getRealPath("upload");

        //File对象指向这个路径，File是否存在
        File dir =new File(parent);
        if(!dir.exists()){//检测目录是否存在
            dir.mkdir();//创建当前的目录
        }
        //获取这个文件名称，UUID工具来讲生成一个新的字符串作为文件名
        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        String filename= UUID.randomUUID().toString().toString()+suffix;

        //上传的文件.../upload/文件/.png
        //                   文件
        File dest=new File(dir,filename);//是一个空文件
        //参数file中的数据写到这个空文件中
        try {
            file.transferTo(dest);//将file文件中数据写到dest文件中
        } catch (FileStateException e) {
            throw new FileSizeException("文件状态异常");
        }
        catch (IOException e) {
            throw new FileUploadIOException("文件读写异常");
        }

        Integer uid=getUidFromSession(session);
        String username=getUsernameFromSession(session);
        String avatar="/upload/"+filename;
        //返回头像的路径 / upload / text .png
        userService.changeAvatar(uid,avatar,username);
        //返回用户头像的路径黑前端页面，将来用于头像展示使用
        return  new JsonResult<>(OK,avatar);
    }

}
