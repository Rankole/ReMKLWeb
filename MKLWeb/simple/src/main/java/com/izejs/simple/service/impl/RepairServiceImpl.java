package com.izejs.simple.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.izejs.simple.entity.Repair;
import com.izejs.simple.service.IRepairService;
import com.izejs.simple.dto.RepairDTO;
import com.izejs.simple.mapper.RepairMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author KunKa
 */
@Service
public class RepairServiceImpl extends ServiceImpl<RepairMapper, Repair> implements IRepairService {

    @Autowired
    private RepairMapper repairMapper;


    /**
     * 根据条件查询所有的报修管理
     *
     * @param page
     * @param repair
     * @return
     */
    @Override
    public IPage<Repair> getAllRepair(Page page, RepairDTO repair) {
        LambdaQueryWrapper<Repair> queryWrapper = new LambdaQueryWrapper<>();

        if (repair.getBrand() != null && !"".equals(repair.getBrand())) {
            queryWrapper = queryWrapper.like(Repair::getBrand, repair.getBrand());
        }
        if (repair.getBrandType() != null && !"".equals(repair.getBrandType())) {
            queryWrapper = queryWrapper.like(Repair::getBrandType, repair.getBrandType());
        }
        if (repair.getWashingMachineNumber() != null && !"".equals(repair.getWashingMachineNumber())) {
            queryWrapper = queryWrapper.like(Repair::getWashingMachineNumber, repair.getWashingMachineNumber());
        }
        if(repair.getRepairUserId() != null){
            queryWrapper = queryWrapper.like(Repair::getRepairUserId, repair.getRepairUserId());
        }

        return repairMapper.selectPage(page, queryWrapper);
    }

}
