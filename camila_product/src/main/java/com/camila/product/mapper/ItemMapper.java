package com.camila.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.camila.feign.domain.dto.OrderDetailDTO;
import com.camila.common.domain.po.Item;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author camila
 * @since 2023-05-05
 */
public interface ItemMapper extends BaseMapper<Item> {

    @Update("UPDATE item SET stock = stock - #{num} WHERE id = #{itemId}")
    void updateStock(OrderDetailDTO orderDetail);
}
