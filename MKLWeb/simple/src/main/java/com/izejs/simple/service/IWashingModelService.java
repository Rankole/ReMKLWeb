package com.izejs.simple.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.izejs.simple.dto.WashingModelDTO;
import com.izejs.simple.entity.WashingModel;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author KunKa
 */
public interface IWashingModelService extends IService<WashingModel> {
    IPage<WashingModel> getAllWashingModel(Page page, WashingModelDTO washingModel);

    List<WashingModel> getWashingModel(Integer brand, Integer brandType);
}
