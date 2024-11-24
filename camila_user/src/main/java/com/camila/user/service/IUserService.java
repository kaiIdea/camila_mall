package com.camila.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.camila.common.domain.po.User;
import com.camila.feign.domain.dto.LoginFormDTO;
import com.camila.feign.domain.vo.UserLoginVO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author camila
 * @since 2023-05-05
 */
public interface IUserService extends IService<User> {

    UserLoginVO login(LoginFormDTO loginFormDTO);

    void deductMoney(String pw, Integer totalFee);
}
