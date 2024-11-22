package com.camila.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.camila.common.domain.dto.OrderFormDTO;
import com.camila.common.domain.po.Order;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author camila
 * @since 2023-05-05
 */
public interface IOrderService extends IService<Order> {

    Long createOrder(OrderFormDTO orderFormDTO);

    void markOrderPaySuccess(Long orderId);
}
