package com.izejs.simple.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.izejs.simple.service.IBrandService;
import com.izejs.simple.entity.Brand;
import com.izejs.simple.mapper.BrandMapper;
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
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> getAllBrand() {
        return brandMapper.selectList(null);
    }
}
