package org.rockyang.mybatis.boot.demo;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * mybatis-kits spring boot demo application
 * @author yangjian
 */
@SpringBootApplication
@MapperScan("org.rockyang.mybatis.boot.demo.mapper")
public class MybatisDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisDemoApplication.class, args);
    }
}
