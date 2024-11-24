package com.camila.user;

import com.camila.feign.config.DefaultFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients(value = "com.camila.feign.client",defaultConfiguration = DefaultFeignConfig.class)
@MapperScan("com.camila.user.mapper")
@SpringBootApplication
public class CamilaUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(CamilaUserApplication.class, args);
    }
}
