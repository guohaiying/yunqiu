package com.yunqiu.service.impl;

import com.yunqiu.dao.PowerMapper;
import com.yunqiu.model.Power;
import com.yunqiu.service.PowerService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Permissions;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class PowerServiceImpl implements PowerService {
    @Autowired
    private PowerMapper powerMapper;

    /**
     * 查询所有菜单
     * @return
     */
    @Override
    public List<String> getMenuRoleByRoleId(String roleId){
        List<String> list = powerMapper.getMenuRoleByRoleId(roleId);
        return list;
    }

    /**
     * 授权
     * @return
     */
    @Override
    public Map<String, Object> addPower(Power power) {
        try {
            power.setPowerId(Utils.getID(22));
            int result = powerMapper.insertPower(power);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }

            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(PowerService.class, "用户添加时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 删除权限
     * @return
     */
    @Override
    public Map<String, Object> deleteMenuRole(String roleId) {
        try {
            //先删除权限
            int result = powerMapper.deleteRoleById(roleId);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }

            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(PowerService.class, "删除权限时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    @Override
    public List<Power> getMenuRoleByRoleIdList(String userId) {
        List<Power> list = powerMapper.getMenuRoleByRoleIdList(userId);
        return list;
    }

}
