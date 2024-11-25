package com.camila.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.camila.common.domain.po.OrderLogistics;
import com.camila.order.mapper.OrderLogisticsMapper;
import com.camila.order.service.IOrderLogisticsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author camila
 * @since 2023-05-05
 */
@Service
public class OrderLogisticsServiceImpl extends ServiceImpl<OrderLogisticsMapper, OrderLogistics> implements IOrderLogisticsService {

}
