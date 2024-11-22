package com.camila.pay;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.camila.pay.mapper")
@SpringBootApplication
public class CamilaPayApplication {
    public static void main(String[] args) {
        SpringApplication.run(CamilaPayApplication.class, args);
    }
}
