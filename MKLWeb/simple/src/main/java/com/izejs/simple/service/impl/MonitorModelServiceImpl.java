package com.izejs.simple.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;;
import com.izejs.simple.dto.MonitorModelDTO;
import com.izejs.simple.entity.MonitorModel;
import com.izejs.simple.mapper.*;
import com.izejs.simple.service.IMonitorModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitorModelServiceImpl extends ServiceImpl<MonitorModelMapper, MonitorModel> implements IMonitorModelService {

    @Autowired
    private MonitorModelMapper monitorModelMapper;

    /**
     * 根据条件查询所有的监测模型信息
     * @param page
     * @param monitorModel
     * @return
     */

    @Override
    public IPage<MonitorModel> getAllMonitorModel(Page page, MonitorModelDTO monitorModel) {
        LambdaQueryWrapper<MonitorModel> queryWrapper = new LambdaQueryWrapper<>();

        if(monitorModel.getBatteryId() != null && !"".equals(monitorModel.getBatteryId())){
            queryWrapper = queryWrapper.like(MonitorModel::getBatteryId, monitorModel.getBatteryId());
        }
        if(monitorModel.getEtuName() != null && !"".equals(monitorModel.getEtuName())){
            queryWrapper = queryWrapper.like(MonitorModel::getEtuName, monitorModel.getEtuName());
        }
        if(monitorModel.getBatterySize() != null && !"".equals(monitorModel.getBatterySize())){
            queryWrapper = queryWrapper.like(MonitorModel::getBatterySize, monitorModel.getBatterySize());
        }

        return monitorModelMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<MonitorModel> getMonitorModelList() {
        return monitorModelMapper.selectList(null);
    }




}
