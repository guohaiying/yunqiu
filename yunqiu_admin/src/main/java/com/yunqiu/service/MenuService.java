package com.yunqiu.service;

import com.yunqiu.model.Menu;

import java.util.List;

/**
 * Created by 11366 on 2017/1/14.
 */
public interface MenuService {

    List<Menu> getMenuList();

    Menu findIdMenu(String menuId);

    List<Menu> findUserButtonMenu(String userId, String menuId);

    List<Menu> getMenu(String userId);


}
