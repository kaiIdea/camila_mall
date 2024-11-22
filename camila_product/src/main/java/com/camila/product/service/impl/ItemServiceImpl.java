package com.camila.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.camila.common.domain.dto.ItemDTO;
import com.camila.common.domain.dto.OrderDetailDTO;
import com.camila.common.domain.po.Item;
import com.camila.common.exception.BizIllegalException;
import com.camila.common.utils.BeanUtils;
import com.camila.product.mapper.ItemMapper;
import com.camila.product.service.IItemService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author camila
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements IItemService {

    @Override
    public void deductStock(List<OrderDetailDTO> items) {
        String sqlStatement = "com.camila.product.mapper.ItemMapper.updateStock";
        boolean r = false;
        try {
            r = executeBatch(items, (sqlSession, entity) -> sqlSession.update(sqlStatement, entity));
        } catch (Exception e) {
            log.error("更新库存异常", e);
            return;
        }
        if (!r) {
            throw new BizIllegalException("库存不足！");
        }
    }

    @Override
    public List<ItemDTO> queryItemByIds(Collection<Long> ids) {
        return BeanUtils.copyList(listByIds(ids), ItemDTO.class);
    }
}
