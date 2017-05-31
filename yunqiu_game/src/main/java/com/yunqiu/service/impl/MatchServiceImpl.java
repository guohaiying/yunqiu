package com.yunqiu.service.impl;

import com.yunqiu.dao.EventMapper;
import com.yunqiu.dao.MatchMapper;
import com.yunqiu.dao.TeamInfoMapper;
import com.yunqiu.model.Event;
import com.yunqiu.model.Match;
import com.yunqiu.service.MatchService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by 11366 on 2017/1/25.
 */

@Service
public class MatchServiceImpl implements MatchService {
    @Autowired
    private MatchMapper matchMapper;
    @Autowired
    private TeamInfoMapper teamInfoMapper;
    @Autowired
    private EventMapper eventMapper;

    /**
     * 参与比赛
     * @param team_id
     * @param event_id
     * @param status
     * @param user_id
     * @return
     */
    @Override
    public Map<String, Object> participationGame(String team_id, String event_id, int status, String user_id) {
        try {
            //验证状态参赛
            if (status != 1 && status != 2 && status != 3){
                return ControllerReturnBase.errorResule(1501,"参与状态错误");
            }
            //验证赛事是否存在
            Event event = eventMapper.selectEventById(event_id);
            if (event == null){
                return ControllerReturnBase.errorResule(1501,"赛事不存在");
            }
            //验证用户是否是球队的成员
            Map<String,Object> teamMatch = teamInfoMapper.selectTeamMemberByTeamIdAndUserId(team_id,user_id);
            if (teamMatch == null){
                return ControllerReturnBase.errorResule(1502,"该用户不是该球队成员,无法报名");
            }
            //查询是否已参与比赛
            Match db_match = matchMapper.selectMatchByUserIdAndEventIdAndTeamId(user_id,event_id);
            //如果是报名，则如果有参赛信息，则修改参赛状态,否则添加报名信息
            if (status == 1){
                if (db_match != null){
                    matchMapper.updateMatchStatusByUserIdAndEventIdAndTeamId(1,user_id,event_id,team_id);
                    return ControllerReturnBase.successResule();
                }else {
                    Match match = new Match();
                    match.setMatch_id(Utils.getUUID());
                    match.setUser_id(user_id);
                    match.setEvent_id(event_id);
                    match.setTeam_id(team_id);
                    match.setStatus(1);
                    matchMapper.insertMatch(match);
                    return ControllerReturnBase.successResule();
                }
             //如果为待定，如果有参赛信息，返回失败
            }else if (status == 2){
                if (db_match != null){
                    return ControllerReturnBase.errorResule(1502,"当前已有报名信息，不能操作为待定");
                }else {
                    Match match = new Match();
                    match.setMatch_id(Utils.getUUID());
                    match.setUser_id(user_id);
                    match.setEvent_id(event_id);
                    match.setTeam_id(team_id);
                    match.setStatus(2);
                    matchMapper.insertMatch(match);
                    return ControllerReturnBase.successResule();
                }
             //如果为请假，如果有参赛信息，则修改参赛状态 否则添加报名信息
            }else{
                if (db_match != null){
                    matchMapper.updateMatchStatusByUserIdAndEventIdAndTeamId(3,user_id,event_id,team_id);
                    return ControllerReturnBase.successResule();
                }else {
                    Match match = new Match();
                    match.setMatch_id(Utils.getUUID());
                    match.setUser_id(user_id);
                    match.setEvent_id(event_id);
                    match.setTeam_id(team_id);
                    match.setStatus(3);
                    matchMapper.insertMatch(match);
                    return ControllerReturnBase.successResule();
                }
            }
        }catch (Exception e){
            LoggerUtil.outError(MatchServiceImpl.class,"参赛比赛(service)发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }
}
