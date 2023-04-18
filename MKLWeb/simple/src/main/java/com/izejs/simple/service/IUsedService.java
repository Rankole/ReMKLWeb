package com.izejs.simple.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.izejs.simple.dto.UsedDTO;
import com.izejs.simple.entity.Used;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author KunKa
 */
public interface IUsedService extends IService<Used> {
    IPage<Used> getAllUsed(Page page, UsedDTO used);

    List<Used> selectList(LambdaQueryWrapper<Used> usedQueryWrapper);

    void washingEnd(Used used);
}
