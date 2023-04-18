package com.izejs.simple.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.izejs.simple.entity.*;
import com.izejs.simple.service.*;
import com.izejs.simple.vo.PaymentModeVO;
import com.izejs.simple.dto.WashingMachineDTO;
import com.izejs.simple.vo.BrandTypeVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Controller
public class WashingMachineController {

    @Resource
    private IWashingMachineService washingMachineService;
    @Resource
    private IWashingModelService washingModelService;
    @Resource
    private IBrandTypeService brandTypeService;
    @Resource
    private IUsedService usedService;
    @Resource
    private IReservationService reservationService;
    @Resource
    private IBrandService brandService;
    @Resource
    private IRepairService repairService;
    @Resource
    private IUserGiftService userGiftService;
    @Resource
    private IUserService userService;


    /**
     * 新增洗衣机
     * @param washingMachine
     * @param session
     * @return
     */
    @PostMapping("/api/addWashingMachine")
    @ResponseBody
    public R addWashingMachine(WashingMachine washingMachine, HttpSession session){
        BrandType brandType = brandTypeService.getById(washingMachine.getTypeId());
        washingMachine.setBrandName(brandType.getBrandName());
        washingMachine.setTypeName(brandType.getTypeName());
        washingMachineService.save(washingMachine);
        return R.ok(null);
    }

    /**
     * 根据id批量删除洗衣机
     * @param session
     * @param washingMachineIds
     * @return
     */
    @DeleteMapping("/api/deleteWashingMachineByIds/{ids}")
    @ResponseBody
    public R deleteWashingMachineByIds(HttpSession session, @PathVariable(value = "ids") String washingMachineIds){
        // 将批量删除洗衣机的id根据逗号切割成数组
        String[] ids = washingMachineIds.split(",");
        washingMachineService.removeByIds(Arrays.asList(ids));
        return R.ok(null);
    }


    /**
     * 根据洗衣机id获取详情
     * @param washingMachineId
     * @return
     */
    @GetMapping("/api/getWashingMachineById")
    @ResponseBody
    public R getWashingMachineById(Integer washingMachineId){
        WashingMachine washingMachine = washingMachineService.getById(washingMachineId);
        return R.ok(washingMachine);
    }

    /**
     * 查询所有的washingMachine
     * @param session
     * @param washingMachineDTO
     * @param page
     * @return
     */
    @GetMapping("/api/getWashingMachineList")
    @ResponseBody
    public R getWashingMachineList(HttpSession session, WashingMachineDTO washingMachineDTO, Page page){

        IPage<WashingMachine> washingMachinePage = washingMachineService.getAllWashingMachine(page, washingMachineDTO);
        return R.ok(washingMachinePage);
    }


    /**
     * 修改洗衣机
     * @param washingMachine
     * @return
     */
    @PostMapping("/api/updateWashingMachine")
    @ResponseBody
    public R updateWashingMachine(WashingMachine washingMachine){
        BrandType brandType = brandTypeService.getById(washingMachine.getTypeId());
        washingMachine.setBrandName(brandType.getBrandName());
        washingMachine.setTypeName(brandType.getTypeName());
        washingMachineService.updateById(washingMachine);
        return R.ok(null);
    }

