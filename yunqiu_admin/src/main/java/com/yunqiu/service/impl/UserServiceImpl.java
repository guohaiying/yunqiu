package com.yunqiu.service.impl;

import com.yunqiu.dao.UserRoleMapper;
import com.yunqiu.dao.UsersMapper;
import com.yunqiu.global.TokenService;
import com.yunqiu.model.AdminUser;
import com.yunqiu.model.PageCrt;
import com.yunqiu.model.UserRole;
import com.yunqiu.service.LogService;
import com.yunqiu.service.UserService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台管理员账户管理
 *
 * @author 武尊
 * @version 1.0
 * @time 2017-01-13
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private LogService logService;

    /**
     * 管理员登录
     *
     * @param userName
     * @param password
     * @return
     */
    @Override
    public Map<String, Object> login(String userName, String password) {
        try {
            //验证参数是否为空
            if (Utils.isNull(userName) || Utils.isNull(password)) {
                return ControllerReturnBase.errorResule(1501, "账户或密码未填写");
            }
            //根据账户名查询用户
            AdminUser adminUser = usersMapper.selectUserByUserName(userName);
            if (adminUser == null) {
                return ControllerReturnBase.errorResule(1502, "账户不存在");
            }
            //验证密码是否正确
            if (!adminUser.getPassword().equals(Utils.SHAEncrypt(password))) {
                return ControllerReturnBase.errorResule(1502, "密码错误");
            }
            //验证账户是否被冻结
            if (adminUser.getStatus()==2) {
                return ControllerReturnBase.errorResule(1502, "账户被冻结 请联系管理员");
            }
            //验证该账户是否已登录系统
           /* if (tokenService.validationLogin(adminUser.getAdminId())) {
                return ControllerReturnBase.errorResule(1502, "账户已在其它地方登录,请确认是否为本人操作");
            }*/
            //登录成功,生成tokey
            String token = tokenService.storageToken(adminUser.getAdminId());
            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("userId", adminUser.getAdminId());
            requst_parm.put("token", token);
            //记录日志
            String describe = "管理员:<" + adminUser.getUserName() + ">登录系统";
            logService.insertLog(1, adminUser.getAdminId(), describe);

            return ControllerReturnBase.successResule(requst_parm);
        } catch (Exception e) {
            LoggerUtil.outError(UserService.class, "管理员登录时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    // 查询总条数
    public Integer selectCount(PageCrt page) {
        return usersMapper.selectTotalPage(page);
    }

    //分页查询
    public List<Map> selectPaging(PageCrt page) {
        return usersMapper.selectePaging(page);
    }

    /**
     * 用户添加
     * @return
     */
    @Override
    public Map<String, Object> addUser(AdminUser adminUser,String role) {
        try {
            //验证参数是否为空
            if (Utils.isNull(adminUser.getUserName()) || Utils.isNull(adminUser.getPassword())) {
                return ControllerReturnBase.errorResule(1501, "用户名或密码未填写");
            }
            //添加用户
            String adminId=Utils.getID(22);
            adminUser.setAdminId(adminId);
            adminUser.setCreateTime(new Date());
            adminUser.setPassword(Utils.SHAEncrypt(adminUser.getPassword()));
            int result = usersMapper.insertUsers(adminUser);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }
            //用户和角色关联
            String [] roleArray = role.split(",");
            for(int i=0;i<roleArray.length;i++){
                UserRole userrole = new UserRole();
                userrole.setUrId(Utils.getID(22));
                userrole.setAdminId(adminId);
                userrole.setRoleId(roleArray[i]);
                int uresult = userRoleMapper.insertUserRole(userrole);
                if(uresult<=0){
                    return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
                }
            }
            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(UserService.class, "用户添加时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 用户修改
     * @return
     */
    @Override
    public Map<String, Object> updateUser(AdminUser adminUser,String role) {
        try {
            int result = usersMapper.updateUser(adminUser);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }
            //删除用户和角色的关联
            int urdresult = userRoleMapper.deleteRoleByUserId(adminUser.getAdminId());
            if(urdresult<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }

            //用户和角色关联
            String [] roleArray = role.split(",");
            for(int i=0;i<roleArray.length;i++){
                UserRole userrole = new UserRole();
                userrole.setUrId(Utils.getID(22));
                userrole.setAdminId(adminUser.getAdminId());
                userrole.setRoleId(roleArray[i]);
                int uresult = userRoleMapper.insertUserRole(userrole);
                if(uresult<=0){
                    return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
                }
            }
            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(UserService.class, "用户修改时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 用户删除
     * @return
     */
    @Override
    public Map<String, Object> deleteUser(String adminId) {
        try {
            int result = usersMapper.deleteUserById(adminId);
            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(UserService.class, "用户删除时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 用户冻结
     * @return
     */
    @Override
    public Map<String, Object> freezeUser(int status,String adminId) {
        try {
            int result = usersMapper.updateUserByStatus(status,adminId);
            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(UserService.class, "用户删除时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 用户修改密码
     * @return
     */
    @Override
    public Map<String, Object> uppassword(String password,String adminId) {
        try {
            int result = usersMapper.updateUserByPassword(Utils.SHAEncrypt(password),adminId);
            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(UserService.class, "用户修改密码时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

}
