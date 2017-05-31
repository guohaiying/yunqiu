package com.yunqiu.service.impl;

import com.yunqiu.dao.TeamMapper;
import com.yunqiu.model.PageCrt;
import com.yunqiu.model.Team;
import com.yunqiu.service.TeamCloudDataService;
import com.yunqiu.service.TeamService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private TeamCloudDataService teamCloudDataService;

    // 查询总条数
    public Integer selectCount(PageCrt page) {
        return teamMapper.selectTotalPage(page);
    }

    //分页查询
    public List<Map> selectPaging(PageCrt page) {
        return teamMapper.selectePaging(page);
    }

    /**
     * 球队添加
     * @return
     */
    @Override
    public Map<String, Object> addTeam(Team team) {
        try {
            //验证参数是否为空
            if (Utils.isNull(team.getTeamName())) {
                return ControllerReturnBase.errorResule(1501, "球队名称未填写");
            }
            //添加用户
            String teamId=Utils.getID(22);
            team.setTeamId(teamId);
            team.setEnterTime(new Date());
            team.setStatus(1);
            int result = teamMapper.insertTeam(team);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }

            //初始化球队云五数据
            teamCloudDataService.initialization(teamId);

            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(TeamService.class, "球队添加时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 球队修改
     * @return
     */
    @Override
    public Map<String, Object> updatePlace(Team team) {
        try {
            int result = teamMapper.updateTeam(team);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }

            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(TeamService.class, "球队修改时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 球队解散
     * @return
     */
    @Override
    public Map<String, Object> disbandTeam(String teamId) {
        try {
            int result = teamMapper.disbandTeam(teamId);
            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(TeamService.class, "球队删除时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }


    /**
     * 查询所有未解散球队
     * @return
     */
    @Override
    public Map<String, Object> getTeamList() {
        try {
            List<Team> map = teamMapper.getTeamList();
            return ControllerReturnBase.successResule(map);
        } catch (Exception e) {
            LoggerUtil.outError(TeamService.class, "查询省时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

}
