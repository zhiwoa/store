package com.example.mapper;

import com.demos.entity.Address;
import com.demos.entity.District;
import com.demos.mapper.AddressMapper;
import com.demos.mapper.DistrictMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest//表示标注当前的类是一个测试类，不会随同项目打包发送
//@RunWith(SpringRunner.class)
public class DistrictTests {
    //userMapper  会报错：idea有检测的功能，接口是不能直接创建bean的（动态代理技术来解决）
    //@Repository  在接口mapper加这个注解就不会报错；将持久层接口的实现类交给spring管理，并封装数据访问异常
    @Autowired
   private DistrictMapper districtMapper;

    @Test
    public void insert(){
        List<District> list = districtMapper.findByparent("210100");
        for(District d:list){
            System.out.println(d+"  ");
        }
    }
    @Test
    public void findNameByCode(){
        String name = districtMapper.findNameByCode("610000");
        System.out.println(name);
    }

}
