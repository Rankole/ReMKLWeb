package com.izejs.simple.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.api.R;
import com.izejs.simple.dto.MaintenanceWorkerDTO;
import com.izejs.simple.entity.MaintenanceWorker;
import com.izejs.simple.service.IMaintenanceWorkerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;


@Controller
public class MaintenanceWorkerController {

    @Resource
    private IMaintenanceWorkerService maintenanceWorkerService;


    /**
     * 新增维修工信息
     * @param maintenanceWorker
     * @param session
     * @return
     */
    @PostMapping("/api/addMaintenanceWorker")
    @ResponseBody
    public R addMaintenanceWorker(MaintenanceWorker maintenanceWorker, HttpSession session){
        maintenanceWorkerService.save(maintenanceWorker);
        return R.ok(null);
    }

    /**
     * 根据id批量删除维修工信息
     * @param session
     * @param maintenanceWorkerIds
     * @return
     */
    @DeleteMapping("/api/deleteMaintenanceWorkerByIds/{maintenanceWorkerIds}")
    @ResponseBody
    public R deleteMaintenanceWorkerByIds(HttpSession session, @PathVariable String maintenanceWorkerIds){
        // 将批量删除维修工信息的id根据逗号切割成数组
        String[] ids = maintenanceWorkerIds.split(",");
        maintenanceWorkerService.removeByIds(Arrays.asList(ids));
        return R.ok(null);
    }


    /**
     * 根据维修工信息id获取详情
     * @param maintenanceWorkerId
     * @return
     */
    @GetMapping("/api/getMaintenanceWorkerById")
    @ResponseBody
    public R getMaintenanceWorkerById(Integer maintenanceWorkerId){
        MaintenanceWorker maintenanceWorker = maintenanceWorkerService.getById(maintenanceWorkerId);
        return R.ok(maintenanceWorker);
    }

    /**
     * 查询所有的maintenanceWorker
     * @param session
     * @param maintenanceWorkerDTO
     * @param page
     * @return
     */
    @GetMapping("/api/getMaintenanceWorkerList")
    @ResponseBody
    public R getMaintenanceWorkerList(HttpSession session, MaintenanceWorkerDTO maintenanceWorkerDTO, Page page){

        IPage<MaintenanceWorker> maintenanceWorkerPage = maintenanceWorkerService.getAllMaintenanceWorker(page, maintenanceWorkerDTO);
        return R.ok(maintenanceWorkerPage);
    }


    /**
     * 修改维修工信息
     * @param maintenanceWorker
     * @return
     */
    @PostMapping("/api/updateMaintenanceWorker")
    @ResponseBody
    public R updateMaintenanceWorker(MaintenanceWorker maintenanceWorker){
        maintenanceWorkerService.updateById(maintenanceWorker);
        return R.ok(null);
    }

    /**
     * 获取维修工列表
     * @return
     */
    @GetMapping("/api/getWorkers")
    @ResponseBody
    public R getWorkers(){
        List<MaintenanceWorker> maintenanceWorkerList = maintenanceWorkerService.getMaintenanceWorkerList();
        return R.ok(maintenanceWorkerList);
    }
}
