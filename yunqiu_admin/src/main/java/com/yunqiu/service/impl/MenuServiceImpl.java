package com.yunqiu.service.impl;

import com.yunqiu.dao.MenuMapper;
import com.yunqiu.model.Menu;
import com.yunqiu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    /**
     * 查询所有菜单
     * @return
     */
    @Override
    public List<Menu> getMenuList() {
        List<Menu> map = menuMapper.selecteAllMenu();
        return map;
    }

    /**
     * 根据id查询菜单
     * @return
     */
    @Override
    public Menu findIdMenu(String menuId) {
        Menu menu = menuMapper.findIdMenu(menuId);
        return menu;
    }

    /**
     * 查询所有菜单
     * @return
     */
    @Override
    public List<Menu> findUserButtonMenu(String userId, String menuId) {
        List<Menu> map = menuMapper.findUserButtonMenu(userId,menuId);
        return map;
    }
    /**
     * 查询所有菜单
     * @return
     */
    @Override
    public List<Menu> getMenu(String userId){
        List<Menu> map = menuMapper.getMenu(userId);
        return map;
    }


}
