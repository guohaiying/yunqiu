package com.yunqiu.service.impl;

import com.yunqiu.dao.ScheduleRankingMapper;
import com.yunqiu.dao.ScheduleRelateGameMapper;
import com.yunqiu.dao.TournamentMapper;
import com.yunqiu.model.PageCrt;
import com.yunqiu.model.ScheduleRanking;
import com.yunqiu.model.Tournament;
import com.yunqiu.service.TournamentService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.DateUtil;
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
public class TournamentServiceImpl implements TournamentService {
    @Autowired
    private TournamentMapper tournamentMapper;

    @Autowired
    private ScheduleRankingMapper scheduleRankingMapper;

    @Autowired
    private ScheduleRelateGameMapper scheduleRelateGameMapper;

    // 查询总条数赛程
    public Integer selectCount(PageCrt page) {
        return tournamentMapper.selectTotalPage(page);
    }

    //分页查询赛程
    public List<Map> selectPaging(PageCrt page) {
        return tournamentMapper.selectePaging(page);
    }

    // 查询比赛总条数
    @Override
    public Integer selectCountTournament(PageCrt page){
        return tournamentMapper.selectCountTournament(page);
    }

    //分页查询比赛
    @Override
    public List<Map> selectPagingTournament(PageCrt page){
        return tournamentMapper.selectPagingTournament(page);
    }

