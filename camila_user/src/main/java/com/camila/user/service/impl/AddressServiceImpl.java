package com.camila.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.camila.common.domain.po.Address;
import com.camila.user.mapper.AddressMapper;
import com.camila.user.service.IAddressService;
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
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

}
