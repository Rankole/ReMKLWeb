package com.izejs.simple.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.izejs.simple.entity.BrandType;
import com.izejs.simple.vo.BrandTypeVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author KunKa
 * @since 2021-04-13
 */
public interface IBrandTypeService extends IService<BrandType> {

    List<BrandType> getBrandTypesByBrandId(Integer brandId);

    List<BrandTypeVO> getBrandTypes();
}
