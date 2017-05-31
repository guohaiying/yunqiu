package com.yunqiu.controller.permissions;

import com.yunqiu.controller.common.BaseController;
import com.yunqiu.model.*;
import com.yunqiu.service.MenuService;
import com.yunqiu.service.PowerService;
import com.yunqiu.service.UserService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.JSONKit;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.*;

/**
 * 后台管理员账户管理
 * @author 武尊
 * @time 2017-01-13
 *@version 1.0
 */

@Controller
@RequestMapping("/perm")
@EnableAutoConfiguration
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private PowerService powerService;

    JSONKit json= new JSONKit();

    /**
     * 管理员登录
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Map<String,Object> login(@RequestParam("userName") String userName,@RequestParam("password") String password, HttpSession httpSession){
        try {
            Map<String,Object> map = userService.login(userName,password);
            httpSession.setAttribute(Global.USER_SESSION_KEY, map);
            return map;
        }catch (Exception e){
            LoggerUtil.outError(UserController.class,"管理员登录时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    //查找所有用户
    @RequestMapping("/select")
    public void findAllUser( int page , int rows,String sidx,String sord,String userName,String phone,String status,String role,  HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("phone", phone);
        map.put("status", status);
        if(Utils.isNull(role) || role.equals("0")){
            map.put("role", null);
        }else{
            map.put("role", role);
        }

        PageCrt page1 = new PageCrt();
        page1.setPage(page);
        page1.setRows(rows);
        page1.setMap(map);
        page1.setSidx(sidx);
        page1.setSord(sord);

        Integer totalRecord=userService.selectCount(page1);
        int index = ((page - 1) * rows);
        page1.setIndex(index);
        page1.setRecords(totalRecord);
        page1.setTotal(totalRecord % rows == 0 ? totalRecord / rows : totalRecord / rows + 1);
        List<Map> list = userService.selectPaging(page1);
        page1.setList(list);
        PrintWriter out;
        out = json.getPrintWriter(response);
        json.objToJson(page1, out);
    }


    //用户添加
    @ResponseBody
    @RequestMapping("/addUser")
    public Map<String,Object>  addUser(HttpServletResponse response, AdminUser adminUser,@RequestParam("role") String role) {
        Map<String, Object> map = userService.addUser(adminUser,role);
        return map;
    }

    //用户修改
    @ResponseBody
    @RequestMapping("/upUser")
    public Map<String,Object>  updateUser(HttpServletResponse response, AdminUser adminUser,@RequestParam("role") String role) {
        Map<String, Object> map = userService.updateUser(adminUser,role);
        return map;
    }

    //用户删除
    @ResponseBody
    @RequestMapping("/delUser")
    public Map<String,Object>  deleteUser(HttpServletResponse response, @RequestParam("adminId") String adminId) {
        Map<String, Object> map = userService.deleteUser(adminId);
        return map;
    }


    //用户冻结或者解冻
    @ResponseBody
    @RequestMapping("/freezeUser")
    public Map<String,Object>  freezeUser(HttpServletResponse response, @RequestParam("adminId") String adminId, @RequestParam("status") int status) {
        Map<String, Object> map = userService.freezeUser(status,adminId);
        return map;
    }

    //修改登录密码
    @ResponseBody
    @RequestMapping("/uppassword")
    public Map<String,Object>  uppassword(HttpServletResponse response, @RequestParam("password") String password,HttpSession httpSession) {
        Map<String,Object> map = (Map<String,Object>)httpSession.getAttribute(Global.USER_SESSION_KEY);
        Map<String,Object> data = (Map<String,Object>)map.get("data");
        String adminId = data.get("userId")+"";
        return userService.uppassword(password,adminId);
    }

    //修改登录密码
    @ResponseBody
    @RequestMapping("/upPassword")
    public Map<String,Object>  upPassword(HttpServletResponse response, @RequestParam("password") String password,@RequestParam("adminId") String adminId,HttpSession httpSession) {
        return userService.uppassword(password,adminId);
    }


    /**
     * 左侧菜单
     */
    @RequestMapping("/getMenu")
    public void getMenu(HttpServletResponse response,HttpSession httpSession) {
        try {
            Map<String,Object> map = (Map<String,Object>)httpSession.getAttribute(Global.USER_SESSION_KEY);
            Map<String,Object> data = (Map<String,Object>)map.get("data");
            String userId = data.get("userId")+"";

            List<Power> listMenuRole = powerService.getMenuRoleByRoleIdList(userId);

            /*List<Menu> listMenu = new ArrayList<Menu>();
            for (int i = 0; i < listMenuRole.size(); i++) {
                Power m = listMenuRole.get(i);
                Menu me = menuService.findIdMenu(m.getMenuId());
                listMenu.add(me);
            }*/
            List<Menu> listMenu = menuService.getMenu(userId);
            PrintWriter out = json.getPrintWriter(response);
            json.objToJson(MenuJson.getMenuJson(listMenu), out);

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    /** 左侧导航栏跳转链接查询权限 */
    @ResponseBody
    @RequestMapping("/power")
    public List<Menu> roleSys(HttpSession httpSession,HttpServletRequest request) {
        Map<String,Object> map = (Map<String,Object>)httpSession.getAttribute(Global.USER_SESSION_KEY);
        Map<String,Object> data = (Map<String,Object>)map.get("data");
        String userId = data.get("userId")+"";

        String menuId = httpSession.getAttribute("menuId")+"";

        List<Menu> listAll = menuService.findUserButtonMenu(userId, menuId);
        return listAll;
    }



}
