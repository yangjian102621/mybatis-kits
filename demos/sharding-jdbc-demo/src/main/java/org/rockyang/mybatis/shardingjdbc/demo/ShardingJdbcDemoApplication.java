package org.rockyang.mybatis.shardingjdbc.demo;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * mybatis-kits spring boot demo application
 * @author yangjian
 */
@SpringBootApplication
@MapperScan("org.rockyang.mybatis.shardingjdbc.demo.mapper")
public class ShardingJdbcDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcDemoApplication.class, args);
    }
}
