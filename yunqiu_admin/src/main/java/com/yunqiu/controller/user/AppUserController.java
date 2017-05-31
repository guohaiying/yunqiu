package com.yunqiu.controller.user;

import com.yunqiu.controller.common.BaseController;
import com.yunqiu.model.AppUser;
import com.yunqiu.model.PageCrt;
import com.yunqiu.service.AppUserService;
import com.yunqiu.util.JSONKit;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理
 *
 * @author ghy
 * @version 1.0
 * @time 2017-01-13
 */

@Controller
@RequestMapping("/user")
@EnableAutoConfiguration
public class AppUserController extends BaseController {
    @Autowired
    private AppUserService userService;

    JSONKit json = new JSONKit();

    //查找所有用户
    @RequestMapping("/select")
    public void findAllUser(int page, int rows, String sidx, String sord, String identifier, String nickname, String sex, String status, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("identifier", identifier);
        map.put("nickname", nickname);
        map.put("sex", sex);
        map.put("status", status);

        PageCrt page1 = new PageCrt();
        page1.setPage(page);
        page1.setRows(rows);
        page1.setMap(map);
        page1.setSidx(sidx);
        page1.setSord(sord);

        Integer totalRecord = userService.selectCount(page1);
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
    public Map<String, Object> addUser(HttpServletResponse response, HttpServletRequest request, @RequestParam("nickname") String nickname, @RequestParam("identifier") String identifier, @RequestParam("teamId") String teamId, @RequestParam("type") Integer type, @RequestParam("jerseyNo") String jerseyNo) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("nickname", nickname);
        dataMap.put("identifier", identifier);
        dataMap.put("teamId", teamId);
        dataMap.put("type", type);
        dataMap.put("jerseyNo", jerseyNo);
        dataMap.put("ip", Utils.getIpAddress(request));

        Map<String, Object> map = userService.addUser(dataMap);
        return map;
    }

    //用户修改
    @ResponseBody
    @RequestMapping("/upUser")
    public Map<String, Object> updateUser(HttpServletResponse response, AppUser user,String credential,String identifier) {

        int age = this.getAge(user.getBirthday());
        user.setAge(age);

        Map<String, Object> map = userService.updateUser(user,credential,identifier);
        return map;
    }

    //用户删除
    @ResponseBody
    @RequestMapping("/delUser")
    public Map<String, Object> deleteUser(HttpServletResponse response, @RequestParam("adminId") String adminId) {
        Map<String, Object> map = userService.deleteUser(adminId);
        return map;
    }

    //查询所有用户
    @ResponseBody
    @RequestMapping("/getAllUser")
    public Map<String, Object> getAllUser(HttpServletResponse response) {
        Map<String, Object> map = userService.getAllUser();
        return map;
    }


    public static int getAge(Date birthDate) {

        if (birthDate == null)
            throw new RuntimeException("出生日期不能为null");

        int age = 0;

        Date now = new Date();

        SimpleDateFormat format_y = new SimpleDateFormat("yyyy");
        SimpleDateFormat format_M = new SimpleDateFormat("MM");

        String birth_year = format_y.format(birthDate);
        String this_year = format_y.format(now);

        String birth_month = format_M.format(birthDate);
        String this_month = format_M.format(now);

        // 初步，估算
        age = Integer.parseInt(this_year) - Integer.parseInt(birth_year);

        // 如果未到出生月份，则age - 1
        if (this_month.compareTo(birth_month) < 0)
            age -= 1;
        if (age < 0)
            age = 0;
        return age;
    }


}
