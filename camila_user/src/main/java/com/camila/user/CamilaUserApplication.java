package com.camila.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.camila.user.mapper")
@SpringBootApplication
public class CamilaUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(CamilaUserApplication.class, args);
    }
}
