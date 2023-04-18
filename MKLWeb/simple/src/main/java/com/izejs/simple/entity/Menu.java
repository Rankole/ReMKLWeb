package com.izejs.simple.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author KunKa
 * @since 2021-03-07
 */
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 菜单名称
     */
    private String title;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 菜单连接
     */
    private String href;

    /**
     * 是否展开子菜单
     */
    private Integer spread;

    /**
     * 子菜单打开时是否在新窗口打开页面
     */
    private String target;

    /**
     * 父菜单id
     */
    private Integer parentId;

    /**
     * 顶菜单id
     */
    private Integer topMenuId;

    /**
     * 权限角色
     */
    private Integer roleId;

    /**
     * 是否是首页
     */
    private Integer isMenu;

    /**
     * 子菜单对象
     */
    @TableField(exist = false)
    private List<Menu> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
    public Integer getSpread() {
        return spread;
    }

    public void setSpread(Integer spread) {
        this.spread = spread;
    }
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    public Integer getTopMenuId() {
        return topMenuId;
    }

    public void setTopMenuId(Integer topMenuId) {
        this.topMenuId = topMenuId;
    }
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(Integer isMenu) {
        this.isMenu = isMenu;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Menu{" +
            "id=" + id +
            ", title=" + title +
            ", icon=" + icon +
            ", href=" + href +
            ", spread=" + spread +
            ", target=" + target +
            ", parentId=" + parentId +
            ", topMenuId=" + topMenuId +
            ", roleId=" + roleId +
        "}";
    }
}
