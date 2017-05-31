package com.yunqiu.service.impl;

import com.yunqiu.dao.*;
import com.yunqiu.model.*;
import com.yunqiu.service.ScheduleService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleMapper scheduleMapper;

    @Autowired
    protected ScheduleRelateGameMapper scheduleRelateGameMapper;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private TournamentMapper tournamentMapper;

    @Autowired
    private ScheduleRankingMapper scheduleRankingMapper;

    // 查询总条数
    public Integer selectCount(PageCrt page) {
        return scheduleRelateGameMapper.selectTotalPage(page);
    }

    //分页查询
    public List<Map> selectPaging(PageCrt page) {
        return scheduleRelateGameMapper.selectePaging(page);
    }

    @Override
    public Map<String, Object> addSchedule(Schedule schedule) {
        try {

            String scheduleId=Utils.getID(22);
            schedule.setScheduleId(scheduleId);
            int result = scheduleMapper.insertSchedule(schedule);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }

            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(ScheduleService.class, "队服颜色添加时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    @Override
    public Map<String, Object> deleteSchedule(String scheduleId) {
        try {
            int result = scheduleMapper.deleteScheduleById(scheduleId);
            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(ScheduleService.class, "队服颜色删除时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    @Override
    public Map<String, Object> getScheduleList(String leagueId) {
        try {
            List<Schedule> map = scheduleMapper.getScheduleList(leagueId);
            return ControllerReturnBase.successResule(map);
        } catch (Exception e) {
            LoggerUtil.outError(ScheduleService.class, "查询队服颜色时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    @Override
    public Map<String, Object> addScheduleRelateGame(ScheduleRelateGame scheduleRelateGame) {
        try {

            String relateId=Utils.getID(22);
            scheduleRelateGame.setRelateId(relateId);
            int result = scheduleRelateGameMapper.insertScheduleRelateGame(scheduleRelateGame);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }

            //根据比赛id获取两个球队
            Map<String, Object> map = tournamentMapper.getEntryTeamNameAB(scheduleRelateGame.getGameId());
            String entryTeamAId = map.get("entryTeamAId")+"";
            String entryTeamBId = map.get("entryTeamBId")+"";

            int count = scheduleRankingMapper.selectCountScheduleRanking(entryTeamAId,scheduleRelateGame.getScheduleId());
            if(count<=0){
                ScheduleRanking scheduleRanking = new ScheduleRanking();
                String ranking_id=Utils.getID(22);
                scheduleRanking.setRanking_id(ranking_id);
                scheduleRanking.setSchedule_id(scheduleRelateGame.getScheduleId());
                scheduleRanking.setTeam_id(entryTeamAId);
                scheduleRanking.setCurrent_ranking(0);
                scheduleRanking.setLifting(0);
                scheduleRanking.setGame_number(0);
                scheduleRanking.setVictory(0);
                scheduleRanking.setFlat(0);
                scheduleRanking.setNegation(0);
                scheduleRanking.setGoal(0);
                scheduleRanking.setLose(0);
                scheduleRanking.setIntegral(0);
                scheduleRankingMapper.insertScheduleRanking(scheduleRanking);
            }

            int count2 = scheduleRankingMapper.selectCountScheduleRanking(entryTeamBId,scheduleRelateGame.getScheduleId());
            if(count2<=0){
                ScheduleRanking scheduleRanking2 = new ScheduleRanking();
                String ranking_id2=Utils.getID(22);
                scheduleRanking2.setRanking_id(ranking_id2);
                scheduleRanking2.setSchedule_id(scheduleRelateGame.getScheduleId());
                scheduleRanking2.setTeam_id(entryTeamBId);
                scheduleRanking2.setCurrent_ranking(0);
                scheduleRanking2.setLifting(0);
                scheduleRanking2.setGame_number(0);
                scheduleRanking2.setVictory(0);
                scheduleRanking2.setFlat(0);
                scheduleRanking2.setNegation(0);
                scheduleRanking2.setGoal(0);
                scheduleRanking2.setLose(0);
                scheduleRanking2.setIntegral(0);
                scheduleRankingMapper.insertScheduleRanking(scheduleRanking2);
            }


            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(ScheduleService.class, "队服颜色添加时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    @Override
    public Map<String, Object> updateScheduleRelateGame(ScheduleRelateGame scheduleRelateGame){
        try {
            int result = scheduleRelateGameMapper.updateScheduleRelateGame(scheduleRelateGame);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }

            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(ScheduleService.class, "队服颜色修改时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    @Override
    public Map<String, Object> deleteScheduleRelateGame(String relateId,String userName,String  password,String  userId) {
        try {

            //查询当前登录用户的帐号和密码
            if (Utils.isNull(userName) || Utils.isNull(password)) {
                return ControllerReturnBase.errorResule(1501, "账户或密码未填写");
            }
            //根据账户名查询用户
            AdminUser adminUser = usersMapper.selectUserByUserName(userName);
            if (adminUser == null) {
                return ControllerReturnBase.errorResule(1502, "账户不存在");
            }
            //验证是否是当前登录账户
            if(!adminUser.getAdminId().equals(userId)){
                return ControllerReturnBase.errorResule(1502, "请输入当前登录的帐号");
            }
            //验证密码是否正确
            if (!adminUser.getPassword().equals(Utils.SHAEncrypt(password))) {
                return ControllerReturnBase.errorResule(1502, "密码错误");
            }
            //验证账户是否被冻结
            if (adminUser.getStatus()==2) {
                return ControllerReturnBase.errorResule(1502, "账户被冻结 请联系管理员");
            }

            int result = scheduleRelateGameMapper.deleteScheduleRelateGameById(relateId);
            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(ScheduleService.class,   "队服颜色删除时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }








}
