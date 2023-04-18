package com.izejs.simple.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.izejs.simple.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author KunKa
 * @since 2021-03-07
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
