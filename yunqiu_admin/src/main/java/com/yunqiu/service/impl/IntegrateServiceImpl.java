package com.yunqiu.service.impl;

import com.yunqiu.dao.BannerMapper;
import com.yunqiu.dao.IntegrateMapper;
import com.yunqiu.model.Banner;
import com.yunqiu.model.Integrate;
import com.yunqiu.model.PageCrt;
import com.yunqiu.service.BannerService;
import com.yunqiu.service.IntegrateService;
import com.yunqiu.service.TeamColourService;
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
 * app用户管理
 */

@Service
public class IntegrateServiceImpl implements IntegrateService {
    @Autowired
    private IntegrateMapper integrateMapper;


    @Override
    public Map<String, Object> addIntegrate(Integrate integrate){
        try {
            int result = 0;
            //验证参数是否为空
            if (Utils.isNull(integrate.getId())) {
                String id=Utils.getID(22);
                integrate.setId(id);
                result = integrateMapper.insertIntegrate(integrate);
            }else{
                result = integrateMapper.updateIntegrate(integrate);
            }
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }


            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(TeamColourService.class, "队服颜色添加时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    @Override
    public Map<String, Object> updatIntegrate(Integrate integrate) {
        try {
            int result = integrateMapper.updateIntegrate(integrate);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }

            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(TeamColourService.class, "队服颜色修改时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    @Override
    public Integrate findIdIntegrate(String id){
        Integrate integrate = integrateMapper.findIdIntegrate(id);
        return integrate;
    }



}
