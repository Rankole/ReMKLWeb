package com.izejs.simple.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.izejs.simple.entity.Brand;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author KunKa
 * @since 2021-04-13
 */
public interface IBrandService extends IService<Brand> {

    List<Brand> getAllBrand();
}
