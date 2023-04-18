package com.izejs.simple.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.izejs.simple.service.IBrandTypeService;
import com.izejs.simple.entity.BrandType;
import com.izejs.simple.mapper.BrandTypeMapper;
import com.izejs.simple.vo.BrandTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author KunKa
 * @since 2021-04-13
 */
@Service
public class BrandTypeServiceImpl extends ServiceImpl<BrandTypeMapper, BrandType> implements IBrandTypeService {

    @Autowired
    private BrandTypeMapper brandTypeMapper;

    @Override
    public List<BrandType> getBrandTypesByBrandId(Integer brandId) {
        return brandTypeMapper.selectList(new LambdaQueryWrapper<BrandType>().eq(BrandType::getBrandId, brandId));
    }

    @Override
    public List<BrandTypeVO> getBrandTypes() {
        return brandTypeMapper.selectBrandTypes();
    }
}
