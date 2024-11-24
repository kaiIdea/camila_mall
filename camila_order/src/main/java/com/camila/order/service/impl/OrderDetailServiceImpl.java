package com.camila.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.camila.feign.domain.po.OrderDetail;
import com.camila.order.mapper.OrderDetailMapper;
import com.camila.order.service.IOrderDetailService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单详情表 服务实现类
 * </p>
 *
 * @author camila
 * @since 2023-05-05
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements IOrderDetailService {

}
