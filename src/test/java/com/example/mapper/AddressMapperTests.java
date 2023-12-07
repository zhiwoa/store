package com.example.mapper;

import com.demos.entity.Address;
import com.demos.entity.User;
import com.demos.mapper.AddressMapper;
import com.demos.mapper.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest//表示标注当前的类是一个测试类，不会随同项目打包发送
//@RunWith(SpringRunner.class)
public class AddressMapperTests {
    //userMapper  会报错：idea有检测的功能，接口是不能直接创建bean的（动态代理技术来解决）
    //@Repository  在接口mapper加这个注解就不会报错；将持久层接口的实现类交给spring管理，并封装数据访问异常
    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insert(){
        Address address=new Address();
        address.setUid(12);
        address.setPhone("1321321");
        address.setName("哈哈哈哈哈");
        addressMapper.insert(address);
    }

    @Test
    public void selectByuid(){
        int rows=addressMapper.countByUid(12);
        System.out.println(rows);
    }

    @Test
    public void findyByUid(){
        List<Address> addresses = addressMapper.findyByUid(34);
        for(Address a:addresses){
            System.out.println(a+" ");
        }
    }
    @Test
   public  void findByAid(){
        Address byAid = addressMapper.findByAid(10);
        System.out.println(byAid.toString());
    }
   @ Test
   public  void updateNonDefault(){
        addressMapper.updateNonDefault(34);
    }

     @Test
    public void updateDefaultByAid(){
        addressMapper.updateDefaultByAid(11,"guanliyuan",new Date());
    }




}
