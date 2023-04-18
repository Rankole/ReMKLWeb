package com.izejs.simple.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.izejs.simple.dto.WashingModelDTO;
import com.izejs.simple.entity.WashingModel;
import com.izejs.simple.service.IWashingModelService;
import com.izejs.simple.mapper.WashingModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author KunKa
 */
@Service
public class WashingModelServiceImpl extends ServiceImpl<WashingModelMapper, WashingModel> implements IWashingModelService {

    @Autowired
    private WashingModelMapper washingModelMapper;


    /**
     * 根据条件查询所有的洗衣机模式
     * @param page
     * @param washingModel
     * @return
     */
    @Override
    public IPage<WashingModel> getAllWashingModel(Page page, WashingModelDTO washingModel) {
        LambdaQueryWrapper<WashingModel> queryWrapper = new LambdaQueryWrapper<>();

        if(washingModel.getBrandName() != null && !"".equals(washingModel.getBrandName())){
            queryWrapper = queryWrapper.like(WashingModel::getBrandName, washingModel.getBrandName());
        }
        if(washingModel.getBrandTypeName() != null && !"".equals(washingModel.getBrandTypeName())){
            queryWrapper = queryWrapper.like(WashingModel::getBrandTypeName, washingModel.getBrandTypeName());
        }

        return washingModelMapper.selectPage(page, queryWrapper);
    }

    /**
     * 根据品牌id和型号id查询所有的洗衣机模式
     * @param brand
     * @param brandType
     * @return
     */
    @Override
    public List<WashingModel> getWashingModel(Integer brand, Integer brandType) {
        LambdaQueryWrapper<WashingModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WashingModel::getBrandId, brand)
                .eq(WashingModel::getBrandTypeId, brandType);
        return washingModelMapper.selectList(queryWrapper);
    }

}