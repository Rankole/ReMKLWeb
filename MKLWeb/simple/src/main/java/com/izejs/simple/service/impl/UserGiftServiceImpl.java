package com.izejs.simple.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.izejs.simple.entity.UserGift;
import com.izejs.simple.mapper.UserGiftMapper;
import com.izejs.simple.dto.UserGiftDTO;
import com.izejs.simple.service.IUserGiftService;
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
public class UserGiftServiceImpl extends ServiceImpl<UserGiftMapper, UserGift> implements IUserGiftService {

    @Autowired
    private UserGiftMapper userGiftMapper;


    /**
     * 根据条件查询所有的我的积分商品
     * @param page
     * @param userGift
     * @return
     */
    @Override
    public IPage<UserGift> getAllUserGift(Page page, UserGiftDTO userGift) {
        LambdaQueryWrapper<UserGift> queryWrapper = new LambdaQueryWrapper<>();

        if(userGift.getGiftName() != null && !"".equals(userGift.getGiftName())){
            queryWrapper = queryWrapper.eq(UserGift::getGiftName, userGift.getGiftName());
        }
        queryWrapper.gt(UserGift::getAmount, 0);
        return userGiftMapper.selectPage(page, queryWrapper);
    }

}
