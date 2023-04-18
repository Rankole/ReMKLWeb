package com.izejs.simple.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.izejs.simple.entity.BrandType;
import com.izejs.simple.vo.BrandTypeVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author KunKa
 * @since 2021-04-13
 */
public interface BrandTypeMapper extends BaseMapper<BrandType> {

    List<BrandTypeVO> selectBrandTypes();
}
