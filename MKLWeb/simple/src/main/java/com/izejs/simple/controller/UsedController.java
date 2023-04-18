package com.izejs.simple.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.api.R;
import com.izejs.simple.entity.Used;
import com.izejs.simple.dto.UsedDTO;
import com.izejs.simple.service.IUsedService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Arrays;


@Controller
public class UsedController {

    @Resource
    private IUsedService usedService;


    /**
     * 新增使用记录
     * @param used
     * @param session
     * @return
     */
    @PostMapping("/api/addUsed")
    @ResponseBody
    public R addUsed(Used used, HttpSession session){
        used.setStatus(1);
        usedService.save(used);
        return R.ok(null);
    }

    /**
     * 根据id批量删除使用记录
     * @param session
     * @param usedIds
     * @return
     */
    @DeleteMapping("/api/deleteUsedByIds/{usedIds}")
    @ResponseBody
    public R deleteUsedByIds(HttpSession session, @PathVariable String usedIds){
        // 将批量删除使用记录的id根据逗号切割成数组
        String[] ids = usedIds.split(",");
        usedService.removeByIds(Arrays.asList(ids));
        return R.ok(null);
    }


    /**
     * 根据使用记录id获取详情
     * @param usedId
     * @return
     */
    @GetMapping("/api/getUsedById")
    @ResponseBody
    public R getUsedById(Integer usedId){
        Used used = usedService.getById(usedId);
        return R.ok(used);
    }

    /**
     * 查询所有的used
     * @param session
     * @param usedDTO
     * @param page
     * @return
     */
    @GetMapping("/api/getUsedList")
    @ResponseBody
    public R getUsedList(HttpSession session, UsedDTO usedDTO, Page page){

        IPage<Used> usedPage = usedService.getAllUsed(page, usedDTO);
        return R.ok(usedPage);
    }


    /**
     * 修改使用记录
     * @param used
     * @return
     */
    @PostMapping("/api/updateUsed")
    @ResponseBody
    public R updateUsed(Used used){
        usedService.updateById(used);
        return R.ok(null);
    }


}
