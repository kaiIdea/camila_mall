package com.camila.feign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

public class DefaultFeignConfig {


    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }


//    public RequestInterceptor userInfoRequestInterceptor(){
//        return new RequestInterceptor() {
//            @Override
//            public void apply(RequestTemplate requestTemplate) {
//
//            }
//        }
//    }
}
