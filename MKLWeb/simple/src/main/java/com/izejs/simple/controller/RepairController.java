package com.izejs.simple.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.api.R;
import com.izejs.simple.dto.RepairDTO;
import com.izejs.simple.entity.MaintenanceWorker;
import com.izejs.simple.entity.Repair;
import com.izejs.simple.entity.User;
import com.izejs.simple.entity.WashingMachine;
import com.izejs.simple.service.IMaintenanceWorkerService;
import com.izejs.simple.service.IRepairService;
import com.izejs.simple.service.IWashingMachineService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Arrays;


@Controller
public class RepairController {

    @Resource
    private IRepairService repairService;
    @Resource
    private IMaintenanceWorkerService maintenanceWorkerService;
    @Resource
    private IWashingMachineService washingMachineService;


    /**
     * 新增报修管理
     * @param repair
     * @param session
     * @return
     */
    @PostMapping("/api/addRepair")
    @ResponseBody
    public R addRepair(Repair repair, HttpSession session){
        User loginUser = (User)session.getAttribute("loginUser");
        Integer workerId = repair.getWorkerId();
        MaintenanceWorker worker = maintenanceWorkerService.getById(workerId);
        // 根据id获取洗衣机
        WashingMachine washingMachine = washingMachineService.getById(repair.getWashingMachineId());
        repair.setWorkerName(worker.getName());
        repair.setWorkerPhone(worker.getPhone());
        repair.setRepairDateime(LocalDateTime.now());
        repair.setRepairUserId(loginUser.getId());
        repair.setRepairUserName(loginUser.getUserName());
        repair.setWashingMachineNumber(washingMachine.getNumber());
        repair.setBrand(washingMachine.getBrandName());
        repair.setBrandType(washingMachine.getTypeName());
        repair.setStatus(0);
        repairService.save(repair);
        // 判断洗衣机状态
        if(washingMachine.getStatus() != 0){
            return R.failed("只有待使用的洗衣机才可以报修!");
        }
        washingMachine.setStatus(2);
        washingMachine.setRepairUserId(loginUser.getId());
        washingMachine.setRepairUserName(loginUser.getUserName());
        washingMachine.setRepairDatetime(LocalDateTime.now());
        washingMachine.setRepairRemark(repair.getRepairRemark());
        washingMachineService.updateById(washingMachine);
        return R.ok(null);
    }

    /**
     * 根据id批量删除报修管理
     * @param session
     * @param repairIds
     * @return
     */
    @DeleteMapping("/api/deleteRepairByIds/{repairIds}")
    @ResponseBody
    public R deleteRepairByIds(HttpSession session, @PathVariable String repairIds){
        // 将批量删除报修管理的id根据逗号切割成数组
        String[] ids = repairIds.split(",");
        repairService.removeByIds(Arrays.asList(ids));
        return R.ok(null);
    }


    /**
     * 根据报修管理id获取详情
     * @param repairId
     * @return
     */
    @GetMapping("/api/getRepairById")
    @ResponseBody
    public R getRepairById(Integer repairId){
        Repair repair = repairService.getById(repairId);
        return R.ok(repair);
    }

    /**
     * 查询所有的repair
     * @param session
     * @param repairDTO
     * @param page
     * @return
     */
    @GetMapping("/api/getRepairList")
    @ResponseBody
    public R getRepairList(HttpSession session, RepairDTO repairDTO, Page page){
        User user = (User)session.getAttribute("loginUser");
        if(user.getRole() != 1){
            repairDTO.setRepairUserId(user.getId());
        }
        IPage<Repair> repairPage = repairService.getAllRepair(page, repairDTO);
        return R.ok(repairPage);
    }


    /**
     * 修改报修管理
     * @param repair
     * @return
     */
    @PostMapping("/api/updateRepair")
    @ResponseBody
    public R updateRepair(Repair repair){
        repairService.updateById(repair);
        return R.ok(null);
    }


}
