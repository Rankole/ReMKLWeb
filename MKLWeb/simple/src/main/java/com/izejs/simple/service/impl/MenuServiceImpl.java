package com.izejs.simple.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.izejs.simple.entity.Menu;
import com.izejs.simple.mapper.MenuMapper;
import com.izejs.simple.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public R selectMenuList(HashMap<String, Object> param) {
        // 根据顶部菜单名和权限查询出对应的菜单
        List<Menu> menus = menuMapper.selectMenuList(param);
        Integer role = (Integer)param.get("role");
        menus.forEach(item -> {
            // 查询一级菜单对应的二级菜单, 并设置到返回参数中
            Integer menuId = item.getId();
            Map queryParam = new HashMap();
            queryParam.put("parent_id", menuId);
            queryParam.put("role_id", (Integer)param.get("role"));
            List<Menu> childrenMenu = menuMapper.selectList(new QueryWrapper<>().allEq(queryParam));
            item.setChildren(childrenMenu);
        });
        Map<String, List<Menu>> result = new HashMap<>();
        result.put((String)param.get("topMenuName"), menus);
        return R.ok(result);
    }

    @Override
    public R selectMainMenu(HashMap<String, Object> param) {
        Integer role = (Integer)param.get("role");
        return R.ok(menuMapper.selectOne(new QueryWrapper<Menu>().eq("is_menu", 1).eq("role_id", role)));
    }
}
