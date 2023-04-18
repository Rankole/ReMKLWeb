package com.izejs.simple.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.izejs.simple.dto.ReservationDTO;
import com.izejs.simple.entity.Reservation;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author KunKa
 */
public interface IReservationService extends IService<Reservation> {
    IPage<Reservation> getAllReservation(Page page, ReservationDTO reservation);

    List<Reservation> selectList(LambdaQueryWrapper<Reservation> reservationQueryWrapper);

    void washingEnd(Reservation reservation);

    void overdue(Reservation reservation);
}
