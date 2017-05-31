package com.yunqiu.controller.permissions;

import com.yunqiu.controller.common.BaseController;
import com.yunqiu.model.Menu;
import com.yunqiu.model.PageCrt;
import com.yunqiu.model.Power;
import com.yunqiu.model.Role;
import com.yunqiu.service.MenuService;
import com.yunqiu.service.PowerService;
import com.yunqiu.service.RoleService;
import com.yunqiu.util.JSONKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * 后台角色管理
 */

@Controller
@RequestMapping("/role")
@EnableAutoConfiguration
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private PowerService powerService;

    @Autowired
    private MenuService menuService;

    JSONKit json= new JSONKit();


    //查找所有角色
    @RequestMapping("/select")
    public void findAllRole( int page , int rows,String sidx,String sord,  HttpServletResponse response) {
        Integer totalRecord=roleService.selectCount();
        PageCrt page1 = new PageCrt();
        page1.setPage(page);
        page1.setRows(rows);
        page1.setSidx(sidx);
        page1.setSord(sord);
        int index = ((page - 1) * rows);
        page1.setIndex(index);
        page1.setRecords(totalRecord);
        page1.setTotal(totalRecord % rows == 0 ? totalRecord / rows : totalRecord / rows + 1);
        List<Map> list = roleService.selectPaging(page1);
        page1.setList(list);
        PrintWriter out;
        out = json.getPrintWriter(response);
        json.objToJson(page1, out);
    }

    //角色添加
    @ResponseBody
    @RequestMapping("/addRole")
    public Map<String,Object>  addRole(HttpServletResponse response, Role role) {
        Map<String, Object> map = roleService.addRole(role);
        return map;
    }

    //角色修改
    @ResponseBody
    @RequestMapping("/upRole")
    public Map<String,Object>  updateRole(HttpServletResponse response, Role role) {
        Map<String, Object> map = roleService.updateRole(role);
        return map;
    }

    //角色删除
    @ResponseBody
    @RequestMapping("/delRole")
    public Map<String,Object>  deleteRole(HttpServletResponse response, @RequestParam("roleId") String roleId) {
        Map<String, Object> map = roleService.deleteRole(roleId);
        return map;
    }

    //查询所有角色 用于用户授权
    @ResponseBody
    @RequestMapping("/getRoleList")
    public Map<String,Object>  getRoleList(HttpServletResponse response) {
        Map<String, Object> map = roleService.getRoleList();
        return map;
    }

    /**
     * 权限菜单
     */
    @RequestMapping("/getMenuRole")
    public void getMenuRole(HttpServletResponse response, HttpSession httpSession, String roleId) {
        try {
            List<String> listPermissions = powerService.getMenuRoleByRoleId(roleId);
            List<Menu> listAll = menuService.getMenuList();
            List<MenuRoleJson> menuRoleJson = MenuJson.getTree(listAll,listPermissions);
            PrintWriter out = json.getPrintWriter(response);
            json.objToJson(menuRoleJson, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 添加权限 */
    @ResponseBody
    @RequestMapping("/addMenuRole")
    public Map<String, Object> addMenuRole(HttpServletResponse response, Power power) {
        Map<String, Object> map = powerService.addPower(power);
        return map;
    }

    /** 删除权限 */
    @ResponseBody
    @RequestMapping("/deleteMenuRole")
    public Map<String, Object> deleteMenuRole(HttpServletResponse response, String roleId) {
        Map<String, Object> map = powerService.deleteMenuRole(roleId);
        return map;
    }








}
