package com.camila.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.camila.common.domain.po.PayOrder;
import com.camila.feign.domain.dto.PayApplyDTO;
import com.camila.feign.domain.dto.PayOrderFormDTO;

/**
 * <p>
 * 支付订单 服务类
 * </p>
 *
 * @author camila
 * @since 2023-05-16
 */
public interface IPayOrderService extends IService<PayOrder> {

    String applyPayOrder(PayApplyDTO applyDTO);

    void tryPayOrderByBalance(PayOrderFormDTO payOrderFormDTO);
}
