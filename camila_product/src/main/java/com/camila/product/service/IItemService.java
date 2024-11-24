package com.camila.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.camila.feign.domain.dto.ItemDTO;
import com.camila.feign.domain.dto.OrderDetailDTO;
import com.camila.common.domain.po.Item;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author camila
 * @since 2023-05-05
 */
public interface IItemService extends IService<Item> {

    void deductStock(List<OrderDetailDTO> items);

    List<ItemDTO> queryItemByIds(Collection<Long> ids);
}
