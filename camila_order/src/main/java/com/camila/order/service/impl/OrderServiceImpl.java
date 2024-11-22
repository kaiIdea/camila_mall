package com.camila.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.camila.common.domain.dto.ItemDTO;
import com.camila.common.domain.dto.OrderDetailDTO;
import com.camila.common.domain.dto.OrderFormDTO;
import com.camila.common.domain.po.Order;
import com.camila.common.domain.po.OrderDetail;
import com.camila.common.exception.BadRequestException;
import com.camila.common.utils.CollUtils;
import com.camila.common.utils.UserContext;
import com.camila.order.mapper.OrderMapper;
import com.camila.order.service.IOrderDetailService;
import com.camila.order.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author camila
 * @since 2023-05-05
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    //private final IItemService itemService;
    private final IOrderDetailService detailService;
    //private final ICartService cartService;

    @Override
    @Transactional
    public Long createOrder(OrderFormDTO orderFormDTO) {
        // 1.订单数据
        Order order = new Order();
        // 1.1.查询商品
        List<OrderDetailDTO> detailDTOS = orderFormDTO.getDetails();
        // 1.2.获取商品id和数量的Map
        Map<Long, Integer> itemNumMap = detailDTOS.stream()
                .collect(Collectors.toMap(OrderDetailDTO::getItemId, OrderDetailDTO::getNum));
        Set<Long> itemIds = itemNumMap.keySet();



        // 1.3.查询商品
        // TODO 微服务调用处理
        //List<ItemDTO> items = itemService.queryItemByIds(itemIds);
        RestTemplate template = new RestTemplate();
        ResponseEntity<List<ItemDTO>> responseEntity = template.exchange("http://localhost:8085/items", HttpMethod.GET, null, new ParameterizedTypeReference<List<ItemDTO>>() {
        }, CollUtils.join(itemIds, ","));
        List<ItemDTO> items = responseEntity.getBody();






        if (items == null || items.size() < itemIds.size()) {
            throw new BadRequestException("商品不存在");
        }
        // 1.4.基于商品价格、购买数量计算商品总价：totalFee
        int total = 0;
        for (ItemDTO item : items) {
            total += item.getPrice() * itemNumMap.get(item.getId());
        }
        order.setTotalFee(total);
        // 1.5.其它属性
        order.setPaymentType(orderFormDTO.getPaymentType());
        order.setUserId(UserContext.getUser());
        order.setStatus(1);
        // 1.6.将Order写入数据库order表中
        save(order);

        // 2.保存订单详情
        List<OrderDetail> details = buildDetails(order.getId(), items, itemNumMap);
        detailService.saveBatch(details);

        // 3.清理购物车商品
        // TODO 微服务调用处理
        //cartService.removeByItemIds(itemIds);
        ResponseEntity<String> response = template.exchange("http://localhost:8086/carts", HttpMethod.DELETE, null,
                String.class, CollUtils.join(itemIds, ","));
        System.out.println(response);


        // 4.扣减库存
        try {
            // TODO 微服务调用处理
            ResponseEntity<String> response1 = template.exchange("http://localhost:8085/items", HttpMethod.PUT, null,
                    String.class, details);
            System.out.println(response1);
            //itemService.deductStock(detailDTOS);
        } catch (Exception e) {
            throw new RuntimeException("库存不足！");
        }
        return order.getId();
    }

    @Override
    public void markOrderPaySuccess(Long orderId) {
        Order order = new Order();
        order.setId(orderId);
        order.setStatus(2);
        order.setPayTime(LocalDateTime.now());
        updateById(order);
    }

    private List<OrderDetail> buildDetails(Long orderId, List<ItemDTO> items, Map<Long, Integer> numMap) {
        List<OrderDetail> details = new ArrayList<>(items.size());
        for (ItemDTO item : items) {
            OrderDetail detail = new OrderDetail();
            detail.setName(item.getName());
            detail.setSpec(item.getSpec());
            detail.setPrice(item.getPrice());
            detail.setNum(numMap.get(item.getId()));
            detail.setItemId(item.getId());
            detail.setImage(item.getImage());
            detail.setOrderId(orderId);
            details.add(detail);
        }
        return details;
    }
}
