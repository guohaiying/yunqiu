package com.yunqiu.tournament.service.impl;

import com.yunqiu.general.dao.TeamMapper;
import com.yunqiu.tournament.dao.GameMemberMapper;
import com.yunqiu.tournament.dao.TournamentMapper;
import com.yunqiu.tournament.model.GameMember;
import com.yunqiu.tournament.model.Tournament;
import com.yunqiu.tournament.service.GameMemberService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 比赛成员数据处理
 * @time 2017-02-26
 */
@Service
public class GameMemberServiceImpl implements GameMemberService {
    @Autowired
    private GameMemberMapper gameMemberMapper;
    @Autowired
    private TournamentMapper tournamentMapper;
    @Autowired
    private TeamMapper teamMapper;

    /**
     * 参与比赛
     * @param game_id
     * @param team_id
     * @param user_id
     * @param join_status
     * @return
     */
    @Override
    public Map<String, Object> participationGame(String game_id, String team_id, String user_id, int join_status) {
        try {
            //验证参赛状态
            if (join_status != 1 && join_status != 2 && join_status != 3){
                return ControllerReturnBase.errorResule(1501,"参与状态错误");
            }
            //验证用户是否是球队的成员
            Map<String,Object> teamMatch = teamMapper.selectTeamMemberByTeamIdAndUserId(team_id,user_id);
            if (teamMatch == null){
                return ControllerReturnBase.errorResule(1502,"该用户不是该球队成员,无法报名");
            }
            //验证赛事
            Tournament tournament = tournamentMapper.selectGameByGameId(game_id);
            if (tournament == null){
                return ControllerReturnBase.errorResule(1501,"赛事不存在");
            }
            if (tournament.getGame_status() != 3){
                return ControllerReturnBase.errorResule(1502,"比赛已开始或已结束，不能再参与");
            }
            //查询是否已参与
            GameMember gameMember = gameMemberMapper.selectGameMember(game_id,user_id);
            if (gameMember == null){
                //未参与，添加参与信息
                gameMember = new GameMember();
                gameMember.setMatch_id(Utils.getUUID());
                gameMember.setUser_id(user_id);
                gameMember.setGame_id(game_id);
                gameMember.setStatus(join_status);
                gameMember.setTeam_id(team_id);
                gameMemberMapper.insertGameMember(gameMember);
            }
            //已参与，修改参与状态
            gameMemberMapper.updateGameMember(gameMember.getMatch_id(),join_status);
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(GameMemberServiceImpl.class,"参与比赛时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }
}
