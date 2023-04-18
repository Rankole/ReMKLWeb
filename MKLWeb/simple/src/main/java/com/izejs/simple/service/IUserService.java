package com.izejs.simple.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.izejs.simple.dto.UserDTO;
import com.izejs.simple.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author KunKa
 * @since 2021-03-07
 */
public interface IUserService extends IService<User> {

    User selectUserByUserName(String userName);

    IPage<User> getAllUser(Page page, UserDTO user);

}
