package com.izejs.simple.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.api.R;
import com.izejs.simple.dto.PaymentInfoDTO;
import com.izejs.simple.entity.PaymentInfo;
import com.izejs.simple.entity.User;
import com.izejs.simple.service.IPaymentInfoService;
import com.izejs.simple.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;


@Controller
public class PaymentInfoController {

    @Resource
    private IPaymentInfoService paymentInfoService;
    @Resource
    private IUserService userService;


    /**
     * 新增交易明细
     * @param paymentInfo
     * @param session
     * @return
     */
    @PostMapping("/api/addPaymentInfo")
    @ResponseBody
    public R addPaymentInfo(PaymentInfo paymentInfo, HttpSession session){
        paymentInfoService.save(paymentInfo);
        return R.ok(null);
    }

    /**
     * 根据id批量删除交易明细
     * @param session
     * @param paymentInfoIds
     * @return
     */
    @DeleteMapping("/api/deletePaymentInfoByIds/{paymentInfoIds}")
    @ResponseBody
    public R deletePaymentInfoByIds(HttpSession session, @PathVariable String paymentInfoIds){
        // 将批量删除交易明细的id根据逗号切割成数组
        String[] ids = paymentInfoIds.split(",");
        paymentInfoService.removeByIds(Arrays.asList(ids));
        return R.ok(null);
    }


    /**
     * 根据交易明细id获取详情
     * @param paymentInfoId
     * @return
     */
    @GetMapping("/api/getPaymentInfoById")
    @ResponseBody
    public R getPaymentInfoById(Integer paymentInfoId){
        PaymentInfo paymentInfo = paymentInfoService.getById(paymentInfoId);
        return R.ok(paymentInfo);
    }

    /**
     * 查询所有的paymentInfo
     * @param session
     * @param paymentInfoDTO
     * @param page
     * @return
     */
    @GetMapping("/api/getPaymentInfoList")
    @ResponseBody
    public R getPaymentInfoList(HttpSession session, PaymentInfoDTO paymentInfoDTO, Page page){
        User loginUser = (User)session.getAttribute("loginUser");
        if(loginUser.getRole() != 1){
            paymentInfoDTO.setUserId(loginUser.getId());
        }
        IPage<PaymentInfo> paymentInfoPage = paymentInfoService.getAllPaymentInfo(page, paymentInfoDTO);
        return R.ok(paymentInfoPage);
    }


    /**
     * 修改交易明细
     * @param paymentInfo
     * @return
     */
    @PostMapping("/api/updatePaymentInfo")
    @ResponseBody
    public R updatePaymentInfo(PaymentInfo paymentInfo){
        paymentInfoService.updateById(paymentInfo);
        return R.ok(null);
    }

    /**
     * 充值
     * @param money
     * @param session
     * @return
     */
    @PostMapping("/api/payment")
    @ResponseBody
    public R payment(BigDecimal money, HttpSession session){
        // 获取当前登录用户
        User loginUser = (User)session.getAttribute("loginUser");
        loginUser = userService.getById(loginUser.getId());
        if(loginUser == null){
            return R.failed("请重新登录后再充值!");
        }
        // 设置当前登录用户的余额
        BigDecimal balance = loginUser.getBalance();
        balance = balance.add(money);
        loginUser.setBalance(balance);
        // 更新当前登录用户
        userService.updateById(loginUser);
        // 创建交易记录对象
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setCreateDatetime(LocalDateTime.now());
        paymentInfo.setMoney(money);
        paymentInfo.setUserId(loginUser.getId());
        paymentInfo.setUserName(loginUser.getUserName());
        paymentInfo.setType("收入");
        paymentInfo.setPaymentExplain("充值");
        // 插入交易记录
        paymentInfoService.save(paymentInfo);
        return R.ok(null);
    }

}
