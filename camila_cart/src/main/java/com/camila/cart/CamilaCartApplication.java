package com.camila.cart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.camila.cart.mapper")
@SpringBootApplication
public class CamilaCartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CamilaCartApplication.class, args);
    }
}