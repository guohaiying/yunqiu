package com.yunqiu.service.impl;

import com.yunqiu.dao.TeamColourMapper;
import com.yunqiu.model.PageCrt;
import com.yunqiu.model.TeamColour;
import com.yunqiu.model.TeamTags;
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
public class TeamColourServiceImpl implements TeamColourService {
    @Autowired
    private TeamColourMapper teamColourMapper;


    // 查询总条数
    public Integer selectCount(PageCrt page) {
        return teamColourMapper.selectTotalPage(page);
    }

    //分页查询
    public List<Map> selectPaging(PageCrt page) {
        return teamColourMapper.selectePaging(page);
    }

    /**
     * 队服颜色添加
     * @return
     */
    @Override
    public Map<String, Object> addTeamColour(TeamColour teamColour) {
        try {
            //验证参数是否为空
            if (Utils.isNull(teamColour.getColour())) {
                return ControllerReturnBase.errorResule(1501, "队服颜色未填写");
            }
            String teamcolorId=Utils.getID(22);
            teamColour.setTeamcolorId(teamcolorId);
            teamColour.setCreateTime(new Date());
            int result = teamColourMapper.insertTeamColour(teamColour);
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

    /**
     * 队服颜色修改
     * @return
     */
    @Override
    public Map<String, Object> updatTeamColour(TeamColour teamColour) {
        try {
            int result = teamColourMapper.updateTeamColour(teamColour);
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

    /**
     * 队服颜色删除
     * @return
     */
    @Override
    public Map<String, Object> deleteTeamColour(String teamcolorId) {
        try {
            int result = teamColourMapper.deleteTeamColourById(teamcolorId);
            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(TeamColourService.class, "队服颜色删除时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }


    /**
     * 查询所有队服颜色
     * @return
     */
    @Override
    public Map<String, Object> getTeamColourList() {
        try {
            List<TeamColour> map = teamColourMapper.getTeamColourList();
            return ControllerReturnBase.successResule(map);
        } catch (Exception e) {
            LoggerUtil.outError(TeamColourService.class, "查询队服颜色时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }




}
