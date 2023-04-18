package com.izejs.simple.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.izejs.simple.entity.PaymentInfo;
import com.izejs.simple.dto.PaymentInfoDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author KunKa
 */
public interface IPaymentInfoService extends IService<PaymentInfo> {
    IPage<PaymentInfo> getAllPaymentInfo(Page page, PaymentInfoDTO paymentInfo);
}
