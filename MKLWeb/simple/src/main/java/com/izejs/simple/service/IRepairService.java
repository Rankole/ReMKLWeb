package com.izejs.simple.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.izejs.simple.entity.Repair;
import com.izejs.simple.dto.RepairDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author KunKa
 */
public interface IRepairService extends IService<Repair> {
    IPage<Repair> getAllRepair(Page page, RepairDTO repair);
}
