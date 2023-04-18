package com.izejs.simple.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.api.R;
import com.izejs.simple.dto.WashingModelDTO;
import com.izejs.simple.entity.BrandType;
import com.izejs.simple.entity.WashingModel;
import com.izejs.simple.service.IBrandTypeService;
import com.izejs.simple.service.IWashingModelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;


@Controller
public class WashingModelController {

    @Resource
    private IWashingModelService washingModelService;
    @Resource
    private IBrandTypeService brandTypeService;


    /**
     * 新增洗衣机模式
     * @param washingModel
     * @param session
     * @return
     */
    @PostMapping("/api/addWashingModel")
    @ResponseBody
    public R addWashingModel(WashingModel washingModel, HttpSession session){
        Integer brandTypeId = washingModel.getBrandTypeId();
        BrandType brandType = brandTypeService.getById(brandTypeId);
        washingModel.setBrandName(brandType.getBrandName());
        washingModel.setBrandTypeName(brandType.getTypeName());
        washingModelService.save(washingModel);
        return R.ok(null);
    }

    /**
     * 根据id批量删除洗衣机模式
     * @param session
     * @param washingModelIds
     * @return
     */
    @DeleteMapping("/api/deleteWashingModelByIds/{washingModelIds}")
    @ResponseBody
    public R deleteWashingModelByIds(HttpSession session, @PathVariable String washingModelIds){
        // 将批量删除洗衣机模式的id根据逗号切割成数组
        String[] ids = washingModelIds.split(",");
        washingModelService.removeByIds(Arrays.asList(ids));
        return R.ok(null);
    }


    /**
     * 根据洗衣机模式id获取详情
     * @param washingModelId
     * @return
     */
    @GetMapping("/api/getWashingModelById")
    @ResponseBody
    public R getWashingModelById(Integer washingModelId){
        WashingModel washingModel = washingModelService.getById(washingModelId);
        return R.ok(washingModel);
    }

    /**
     * 查询所有的washingModel
     * @param session
     * @param washingModelDTO
     * @param page
     * @return
     */
    @GetMapping("/api/getWashingModelList")
    @ResponseBody
    public R getWashingModelList(HttpSession session, WashingModelDTO washingModelDTO, Page page){

        IPage<WashingModel> washingModelPage = washingModelService.getAllWashingModel(page, washingModelDTO);
        return R.ok(washingModelPage);
    }


    /**
     * 修改洗衣机模式
     * @param washingModel
     * @return
     */
    @PostMapping("/api/updateWashingModel")
    @ResponseBody
    public R updateWashingModel(WashingModel washingModel){
        washingModelService.updateById(washingModel);
        return R.ok(null);
    }

    /**
     * 根据品牌id和型号id获取所有的洗衣机模式
     * @param brand
     * @param brandType
     * @return
     */
    @GetMapping("/api/getWashingModel")
    @ResponseBody
    public R getWashingModel(Integer brand, Integer brandType){
        List<WashingModel> list =  washingModelService.getWashingModel(brand, brandType);
        return R.ok(list);
    }
}
