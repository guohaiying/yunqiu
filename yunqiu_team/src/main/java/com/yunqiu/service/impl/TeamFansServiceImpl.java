package com.yunqiu.service.impl;

import com.yunqiu.dao.TeamFansMapper;
import com.yunqiu.model.TeamFans;
import com.yunqiu.service.TeamFansService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 11366 on 2017/2/11.
 */

@Service
public class TeamFansServiceImpl implements TeamFansService {
    @Autowired
    private TeamFansMapper teamFansMapper;

    /**
     * 关注球队
     * @param user_id
     * @param team_id
     * @return
     */
    @Override
    public Map<String, Object> attentionTeam(String user_id, String team_id) {
        try {
            if (Utils.isNull(team_id)){
                return ControllerReturnBase.errorResule(1501,"缺少被关注的球队id");
            }
            TeamFans db_teamFans = teamFansMapper.selectTeamFans(user_id,team_id);
            if (db_teamFans != null){
                return ControllerReturnBase.errorResule(1502,"已关注该球队，请勿重复操作");
            }
            TeamFans teamFans = new TeamFans();
            teamFans.setFans_id(Utils.getUUID());
            teamFans.setUser_id(user_id);
            teamFans.setTeam_id(team_id);
            teamFansMapper.insertTeamFans(teamFans);
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(TeamServiceImpl.class,"关注球队时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 取消球队关注
     * @param user_id
     * @param team_id
     * @return
     */
    @Override
    public Map<String, Object> cancelAttentionTeam(String user_id, String team_id) {
        try {
            if (Utils.isNull(team_id)){
                return ControllerReturnBase.errorResule(1501,"缺少被关注的球队id");
            }
            TeamFans db_teamFans = teamFansMapper.selectTeamFans(user_id,team_id);
            if (db_teamFans == null){
                return ControllerReturnBase.errorResule(1502,"未关注该球队");
            }
            teamFansMapper.deleteFans(db_teamFans.getFans_id());
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(TeamServiceImpl.class,"取消球队关注时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 获取关注的球队列表
     * @param pageNum
     * @param pageSize
     * @param user_id
     * @return
     */
    @Override
    public Map<String, Object> focusTeamList(int pageNum, int pageSize, String user_id) {
        try {
            if (pageNum <= 0 || pageSize <= 0){
                return ControllerReturnBase.errorResule(1501,"页码或者每页条数不可为0");
            }
            //获取总条数
            int focusTotal = teamFansMapper.selectFocusTotal(user_id);
            if (focusTotal == 0){
                return ControllerReturnBase.errorResule(1502,"无关注的球队");
            }
            //计算总页数
            int pageTotal = focusTotal%pageSize == 0 ?focusTotal/pageSize:(focusTotal/pageSize)+1;
            //计算起始行索引
            int start_now = (pageNum-1)*pageSize;
            //分页查询关注的球队列表
            List<Map<String,Object>> focus_list = teamFansMapper.selectPagingFocus(user_id,start_now,pageSize);
            //返回数据
            Map<String,Object> result_param = new HashMap<>();
            result_param.put("total",pageTotal);
            result_param.put("pageSize",pageSize);
            result_param.put("pageNum",pageNum);
            result_param.put("focus",focus_list);
            return ControllerReturnBase.successResule(result_param);
        }catch (Exception e){
            LoggerUtil.outError(TeamServiceImpl.class,"获取关注的球队列表时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 获取球队粉丝
     * @param pageNum
     * @param pageSize
     * @param team_id
     * @return
     */
    @Override
    public Map<String, Object> getTeamFans(int pageNum, int pageSize, String team_id) {
        try {
            //获取总条数
            int fansTotal = teamFansMapper.selectTeamFansTotal(team_id);
            if (fansTotal == 0){
                return ControllerReturnBase.errorResule(1502,"无粉丝");
            }
            //计算总页数
            int pageTotal = fansTotal%pageSize == 0 ?fansTotal/pageSize:(fansTotal/pageSize)+1;
            //计算起始行索引
            int start_now = (pageNum-1)*pageSize;
            //获取粉丝列表
            List<Map<String,Object>> teamFans = teamFansMapper.selectTeamFansList(team_id,start_now,pageSize);
            //返回数据
            Map<String,Object> result_param = new HashMap<>();
            result_param.put("total",pageTotal);
            result_param.put("pageSize",pageSize);
            result_param.put("pageNum",pageNum);
            result_param.put("fans",teamFans);
            return ControllerReturnBase.successResule(result_param);
        }catch (Exception e){
            LoggerUtil.outError(TeamFansServiceImpl.class,"获取球队粉丝时发送错误",e);
            return ControllerReturnBase.errorResule();
        }
    }
}
