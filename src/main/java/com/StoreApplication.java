package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import javax.servlet.MultipartConfigElement;

@Configuration//表示配置类
@SpringBootApplication
//@MapperScan 注释指定当前项目中的Mapper接口路径的文字，在项目启动的时候会自动加载所有的接口
@MapperScan("com.demos.mapper")
public class StoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
    }

    @Bean
    public MultipartConfigElement MultipartConfigElement(){
        //配置一个配置的工厂类对象
        MultipartConfigFactory factory=new MultipartConfigFactory();

        //设置需要创建的对象的相关信息
        factory.setMaxFileSize(DataSize.of(10, DataUnit.MEGABYTES));
        factory.setMaxFileSize(DataSize.of(15, DataUnit.MEGABYTES));


        //通过工厂类来创建MultipartConfigElement
        return factory.createMultipartConfig();
    }
}
