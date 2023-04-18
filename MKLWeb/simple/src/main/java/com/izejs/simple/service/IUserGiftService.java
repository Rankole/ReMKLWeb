package com.izejs.simple.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.izejs.simple.entity.UserGift;
import com.izejs.simple.dto.UserGiftDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author KunKa
 */
public interface IUserGiftService extends IService<UserGift> {
    IPage<UserGift> getAllUserGift(Page page, UserGiftDTO userGift);
}
