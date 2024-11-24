package com.camila.pay;


import com.camila.feign.config.DefaultFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients(value = "com.camila.feign.client",defaultConfiguration = DefaultFeignConfig.class)
@MapperScan("com.camila.pay.mapper")
@SpringBootApplication
public class CamilaPayApplication {
    public static void main(String[] args) {
        SpringApplication.run(CamilaPayApplication.class, args);
    }
}
