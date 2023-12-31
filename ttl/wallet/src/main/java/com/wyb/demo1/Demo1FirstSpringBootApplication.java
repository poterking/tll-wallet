package com.wyb.demo1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wyb.demo1.mapper")
public class Demo1FirstSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(Demo1FirstSpringBootApplication.class, args);
    }

}