    /**
     * 使用洗衣机
     * @param washingMachineId
     * @param session
     * @return
     */
    @PostMapping("/api/usedWashingMachine")
    @ResponseBody
    public R usedWashingMachine(Integer washingMachineId, Integer washingModelId, Integer reservationId, Integer userGiftId, HttpSession session){
        // 获取当前登录用户
        User user = (User)session.getAttribute("loginUser");
        // 根据id查询洗衣机
        WashingMachine washingMachine = washingMachineService.getById(washingMachineId);
        WashingModel washingModel = washingModelService.getById(washingModelId);
        LambdaUpdateWrapper<WashingMachine> updateWrapper = new LambdaUpdateWrapper<>();
        Integer status = washingMachine.getStatus();
        // 判断洗衣机是否被预约
        if(status == 1){
            // 如果洗衣机已被预约, 判断预约人是不是当前登录用户
            if(washingMachine.getReservationUserId() != user.getId()){
                return R.failed("该洗衣机已被别人预约!无法使用!");
            }else{
                updateWrapper.set(WashingMachine::getReservationDatetime, null)
                             .set(WashingMachine::getReservationUserId, null)
                             .set(WashingMachine::getReservationUserName, null);

            }
        }
        // 判断洗衣机是否被使用
        if(status == 3){
            return R.failed("该洗衣机正在被别人使用!");
        }
        // 判断洗衣机是否被报修
        if(status == 3){
            return R.failed("该洗衣机已被报修!无法使用!");
        }

        // 如果预约id不为空, 更新预约信息
        if(reservationId != null){
            Reservation reservation = reservationService.getById(reservationId);
            reservation.setStatus(1);
            reservation.setUsedDatetime(LocalDateTime.now());
            reservationService.updateById(reservation);
        }else{
            R result = washingMachineService.payment(washingMachine, user, userGiftId, null, 0);
            if(result != null){
                return result;
            }
        }

        // 更新洗衣机使用者id和使用者姓名和开始使用时间
        updateWrapper.set(WashingMachine::getUsedUserId, user.getId())
                     .set(WashingMachine::getUsedUserName, user.getUserName())
                     .set(WashingMachine::getUsedDatetime, LocalDateTime.now())
                     .set(WashingMachine::getStatus, 3)
                     .eq(WashingMachine::getId, washingMachine.getId());
        washingMachineService.update(updateWrapper);
        Used used = new Used();
        used.setBrand(washingMachine.getBrandName());
        used.setBrandType(washingMachine.getTypeName());
        used.setUsedDatetime(LocalDateTime.now());
        used.setUsedUserId(user.getId());
        used.setUsedUserName(user.getUserName());
        used.setWashingMachineId(washingMachineId);
        used.setWashingMachineNumber(washingMachine.getNumber());
        used.setWashingModel(washingModel.getModelName());
        used.setWashingModelId(washingModelId);
        used.setWashingTimes(washingModel.getRunTime());
        used.setRunTimes(washingModel.getRunTime());
        used.setStatus(1);
        usedService.save(used);
        return R.ok(null);
    }

    /**
     * 预约洗衣机
     * @param washingMachineId
     * @param session
     * @return
     */
    @PostMapping("/api/reservationWashingMachine")
    @ResponseBody
    public R reservationWashingMachine(Integer washingMachineId, Integer washingModelId, Integer userGiftId, HttpSession session){
        // 获取当前登录用户
        User user = (User)session.getAttribute("loginUser");
        Integer userId = user.getId();
        // 根据id查询洗衣机
        WashingMachine washingMachine = washingMachineService.getById(washingMachineId);
        WashingModel washingModel = washingModelService.getById(washingModelId);
        Integer status = washingMachine.getStatus();
        // 判断洗衣机是否被预约
        if(status == 1){
            // 如果洗衣机已被预约, 判断预约人是不是当前登录用户
            if(washingMachine.getReservationUserId() != user.getId()){
                return R.failed("该洗衣机已被别人预约!无法预约!");
            }else{
                return R.failed("您已经预约过该洗衣机!");
            }
        }
        // 判断洗衣机是否被使用
        if(status == 3){
            return R.failed("该洗衣机正在被别人使用!无法预约!");
        }
        // 判断洗衣机是否被报修
        if(status == 3){
            return R.failed("该洗衣机已被报修!无法预约!");
        }
        if (userGiftId == -1) {
            BigDecimal price = washingMachine.getPrice();
            BigDecimal balance = user.getBalance();
            BigDecimal nowBalance = balance.subtract(price);
            if (nowBalance.compareTo(new BigDecimal(0)) == -1) {
                return R.failed("您的余额不足, 请充值!");
            }
        }else{
            UserGift userGift = userGiftService.getById(userGiftId);
            if (userGift == null) {
                return R.failed("该洗衣券不存在!");
            }
            Integer amount = userGift.getAmount();
            if (amount <= 0) {
                return R.failed("该洗衣券数量不足!");
            }
        }
        // 更新洗衣机预约者id和预约者姓名和预约时间
        washingMachine.setReservationUserId(userId);
        washingMachine.setStatus(1);
        washingMachine.setReservationUserName(user.getUserName());
        washingMachine.setReservationDatetime(LocalDateTime.now());
        washingMachineService.updateById(washingMachine);
        Reservation reservation = new Reservation();
        reservation.setBrand(washingMachine.getBrandName());
        reservation.setBrandType(washingMachine.getTypeName());
        reservation.setReserUserId(userId);
        reservation.setStatus(0);
        reservation.setRunTimes(washingModel.getRunTime());
        reservation.setReserUserName(user.getUserName());
        reservation.setReservationDatetime(LocalDateTime.now());
        reservation.setWashingMachineId(washingMachineId);
        reservation.setWashingMachineNumber(washingMachine.getNumber());
        reservation.setWashingModel(washingModel.getModelName());
        reservation.setWashingModelId(washingModelId);
        reservation.setWashingTimes(washingModel.getRunTime());
        reservationService.save(reservation);
        R result = washingMachineService.payment(washingMachine, user, userGiftId, reservation.getId(), 1);
        if(result != null){
            return result;
        }
        return R.ok(null);
    }


