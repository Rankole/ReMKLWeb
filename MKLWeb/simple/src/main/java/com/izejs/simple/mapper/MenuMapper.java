package com.izejs.simple.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.izejs.simple.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author KunKa
 * @since 2021-03-07
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    @Select("SELECT m.id, m.title, m.icon, m.href, m.spread, m.target, m.parent_id parentId, m.top_menu_id topMenuId FROM menu m, top_menu tm where m.top_menu_id = tm.id and tm.title = #{topMenuName} and m.role_id = #{role}")
    List<Menu> selectMenuList(HashMap<String, Object> param);

}
