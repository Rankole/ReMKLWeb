package com.izejs.simple.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.izejs.simple.dto.UsedDTO;
import com.izejs.simple.entity.Used;
import com.izejs.simple.mapper.UsedMapper;
import com.izejs.simple.service.IUsedService;
import com.izejs.simple.service.IWashingMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author KunKa
 */
@Service
public class UsedServiceImpl extends ServiceImpl<UsedMapper, Used> implements IUsedService {

    @Autowired
    private UsedMapper usedMapper;
    @Resource
    private IWashingMachineService washingMachineService;


    /**
     * 根据条件查询所有的使用记录
     * @param page
     * @param used
     * @return
     */
    @Override
    public IPage<Used> getAllUsed(Page page, UsedDTO used) {
        LambdaQueryWrapper<Used> queryWrapper = new LambdaQueryWrapper<>();

        if(used.getBrand() != null && !"".equals(used.getBrand())){
            queryWrapper = queryWrapper.like(Used::getBrand, used.getBrand());
        }
            if(used.getBrandType() != null && !"".equals(used.getBrandType())){
            queryWrapper = queryWrapper.like(Used::getBrandType, used.getBrandType());
        }
            if(used.getWashingMachineNumber() != null && !"".equals(used.getWashingMachineNumber())){
            queryWrapper = queryWrapper.like(Used::getWashingMachineNumber, used.getWashingMachineNumber());
        }
            if(used.getStatus() != null && !"".equals(used.getStatus())){
            queryWrapper = queryWrapper.like(Used::getStatus, used.getStatus());
        }

        return usedMapper.selectPage(page, queryWrapper);
    }

    /**
     * 查询使用记录
     * @param usedQueryWrapper
     * @return
     */
    @Override
    public List<Used> selectList(LambdaQueryWrapper<Used> usedQueryWrapper) {
        return usedMapper.selectList(usedQueryWrapper);
    }

    @Override
    public void washingEnd(Used used) {
        // 将洗衣机状态还原成初始状态
        washingMachineService.resetWashingMachineStatus(used.getWashingMachineId());
        // 将使用记录的状态更新成已完成
        used.setStatus(2);
        usedMapper.updateById(used);
    }

}
