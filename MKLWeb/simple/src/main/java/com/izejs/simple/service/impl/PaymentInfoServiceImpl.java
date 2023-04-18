package com.izejs.simple.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.izejs.simple.entity.PaymentInfo;
import com.izejs.simple.mapper.PaymentInfoMapper;
import com.izejs.simple.dto.PaymentInfoDTO;
import com.izejs.simple.service.IPaymentInfoService;
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
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoMapper, PaymentInfo> implements IPaymentInfoService {

    @Autowired
    private PaymentInfoMapper paymentInfoMapper;


    /**
     * 根据条件查询所有的交易明细
     * @param page
     * @param paymentInfo
     * @return
     */
    @Override
    public IPage<PaymentInfo> getAllPaymentInfo(Page page, PaymentInfoDTO paymentInfo) {
        LambdaQueryWrapper<PaymentInfo> queryWrapper = new LambdaQueryWrapper<>();

        if(paymentInfo.getType() != null && !"".equals(paymentInfo.getType())){
            queryWrapper = queryWrapper.eq(PaymentInfo::getType, paymentInfo.getType());
        }
        if(paymentInfo.getPaymentExplain() != null && !"".equals(paymentInfo.getPaymentExplain())){
            queryWrapper = queryWrapper.eq(PaymentInfo::getPaymentExplain, paymentInfo.getPaymentExplain());
        }
        if(paymentInfo.getUserId() != null && !"".equals(paymentInfo.getUserId())){
            queryWrapper = queryWrapper.eq(PaymentInfo::getUserId, paymentInfo.getUserId());
        }

        return paymentInfoMapper.selectPage(page, queryWrapper);
    }

}
