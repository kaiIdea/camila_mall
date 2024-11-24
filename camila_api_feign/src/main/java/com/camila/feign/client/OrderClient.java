package com.camila.feign.client;


import com.camila.feign.domain.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("camila-order")
public interface OrderClient {


    @PutMapping("/updateById")
    void updateById(@RequestBody OrderDTO order);
}
