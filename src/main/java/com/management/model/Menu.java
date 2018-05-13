package com.management.model;/**
 * Created by jiajia on 2018/5/13.
 */

/**
 * @author jiajia
 * @version V1.0
 * @Description: 菜单
 * @date 2018/5/13 12:40
 */
public class Menu {
    private String menuId;
    private String name;
    private String url;
    private String module;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
