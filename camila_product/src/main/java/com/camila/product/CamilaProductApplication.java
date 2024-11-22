package com.camila.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.camila.product.mapper")
@SpringBootApplication
public class CamilaProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(CamilaProductApplication.class, args);
    }
}
