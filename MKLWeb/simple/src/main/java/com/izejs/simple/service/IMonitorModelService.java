package com.izejs.simple.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.izejs.simple.dto.MonitorModelDTO;
import com.izejs.simple.entity.MonitorModel;
;

import java.util.List;


public interface IMonitorModelService extends IService<MonitorModel> {
    IPage<MonitorModel> getAllMonitorModel(Page page, MonitorModelDTO monitorModel);

    List<MonitorModel> getMonitorModelList();

}
