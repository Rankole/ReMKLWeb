package com.izejs.simple.timer;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.izejs.simple.entity.*;
import com.izejs.simple.service.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class WashingMachineStatusTask {

    @Resource
    private IWashingMachineService washingMachineService;
    @Resource
    private IUsedService usedService;
    @Resource
    private IReservationService reservationService;
    @Resource
    private IWashingModelService washingModelService;
    @Resource
    private IUserService userService;
    @Resource
    private IPaymentInfoService paymentInfoService;

    @Scheduled(cron = "*/5 * * * * ?")
    public void updateWashingMachineUsedStatus(){
        LambdaQueryWrapper<Used> usedQueryWrapper = new LambdaQueryWrapper<>();
        usedQueryWrapper.eq(Used::getStatus, 1);
        LambdaQueryWrapper<Reservation> reservationQueryWrapper = new LambdaQueryWrapper<>();
        reservationQueryWrapper.eq(Reservation::getStatus, 0);
        LocalDateTime now = LocalDateTime.now();
        // 查询所有的使用中的洗衣机
        List<Used> usedList = usedService.selectList(usedQueryWrapper);
        // 查询所有的已预约未使用的洗衣机
        List<Reservation> reservationList = reservationService.selectList(reservationQueryWrapper);
        // 查询所有已预约已使用的洗衣机
        reservationQueryWrapper.clear();
        reservationQueryWrapper.eq(Reservation::getStatus, 1);
        List<Reservation> unUsedList = reservationService.selectList(reservationQueryWrapper);

        usedList.forEach(used -> {
            Integer washingModelId = used.getWashingModelId();
            LocalDateTime usedDatetime = used.getUsedDatetime();
            // 获取模式的运行时间
            WashingModel washingModel = washingModelService.getById(washingModelId);
            Integer runTime = washingModel.getRunTime();
            // 获取应该结束时间
            LocalDateTime shouldEndTime = usedDatetime.plusMinutes(runTime);
            if(now.isAfter(shouldEndTime)){
                usedService.washingEnd(used);
            }
        });

        unUsedList.forEach(reservation -> {
            Integer washingModelId = reservation.getWashingModelId();
            LocalDateTime usedDatetime = reservation.getUsedDatetime();
            // 获取模式的运行时间
            WashingModel washingModel = washingModelService.getById(washingModelId);
            Integer runTime = washingModel.getRunTime();
            // 获取应该结束时间
            LocalDateTime shouldEndTime = usedDatetime.plusMinutes(runTime);
            if(now.isAfter(shouldEndTime)){
                reservationService.washingEnd(reservation);
            }
        });

        reservationList.forEach(reservation -> {
            LocalDateTime reservationDatetime = reservation.getReservationDatetime();
            if(now.isAfter(reservationDatetime.plusMinutes(15))){
                reservationService.overdue(reservation);
                Integer reserUserId = reservation.getReserUserId();
                Integer washingMachineId = reservation.getWashingMachineId();
                User user = userService.getById(reserUserId);
                WashingMachine washingMachine = washingMachineService.getById(washingMachineId);
                BigDecimal price = washingMachine.getPrice();
                BigDecimal returnMoney = price.multiply(new BigDecimal(0.1));
                user.setBalance(user.getBalance().add(returnMoney));
                userService.updateById(user);
                PaymentInfo paymentInfo = new PaymentInfo();
                paymentInfo.setBusinessId(reservation.getId());
                paymentInfo.setCreateDatetime(LocalDateTime.now());
                paymentInfo.setMoney(returnMoney);
                paymentInfo.setUserId(user.getId());
                paymentInfo.setUserName(user.getUserName());
                paymentInfo.setType("收入");
                paymentInfo.setPaymentExplain("预约逾期退回");
                paymentInfoService.save(paymentInfo);
            }
        });

    }
}