    /**
     * 赛程添加
     * @return
     */
    @Override
    public Map<String, Object> addTournament(Tournament tournament) {
        try {
            //验证参数是否为空
            /*if (Utils.isNull(place.getPlaceName())) {
                return ControllerReturnBase.errorResule(1501, "场馆名称未填写");
            }*/
            //添加用户
            String newGameId=Utils.getID(22);
            //place.setCreateTime(new Date());
            int result = tournamentMapper.insertTournament(tournament.getRounds(),newGameId,tournament.getGameId(),tournament.getUserId(),tournament.getLeagueId());
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }

            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(TournamentService.class, "场地添加时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }


    /**
     * 比赛添加
     * @return
     */
    @Override
    public Map<String, Object> addTournamentList(Tournament tournament) {
        try {
            if (tournament.getGameTime().getTime()> tournament.getApplyEndTime().getTime()) {
                return ControllerReturnBase.errorResule(1501, "开始时间必须晚于报名结束时间，请重新选择");
            }
            String gameId=Utils.getID(22);
            tournament.setGameId(gameId);
            tournament.setScoreStatus(2);
            tournament.setEnteringStatus(1);
            long nowdate = new Date().getTime();
            Date endTime = DateUtil.assignTimeLaterDayTime(tournament.getGameTime(),tournament.getContinueTime());

            if(nowdate<=tournament.getApplyEndTime().getTime()){//3，报名中  报名截止时间之前
                tournament.setGameStatus(3);
            }else if(nowdate>tournament.getApplyEndTime().getTime() && nowdate<=tournament.getGameTime().getTime()){//4，报名结束  报名截止时间-比赛开始时间
                tournament.setGameStatus(4);
            }else if(nowdate>tournament.getGameTime().getTime() && nowdate<=endTime.getTime()){//5，进行中  比赛开始时间到比赛开始时间往后延续持续时间
                tournament.setGameStatus(5);
            }else if(nowdate>endTime.getTime()){//6，已结束   比赛开始时间往后延持续时间之后
                tournament.setGameStatus(6);
            }
            int result = tournamentMapper.insertTournamentList(tournament);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }

            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(TournamentService.class, "场地添加时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 赛程修改
     * @return
     */
    @Override
    public Map<String, Object> updateTournament(Tournament tournament) {
        try {
            int result = tournamentMapper.updateTournament(tournament);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }

            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(TournamentService.class, "场地修改时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 比赛信息修改
     * @return
     */
    @Override
    public Map<String, Object> updateTournamentList(Tournament tournament) {
        try {
            if (tournament.getGameTime().getTime()> tournament.getApplyEndTime().getTime()) {
                return ControllerReturnBase.errorResule(1501, "开始时间必须晚于报名结束时间，请重新选择");
            }

            Map<String,Object>  mapobj = scheduleRelateGameMapper.getScheduleRelateGame(tournament.getGameId());
            Map<String, Object> map = tournamentMapper.getEntryTeamNameAB(tournament.getGameId());
            if(mapobj!=null){
                //先把原的胜负平数减1
                int y_scoreTeamA = Integer.parseInt(map.get("scoreTeamA")+"");
                int y_scoreTeamB = Integer.parseInt(map.get("scoreTeamB")+"");
                String entryTeamA = tournament.getEntryTeamA();
                String entryTeamB = tournament.getEntryTeamB();
                if(y_scoreTeamA>y_scoreTeamB){
                    ScheduleRanking scheduleRanking = new ScheduleRanking();
                    scheduleRanking.setTeam_id(entryTeamA);
                    scheduleRanking.setSchedule_id(mapobj.get("schedule_id")+"");
                    scheduleRankingMapper.updatescheduleRankingVictoryJian(scheduleRanking);

                    ScheduleRanking scheduleRanking2 = new ScheduleRanking();
                    scheduleRanking2.setTeam_id(entryTeamB);
                    scheduleRanking2.setSchedule_id(mapobj.get("schedule_id")+"");
                    scheduleRankingMapper.updatescheduleRankingnNegationJian(scheduleRanking2);
                }else if(y_scoreTeamA<y_scoreTeamB){
                    ScheduleRanking scheduleRanking = new ScheduleRanking();
                    scheduleRanking.setTeam_id(entryTeamB);
                    scheduleRanking.setSchedule_id(mapobj.get("schedule_id")+"");
                    scheduleRankingMapper.updatescheduleRankingVictoryJian(scheduleRanking);

                    ScheduleRanking scheduleRanking2 = new ScheduleRanking();
                    scheduleRanking2.setTeam_id(entryTeamA);
                    scheduleRanking2.setSchedule_id(mapobj.get("schedule_id")+"");
                    scheduleRankingMapper.updatescheduleRankingnNegationJian(scheduleRanking2);
                }else if(y_scoreTeamA==y_scoreTeamB){
                    ScheduleRanking scheduleRanking = new ScheduleRanking();
                    scheduleRanking.setTeam_id(entryTeamA);
                    scheduleRanking.setSchedule_id(mapobj.get("schedule_id")+"");
                    scheduleRankingMapper.updatescheduleRankingnFlatJian(scheduleRanking);

                    ScheduleRanking scheduleRanking2 = new ScheduleRanking();
                    scheduleRanking2.setTeam_id(entryTeamB);
                    scheduleRanking2.setSchedule_id(mapobj.get("schedule_id")+"");
                    scheduleRankingMapper.updatescheduleRankingnFlatJian(scheduleRanking2);
                }
            }

            long nowdate = new Date().getTime();
            Date endTime = DateUtil.assignTimeLaterDayTime(tournament.getGameTime(),tournament.getContinueTime());

            if(nowdate<=tournament.getApplyEndTime().getTime()){//3，报名中  报名截止时间之前
                tournament.setGameStatus(3);
            }else if(nowdate>tournament.getApplyEndTime().getTime() && nowdate<=tournament.getGameTime().getTime()){//4，报名结束  报名截止时间-比赛开始时间
                tournament.setGameStatus(4);
            }else if(nowdate>tournament.getGameTime().getTime() && nowdate<=endTime.getTime()){//5，进行中  比赛开始时间到比赛开始时间往后延续持续时间
                tournament.setGameStatus(5);
            }else if(nowdate>endTime.getTime()){//6，已结束   比赛开始时间往后延持续时间之后
                tournament.setGameStatus(6);
            }
            tournament.setEnteringStatus(1);
            int result = tournamentMapper.updateTournamentList(tournament);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }

            if(tournament.getGameStatus() == 6){
                if(mapobj!=null){
                    int scoreTeamA = tournament.getScoreTeamA();
                    int scoreTeamB = tournament.getScoreTeamB();
                    String entryTeamA = tournament.getEntryTeamA();
                    String entryTeamB = tournament.getEntryTeamB();
                    if(scoreTeamA>scoreTeamB){
                        ScheduleRanking scheduleRanking = new ScheduleRanking();
                        scheduleRanking.setTeam_id(entryTeamA);
                        scheduleRanking.setSchedule_id(mapobj.get("schedule_id")+"");
                        scheduleRankingMapper.updatescheduleRankingVictory(scheduleRanking);

                        ScheduleRanking scheduleRanking2 = new ScheduleRanking();
                        scheduleRanking2.setTeam_id(entryTeamB);
                        scheduleRanking2.setSchedule_id(mapobj.get("schedule_id")+"");
                        scheduleRankingMapper.updatescheduleRankingnNegation(scheduleRanking2);
                    }else if(scoreTeamA<scoreTeamB){
                        ScheduleRanking scheduleRanking = new ScheduleRanking();
                        scheduleRanking.setTeam_id(entryTeamB);
                        scheduleRanking.setSchedule_id(mapobj.get("schedule_id")+"");
                        scheduleRankingMapper.updatescheduleRankingVictory(scheduleRanking);

                        ScheduleRanking scheduleRanking2 = new ScheduleRanking();
                        scheduleRanking2.setTeam_id(entryTeamA);
                        scheduleRanking2.setSchedule_id(mapobj.get("schedule_id")+"");
                        scheduleRankingMapper.updatescheduleRankingnNegation(scheduleRanking2);
                    }else if(scoreTeamA==scoreTeamB){
                        ScheduleRanking scheduleRanking = new ScheduleRanking();
                        scheduleRanking.setTeam_id(entryTeamA);
                        scheduleRanking.setSchedule_id(mapobj.get("schedule_id")+"");
                        scheduleRankingMapper.updatescheduleRankingnFlat(scheduleRanking);

                        ScheduleRanking scheduleRanking2 = new ScheduleRanking();
                        scheduleRanking2.setTeam_id(entryTeamB);
                        scheduleRanking2.setSchedule_id(mapobj.get("schedule_id")+"");
                        scheduleRankingMapper.updatescheduleRankingnFlat(scheduleRanking2);
                    }
                }
            }

            //修改排序
            List<ScheduleRanking> schList = scheduleRankingMapper.findRanking(tournament.getGameId());
            int current_ranking = 0;
            for(int i=0;i<schList.size();i++){
                ScheduleRanking scheduleRanking = schList.get(i);
                if(i!=0){
                    ScheduleRanking sh1 = schList.get(i-1);
                    if(sh1.getIntegral()!=scheduleRanking.getIntegral()){
                        current_ranking = current_ranking + 1;
                    }
                }else{
                    current_ranking = 1;
                }
                scheduleRanking.setCurrent_ranking(current_ranking);
                scheduleRankingMapper.updatescheduleCurrentRanking(scheduleRanking);
            }

            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(TournamentService.class, "场地修改时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 赛程删除
     * @return
     */
    @Override
    public Map<String, Object> deleteTournament(String gameId){
        try {
            int result = tournamentMapper.deleteTournamentById(gameId);
            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(TournamentService.class, "赛程删除时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }


    /**
     * 获取所有比赛名称
     * @return
     */
    @Override
    public Map<String, Object> getTournamentList(){
        try {
            List<Tournament> map = tournamentMapper.getTournamentList();
            return ControllerReturnBase.successResule(map);
        } catch (Exception e) {
            LoggerUtil.outError(TournamentService.class, "查询比赛名称时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }



    @Override
    public Map<String, Object> getEntryTeamAList(String gameId){
        try {
            List<Map<String,Object>> map = tournamentMapper.getEntryTeamAList(gameId);
            return ControllerReturnBase.successResule(map);
        } catch (Exception e) {
            LoggerUtil.outError(TournamentService.class, "查询比赛名称时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    @Override
    public Map<String, Object> getEntryTeamAName(String gameId){
        try {
            String teamAName = tournamentMapper.getEntryTeamAName(gameId);
            return ControllerReturnBase.successResule(teamAName);
        } catch (Exception e) {
            LoggerUtil.outError(TournamentService.class, "查询比赛名称时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }



    @Override
    public Map<String, Object> getEntryTeamBList(String gameId){
        try {
            List<Map<String,Object>> map = tournamentMapper.getEntryTeamBList(gameId);
            return ControllerReturnBase.successResule(map);
        } catch (Exception e) {
            LoggerUtil.outError(TournamentService.class, "查询比赛名称时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    @Override
    public Map<String, Object> getEntryTeamAllUser(String gameId){
        try {
            List<Map<String,Object>> map = tournamentMapper.getEntryTeamAllUser(gameId);
            return ControllerReturnBase.successResule(map);
        } catch (Exception e) {
            LoggerUtil.outError(TournamentService.class, "查询比赛名称时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    @Override
    public Map<String, Object> getEntryTeamByTeamId(String gameId,String teamId){
        try {
            List<Map<String,Object>> map = tournamentMapper.getEntryTeamByTeamId(gameId,teamId);
            return ControllerReturnBase.successResule(map);
        } catch (Exception e) {
            LoggerUtil.outError(TournamentService.class, "查询比赛名称时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    @Override
    public Map<String, Object> getEntryTeamBName(String gameId){
        try {
            String teamAName = tournamentMapper.getEntryTeamBName(gameId);
            return ControllerReturnBase.successResule(teamAName);
        } catch (Exception e) {
            LoggerUtil.outError(TournamentService.class, "查询比赛名称时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    @Override
    public Map<String, Object> getEntryTeamNameAB(String gameId){
        try {
            Map<String, Object> teamMap = tournamentMapper.getEntryTeamNameAB(gameId);
            return ControllerReturnBase.successResule(teamMap);
        } catch (Exception e) {
            LoggerUtil.outError(TournamentService.class, "查询比赛名称时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    @Override
    public Map<String, Object> getEntryTeamNameABMap(String gameId){
        Map<String, Object> teamMap = tournamentMapper.getEntryTeamNameAB(gameId);
        return teamMap;
    }

}
