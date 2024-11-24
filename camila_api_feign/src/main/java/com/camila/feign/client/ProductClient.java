package com.camila.feign.client;


import com.camila.feign.domain.dto.ItemDTO;
import com.camila.feign.domain.dto.OrderDetailDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

@FeignClient("camila-product")
public interface ProductClient {

    @GetMapping("/items")
    List<ItemDTO> queryItemByIds(@RequestParam("ids") Collection<Long> ids);

    @PutMapping("/stock/deduct")
    void deductStock(@RequestBody List<OrderDetailDTO> items);


}
