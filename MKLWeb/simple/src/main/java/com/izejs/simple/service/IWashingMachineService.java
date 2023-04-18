package com.izejs.simple.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.izejs.simple.entity.User;
import com.izejs.simple.entity.WashingMachine;
import com.izejs.simple.dto.WashingMachineDTO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author KunKa
 */
public interface IWashingMachineService extends IService<WashingMachine> {

    IPage<WashingMachine> getAllWashingMachine(Page page, WashingMachineDTO washingMachine);

    void resetWashingMachineStatus(Integer washingMachineId);

    List<WashingMachine> selectList(LambdaQueryWrapper<WashingMachine> eq);

    List<WashingMachine> getWashingMachineListByBrand(Integer brand, Integer brandType);

    R payment(WashingMachine washingMachine, User user, Integer giftId, Integer businessId, Integer type);
}
