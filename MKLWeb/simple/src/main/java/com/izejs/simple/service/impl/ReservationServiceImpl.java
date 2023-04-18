package com.izejs.simple.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.izejs.simple.dto.ReservationDTO;
import com.izejs.simple.entity.Reservation;
import com.izejs.simple.mapper.ReservationMapper;
import com.izejs.simple.service.IReservationService;
import com.izejs.simple.service.IWashingMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author KunKa
 */
@Service
public class ReservationServiceImpl extends ServiceImpl<ReservationMapper, Reservation> implements IReservationService {

    @Autowired
    private ReservationMapper reservationMapper;
    @Resource
    private IWashingMachineService washingMachineService;


    /**
     * 根据条件查询所有的预约管理
     * @param page
     * @param reservation
     * @return
     */
    @Override
    public IPage<Reservation> getAllReservation(Page page, ReservationDTO reservation) {
        LambdaQueryWrapper<Reservation> queryWrapper = new LambdaQueryWrapper<>();

        if(reservation.getBrand() != null && !"".equals(reservation.getBrand())){
            queryWrapper = queryWrapper.like(Reservation::getBrand, reservation.getBrand());
        }
            if(reservation.getBrandType() != null && !"".equals(reservation.getBrandType())){
            queryWrapper = queryWrapper.like(Reservation::getBrandType, reservation.getBrandType());
        }
            if(reservation.getWashingMachineNumber() != null && !"".equals(reservation.getWashingMachineNumber())){
            queryWrapper = queryWrapper.like(Reservation::getWashingMachineNumber, reservation.getWashingMachineNumber());
        }
            if(reservation.getStatus() != null && !"".equals(reservation.getStatus())){
            queryWrapper = queryWrapper.like(Reservation::getStatus, reservation.getStatus());
        }

        return reservationMapper.selectPage(page, queryWrapper);
    }

    /**
     * 查询所有预约记录
     * @param reservationQueryWrapper
     * @return
     */
    @Override
    public List<Reservation> selectList(LambdaQueryWrapper<Reservation> reservationQueryWrapper) {
        return reservationMapper.selectList(reservationQueryWrapper);
    }

    /**
     * 预约洗衣结束
     * @param reservation
     */
    @Override
    public void washingEnd(Reservation reservation) {
        Integer washingMachineId = reservation.getWashingMachineId();
        washingMachineService.resetWashingMachineStatus(washingMachineId);
        reservation.setStatus(2);
        reservationMapper.updateById(reservation);
    }

    /**
     * 预约逾期
     * @param reservation
     */
    @Override
    public void overdue(Reservation reservation) {
        Integer washingMachineId = reservation.getWashingMachineId();
        washingMachineService.resetWashingMachineStatus(washingMachineId);
        reservation.setStatus(3);
        reservationMapper.updateById(reservation);
    }

}
