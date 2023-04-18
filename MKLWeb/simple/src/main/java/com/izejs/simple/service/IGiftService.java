package com.izejs.simple.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.izejs.simple.entity.Gift;
import com.izejs.simple.dto.GiftDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author KunKa
 */
public interface IGiftService extends IService<Gift> {
    IPage<Gift> getAllGift(Page page, GiftDTO gift);
}
