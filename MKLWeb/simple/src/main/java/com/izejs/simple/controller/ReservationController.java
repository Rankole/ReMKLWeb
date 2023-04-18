package com.izejs.simple.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.api.R;
import com.izejs.simple.entity.Reservation;
import com.izejs.simple.dto.ReservationDTO;
import com.izejs.simple.service.IReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Arrays;


@Controller
public class ReservationController {

    @Resource
    private IReservationService reservationService;


    /**
     * 新增预约管理
     * @param reservation
     * @param session
     * @return
     */
    @PostMapping("/api/addReservation")
    @ResponseBody
    public R addReservation(Reservation reservation, HttpSession session){
        reservation.setStatus(0);
        reservationService.save(reservation);
        return R.ok(null);
    }

    /**
     * 根据id批量删除预约管理
     * @param session
     * @param reservationIds
     * @return
     */
    @DeleteMapping("/api/deleteReservationByIds/{reservationIds}")
    @ResponseBody
    public R deleteReservationByIds(HttpSession session, @PathVariable String reservationIds){
        // 将批量删除预约管理的id根据逗号切割成数组
        String[] ids = reservationIds.split(",");
        reservationService.removeByIds(Arrays.asList(ids));
        return R.ok(null);
    }


    /**
     * 根据预约管理id获取详情
     * @param reservationId
     * @return
     */
    @GetMapping("/api/getReservationById")
    @ResponseBody
    public R getReservationById(Integer reservationId){
        Reservation reservation = reservationService.getById(reservationId);
        return R.ok(reservation);
    }

    /**
     * 查询所有的reservation
     * @param session
     * @param reservationDTO
     * @param page
     * @return
     */
    @GetMapping("/api/getReservationList")
    @ResponseBody
    public R getReservationList(HttpSession session, ReservationDTO reservationDTO, Page page){

        IPage<Reservation> reservationPage = reservationService.getAllReservation(page, reservationDTO);
        return R.ok(reservationPage);
    }


    /**
     * 修改预约管理
     * @param reservation
     * @return
     */
    @PostMapping("/api/updateReservation")
    @ResponseBody
    public R updateReservation(Reservation reservation){
        reservationService.updateById(reservation);
        return R.ok(null);
    }


}
