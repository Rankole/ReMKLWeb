package com.izejs.simple.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.izejs.simple.entity.MaintenanceWorker;
import com.izejs.simple.mapper.MaintenanceWorkerMapper;
import com.izejs.simple.dto.MaintenanceWorkerDTO;
import com.izejs.simple.service.IMaintenanceWorkerService;
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
public class MaintenanceWorkerServiceImpl extends ServiceImpl<MaintenanceWorkerMapper, MaintenanceWorker> implements IMaintenanceWorkerService {

    @Autowired
    private MaintenanceWorkerMapper maintenanceWorkerMapper;


    /**
     * 根据条件查询所有的维修工信息
     * @param page
     * @param maintenanceWorker
     * @return
     */
    @Override
    public IPage<MaintenanceWorker> getAllMaintenanceWorker(Page page, MaintenanceWorkerDTO maintenanceWorker) {
        LambdaQueryWrapper<MaintenanceWorker> queryWrapper = new LambdaQueryWrapper<>();

        if(maintenanceWorker.getName() != null && !"".equals(maintenanceWorker.getName())){
            queryWrapper = queryWrapper.like(MaintenanceWorker::getName, maintenanceWorker.getName());
        }
            if(maintenanceWorker.getWorkNumber() != null && !"".equals(maintenanceWorker.getWorkNumber())){
            queryWrapper = queryWrapper.like(MaintenanceWorker::getWorkNumber, maintenanceWorker.getWorkNumber());
        }

        return maintenanceWorkerMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<MaintenanceWorker> getMaintenanceWorkerList() {
        return maintenanceWorkerMapper.selectList(null);
    }

}
