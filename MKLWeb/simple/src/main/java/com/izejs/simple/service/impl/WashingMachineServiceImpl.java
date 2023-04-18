package com.izejs.simple.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.izejs.simple.entity.PaymentInfo;
import com.izejs.simple.entity.User;
import com.izejs.simple.entity.WashingMachine;
import com.izejs.simple.mapper.PaymentInfoMapper;
import com.izejs.simple.mapper.UserGiftMapper;
import com.izejs.simple.mapper.UserMapper;
import com.izejs.simple.mapper.WashingMachineMapper;
import com.izejs.simple.service.IWashingMachineService;
import com.izejs.simple.dto.WashingMachineDTO;
import com.izejs.simple.entity.UserGift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author KunKa
 */
@Service
public class WashingMachineServiceImpl extends ServiceImpl<WashingMachineMapper, WashingMachine> implements IWashingMachineService {

    @Autowired
    private WashingMachineMapper washingMachineMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserGiftMapper userGiftMapper;
    @Autowired
    private PaymentInfoMapper paymentInfoMapper;


    /**
     * 根据条件查询所有的洗衣机
     *
     * @param page
     * @param washingMachine
     * @return
     */
    @Override
    public IPage<WashingMachine> getAllWashingMachine(Page page, WashingMachineDTO washingMachine) {
        QueryWrapper<WashingMachine> queryWrapper = new QueryWrapper<>();

        if (washingMachine.getBrandName() != null && !"".equals(washingMachine.getBrandName())) {
            queryWrapper = queryWrapper.like("brand_name", washingMachine.getBrandName());
        }
        if (washingMachine.getNumber() != null && !"".equals(washingMachine.getNumber())) {
            queryWrapper = queryWrapper.like("number", washingMachine.getNumber());
        }
        if (washingMachine.getStatus() != null && !"".equals(washingMachine.getStatus())) {
            queryWrapper = queryWrapper.like("status", washingMachine.getStatus());
        }
        queryWrapper.orderByAsc("status");
        return washingMachineMapper.selectPage(page, queryWrapper);
    }

    /**
     * 重置洗衣机状态
     *
     * @param washingMachineId
     */
    @Override
    public void resetWashingMachineStatus(Integer washingMachineId) {
        LambdaUpdateWrapper<WashingMachine> updateWrapper = new LambdaUpdateWrapper<WashingMachine>();
        WashingMachine washingMachine = new WashingMachine();
        washingMachine.setId(washingMachineId);
        updateWrapper.set(WashingMachine::getRepairUserId, null)
                .set(WashingMachine::getRepairUserName, null)
                .set(WashingMachine::getRepairDatetime, null)
                .set(WashingMachine::getUsedUserId, null)
                .set(WashingMachine::getUsedUserName, null)
                .set(WashingMachine::getUsedDatetime, null)
                .set(WashingMachine::getReservationUserId, null)
                .set(WashingMachine::getReservationUserName, null)
                .set(WashingMachine::getReservationDatetime, null)
                .set(WashingMachine::getRepairRemark, null)
                .set(WashingMachine::getStatus, 0)
                .eq(WashingMachine::getId, washingMachineId);
        washingMachineMapper.update(washingMachine, updateWrapper);
    }

    @Override
    public List<WashingMachine> selectList(LambdaQueryWrapper<WashingMachine> queryWrapper) {
        return washingMachineMapper.selectList(queryWrapper);
    }

    @Override
    public List<WashingMachine> getWashingMachineListByBrand(Integer brand, Integer brandType) {
        LambdaQueryWrapper<WashingMachine> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WashingMachine::getBrandId, brand)
                .eq(WashingMachine::getTypeId, brandType);
        return washingMachineMapper.selectList(queryWrapper);
    }

    /**
     * 使用,预约洗衣机支付
     * @param washingMachine
     * @param user
     * @param userGiftId
     * @return
     */
    @Override
    public R payment(WashingMachine washingMachine, User user, Integer userGiftId, Integer businessId, Integer type) {
        if (userGiftId == -1) {
            BigDecimal price = washingMachine.getPrice();
            BigDecimal balance = user.getBalance();
            BigDecimal nowBalance = balance.subtract(price);
            if (nowBalance.compareTo(new BigDecimal(0)) == -1) {
                return R.failed("您的余额不足, 请充值!");
            }
            user.setBalance(nowBalance);
            PaymentInfo paymentInfo = new PaymentInfo();
            if(type == 0 || type == null){
                paymentInfo.setPaymentExplain("使用洗衣机");
            }else{
                paymentInfo.setPaymentExplain("预约洗衣机");
            }
            paymentInfo.setType("支出");
            paymentInfo.setUserName(user.getUserName());
            paymentInfo.setUserId(user.getId());
            paymentInfo.setMoney(price);
            paymentInfo.setCreateDatetime(LocalDateTime.now());
            paymentInfo.setBusinessId(businessId);
            paymentInfoMapper.insert(paymentInfo);
            // 根据本次花费的钱获得积分, 获得消费金额1%的积分
            BigDecimal score = price.divide(new BigDecimal(100));
            user.setScore(user.getScore().add(score));
            userMapper.updateById(user);
            return null;
        } else {
            UserGift userGift = userGiftMapper.selectById(userGiftId);
            if (userGift == null) {
                return R.failed("该洗衣券不存在!");
            }
            Integer amount = userGift.getAmount();
            if (amount <= 0) {
                return R.failed("该洗衣券数量不足!");
            }
            userGift.setAmount(amount-1);
            userGiftMapper.updateById(userGift);
            return null;
        }
    }

}
