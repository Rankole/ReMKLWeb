package com.izejs.simple.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.izejs.simple.entity.User;
import com.izejs.simple.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class MenuController {

    @Resource
    private MenuService menuService;

    /**
     * 查询菜单列表, 返回前端, 以便前端渲染菜单
     * @param topMenuName 顶部菜单名称
     * @param session
     * @return
     */
    @GetMapping("/api/selectMenuList")
    @ResponseBody
    public R selectMenuList(String topMenuName, HttpSession session){
        // 如果顶端菜单名称为空, 则设置默认值: contentManagement
        topMenuName = topMenuName == null ? "contentManagement":topMenuName;
        // 从session获取登录用户
        User loginUser = (User)session.getAttribute("loginUser");
        HashMap<String, Object> param = new HashMap<>();
        // 设置查询菜单列表的参数: 顶部菜单值, 和用户角色
        param.put("topMenuName", topMenuName);
        param.put("role", loginUser.getRole());
        return menuService.selectMenuList(param);
    }

    /**
     * 查询首页
     * @param topMenuName
     * @param session
     * @return
     */
    @GetMapping("/api/selectMainMenu")
    @ResponseBody
    public R selectMainMenu(String topMenuName, HttpSession session){
        // 如果顶端菜单名称为空, 则设置默认值: contentManagement
        topMenuName = topMenuName == null ? "contentManagement":topMenuName;
        // 从session获取登录用户
        User loginUser = (User)session.getAttribute("loginUser");
        HashMap<String, Object> param = new HashMap<>();
        // 设置查询菜单列表的参数: 顶部菜单值, 和用户角色
        param.put("topMenuName", topMenuName);
        param.put("role", loginUser.getRole());
        return menuService.selectMainMenu(param);
    }

}
