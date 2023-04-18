package com.izejs.simple.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.api.R;
import com.izejs.simple.dto.GiftDTO;
import com.izejs.simple.entity.Brand;
import com.izejs.simple.entity.Gift;
import com.izejs.simple.entity.User;
import com.izejs.simple.service.IBrandService;
import com.izejs.simple.service.IGiftService;
import com.izejs.simple.entity.UserGift;
import com.izejs.simple.service.IUserGiftService;
import com.izejs.simple.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Arrays;


@Controller
public class GiftController {

    @Resource
    private IGiftService giftService;
    @Resource
    private IBrandService brandService;
    @Resource
    private IUserGiftService userGiftService;
    @Resource
    private IUserService userService;


    /**
     * 新增积分商品
     * @param gift
     * @param session
     * @return
     */
    @PostMapping("/api/addGift")
    @ResponseBody
    public R addGift(Gift gift, HttpSession session){
        Brand brand = brandService.getById(gift.getUsedId());
        gift.setUsedName(brand.getName());
        giftService.save(gift);
        return R.ok(null);
    }

    /**
     * 根据id批量删除积分商品
     * @param session
     * @param giftIds
     * @return
     */
    @DeleteMapping("/api/deleteGiftByIds/{giftIds}")
    @ResponseBody
    public R deleteGiftByIds(HttpSession session, @PathVariable String giftIds){
        // 将批量删除积分商品的id根据逗号切割成数组
        String[] ids = giftIds.split(",");
        giftService.removeByIds(Arrays.asList(ids));
        return R.ok(null);
    }


    /**
     * 根据积分商品id获取详情
     * @param giftId
     * @return
     */
    @GetMapping("/api/getGiftById")
    @ResponseBody
    public R getGiftById(Integer giftId){
        Gift gift = giftService.getById(giftId);
        return R.ok(gift);
    }

    /**
     * 查询所有的gift
     * @param session
     * @param giftDTO
     * @param page
     * @return
     */
    @GetMapping("/api/getGiftList")
    @ResponseBody
    public R getGiftList(HttpSession session, GiftDTO giftDTO, Page page){

        IPage<Gift> giftPage = giftService.getAllGift(page, giftDTO);
        return R.ok(giftPage);
    }


    /**
     * 修改积分商品
     * @param gift
     * @return
     */
    @PostMapping("/api/updateGift")
    @ResponseBody
    public R updateGift(Gift gift){
        Brand brand = brandService.getById(gift.getUsedId());
        gift.setUsedName(brand.getName());
        giftService.updateById(gift);
        return R.ok(null);
    }

    /**
     * 积分兑换商品
     * @param id
     * @param session
     * @return
     */
    @PostMapping("/api/exchangeProduct")
    @ResponseBody
    public R exchangeProduct(Integer id, Integer amount, HttpSession session){
        User loginUser = (User) session.getAttribute("loginUser");
        Integer userId = loginUser.getId();
        loginUser = userService.getById(userId);
        Gift gift = giftService.getById(id);
        if(gift == null){
            return R.failed("该商品不存在!");
        }
        if(gift.getPrice() < amount){
            return R.failed("该商品剩余数量不足!");
        }
        // 获取当前用户的积分
        BigDecimal score = loginUser.getScore();
        // 获取兑换商品所需积分
        BigDecimal giftScore = new BigDecimal(amount).multiply(gift.getScore());
        if(score.compareTo(giftScore) < 0){
            return R.failed("您的积分不足!");
        }

        gift.setPrice(gift.getPrice()-amount);
        loginUser.setScore(score.subtract(giftScore));
        giftService.updateById(gift);
        userService.updateById(loginUser);
        UserGift userGift = new UserGift();
        userGift.setAmount(amount);
        userGift.setGiftDesc(gift.getProductDesc());
        userGift.setGiftId(gift.getId());
        userGift.setGiftName(gift.getName());
        userGift.setGiftScore(gift.getScore());
        userGift.setGiftUsedId(gift.getUsedId());
        userGift.setGiftUsedName(gift.getUsedName());
        userGift.setUserId(userId);
        userGift.setUserName(loginUser.getUserName());
        userGiftService.save(userGift);
        return R.ok(null);
    }
}
