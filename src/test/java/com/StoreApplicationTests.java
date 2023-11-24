package com;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class StoreApplicationTests {

    @Autowired//自动装配
    private DataSource dataSource;

    @Test
     void getConnnection(){
       try {
         System.out.println(dataSource.getConnection());
         //HikariProxyConnection@261477965 wrapping com.mysql.cj.jdbc.ConnectionImpl@288728e
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
 }
}
