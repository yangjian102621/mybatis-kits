package org.rockyang.mybatis.springboot.demo;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * mybatis-kits spring boot demo application
 * @author yangjian
 */
@SpringBootApplication
@MapperScan("org.rockyang.mybatis.springboot.demo.mapper")
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }
}
