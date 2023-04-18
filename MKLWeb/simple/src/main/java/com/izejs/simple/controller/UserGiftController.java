package com.izejs.simple.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.api.R;
import com.izejs.simple.dto.UserGiftDTO;
import com.izejs.simple.entity.UserGift;
import com.izejs.simple.service.IUserGiftService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Arrays;


@Controller
public class UserGiftController {

    @Resource
    private IUserGiftService userGiftService;


    /**
     * 新增我的积分商品
     * @param userGift
     * @param session
     * @return
     */
    @PostMapping("/api/addUserGift")
    @ResponseBody
    public R addUserGift(UserGift userGift, HttpSession session){
        userGiftService.save(userGift);
        return R.ok(null);
    }

    /**
     * 根据id批量删除我的积分商品
     * @param session
     * @param userGiftIds
     * @return
     */
    @DeleteMapping("/api/deleteUserGiftByIds/{userGiftIds}")
    @ResponseBody
    public R deleteUserGiftByIds(HttpSession session, @PathVariable String userGiftIds){
        // 将批量删除我的积分商品的id根据逗号切割成数组
        String[] ids = userGiftIds.split(",");
        userGiftService.removeByIds(Arrays.asList(ids));
        return R.ok(null);
    }


    /**
     * 根据我的积分商品id获取详情
     * @param userGiftId
     * @return
     */
    @GetMapping("/api/getUserGiftById")
    @ResponseBody
    public R getUserGiftById(Integer userGiftId){
        UserGift userGift = userGiftService.getById(userGiftId);
        return R.ok(userGift);
    }

    /**
     * 查询所有的userGift
     * @param session
     * @param userGiftDTO
     * @param page
     * @return
     */
    @GetMapping("/api/getUserGiftList")
    @ResponseBody
    public R getUserGiftList(HttpSession session, UserGiftDTO userGiftDTO, Page page){

        IPage<UserGift> userGiftPage = userGiftService.getAllUserGift(page, userGiftDTO);
        return R.ok(userGiftPage);
    }


    /**
     * 修改我的积分商品
     * @param userGift
     * @return
     */
    @PostMapping("/api/updateUserGift")
    @ResponseBody
    public R updateUserGift(UserGift userGift){
        userGiftService.updateById(userGift);
        return R.ok(null);
    }


}
