package com.izejs.simple.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.izejs.simple.dto.GiftDTO;
import com.izejs.simple.entity.Gift;
import com.izejs.simple.mapper.GiftMapper;
import com.izejs.simple.service.IGiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author KunKa
 */
@Service
public class GiftServiceImpl extends ServiceImpl<GiftMapper, Gift> implements IGiftService {

    @Autowired
    private GiftMapper giftMapper;


    /**
     * 根据条件查询所有的积分商品
     * @param page
     * @param gift
     * @return
     */
    @Override
    public IPage<Gift> getAllGift(Page page, GiftDTO gift) {
        LambdaQueryWrapper<Gift> queryWrapper = new LambdaQueryWrapper<>();

        if(gift.getName() != null && !"".equals(gift.getName())){
            queryWrapper = queryWrapper.eq(Gift::getName, gift.getName());
        }

        return giftMapper.selectPage(page, queryWrapper);
    }

}
