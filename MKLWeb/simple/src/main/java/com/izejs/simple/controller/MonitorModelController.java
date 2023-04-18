package com.izejs.simple.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.izejs.simple.dto.MonitorModelDTO;
import com.izejs.simple.entity.MonitorModel;
import com.izejs.simple.service.IMonitorModelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
//@CrossOrigin(origins="*")
public class MonitorModelController {

    @Resource
    private IMonitorModelService monitorModelService;

    /**
     * 新增监测模型信息
     * @param monitorModel
     * @param session
     * @return
     */
    @PostMapping("/api/addMonitorModel")
    @ResponseBody
    public R addMonitorModel(MonitorModel monitorModel, HttpSession session){
        monitorModelService.save(monitorModel);
        return R.ok(null);
    }

    /**
     * 根据id批量监测模型信息
     * @param session
     * @param monitorModelIds
     * @return
     */
    @DeleteMapping("/api/deleteMonitorModelByIds/{monitorModelIds}")
    @ResponseBody
    public R deleteMonitorModelByIds(HttpSession session, @PathVariable String monitorModelIds){
        // 将批量删除监测模型信息的id根据逗号切割成数组
        String[] ids = monitorModelIds.split(",");
        monitorModelService.removeByIds(Arrays.asList(ids));
        return R.ok(null);
    }

    /**
     * 根据监测模型信息id获取详情
     * @param monitorModelId
     * @return
     */
    @GetMapping("/api/getMonitorModelById")
    @ResponseBody
    public R getMonitorModelById(Integer monitorModelId){
        MonitorModel monitorModel = monitorModelService.getById(monitorModelId);
        return R.ok(monitorModel);
    }

    /**
     * 查询所有的监测模型
     * @param session
     * @param monitorModelDTO
     * @param page
     * @return
     */
    @GetMapping("/api/getMonitorModelList")
    @ResponseBody
    private R getMonitorModelList(HttpSession session, MonitorModelDTO monitorModelDTO, Page page){
        IPage<MonitorModel> monitorModelPage = monitorModelService.getAllMonitorModel(page,monitorModelDTO);
        return R.ok(monitorModelPage);
    }

    /**
     * 修改监测模型信息
     * @param monitorModel
     * @return
     */
    @PostMapping("/api/updateMonitorModel")
    @ResponseBody
    public R updateMonitorModel(MonitorModel monitorModel){
        monitorModelService.updateById(monitorModel);
        return R.ok(null);
    }

    /**
     * 获取监测模型列表
     * @return
     */
    @GetMapping("/api/getMonitorModels")
    @ResponseBody
    public R getMonitorModels(){
        List<MonitorModel> monitorModelList = monitorModelService.getMonitorModelList();
        return R.ok(monitorModelList);
    }

}
