package com.camila.order;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.camila.order.mapper")
@SpringBootApplication
public class CamilaOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(CamilaOrderApplication.class, args);
    }
}