    /**
     * 获取品牌对象
     * @param brandId
     * @return
     */
    @GetMapping("/api/getBrand")
    @ResponseBody
    public R getBrand(Integer brandId){
        List<BrandType> brandTypeList = brandTypeService.getBrandTypesByBrandId(brandId);
        return R.ok(brandTypeList);
    }


    /**
     * 获取洗衣机品牌已经对应的型号
     * @return
     */
    @GetMapping("/api/getBrands")
    @ResponseBody
    public R getBrands(){
        List<BrandTypeVO> brandTypes = brandTypeService.getBrandTypes();
        return R.ok(brandTypes);
    }


    /**
     * 根据品牌和型号获取所有洗衣机
     * @param brand
     * @param brandType
     * @return
     */
    @GetMapping("/api/getWashingMachineListByBrand")
    @ResponseBody
    public R getWashingMachineList(Integer brand, Integer brandType){
        List<WashingMachine> washingMachineList = washingMachineService.getWashingMachineListByBrand(brand, brandType);
        return R.ok(washingMachineList);
    }

    /**
     * 获取所有的品牌
     * @return
     */
    @GetMapping("/api/getAllBrand")
    @ResponseBody
    public R getAllBrand(){
        List<Brand> list = brandService.getAllBrand();
        return R.ok(list);
    }

    /**
     * 根据品牌获取所有型号
     * @param brandId
     * @return
     */
    @GetMapping("/api/getBrandTypeByBrand")
    @ResponseBody
    public R getBrandTypeByBrand(Integer brandId){
        List<BrandType> brandTypeList = brandTypeService.getBrandTypesByBrandId(brandId);
        return R.ok(brandTypeList);
    }

    /**
     * 维修完成洗衣机
     * @return
     */
    @PostMapping("/api/serviceDone")
    @ResponseBody
    public R serviceDone(Integer repairId){
        Repair repair = repairService.getById(repairId);
        repair.setSolveDatetime(LocalDateTime.now());
        Integer washingMachineId = repair.getWashingMachineId();
        washingMachineService.resetWashingMachineStatus(washingMachineId);
        repair.setStatus(1);
        repairService.updateById(repair);
        return R.ok(null);
    }

    /**
     * 获取个人余额与洗衣券
     * @return
     */
    @GetMapping("/api/getPaymentModeInfo")
    @ResponseBody
    public R getPaymentModeInfo(HttpSession session){
        PaymentModeVO paymentModeVO = new PaymentModeVO();
        User loginUser = (User)session.getAttribute("loginUser");
        loginUser = userService.getById(loginUser.getId());
        BigDecimal balance = loginUser.getBalance();
        paymentModeVO.setBalance(balance);
        LambdaQueryWrapper<UserGift> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserGift::getUserId, loginUser.getId())
                    .gt(UserGift::getAmount, 0);
        List<UserGift> userGiftList = userGiftService.list(queryWrapper);
        paymentModeVO.setUserGiftList(userGiftList);
        return R.ok(paymentModeVO);
    }
}
