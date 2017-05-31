package com.yunqiu.league.service.impl;

import com.yunqiu.league.dao.ScheduleMapper;
import com.yunqiu.league.service.ScheduleService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.DateUtil;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 赛程数据处理
 */

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleMapper scheduleMapper;

    /**
     * 获取赛程轮次
     * @param league_id
     * @return
     */
    @Override
    public Map<String, Object> selectScheduleRounds(String league_id) {
        try {
            List<Map<String,Object>> result = new ArrayList<>();
            //获取赛事的所有赛程
            List<Map<String,Object>> scheduleList = scheduleMapper.selectSchedule(league_id);
            for (int index=0;index<scheduleList.size();index++){
                Map<String,Object> schedule = scheduleList.get(index);
                for (int item=1;item<=(int)schedule.get("total_rounds");item++){
                    Map<String,Object> result_map = new HashMap<>();
                    result_map.put("schedule_id",schedule.get("schedule_id"));
                    result_map.put("rounds",item);
                    String schedule_name = schedule.get("schedule_name")+" 第"+item+"轮";
                    result_map.put("schedule_name",schedule_name);
                    result.add(result_map);
                }
            }
            return ControllerReturnBase.successResule(result);
        }catch (Exception e){
            LoggerUtil.outError(ScheduleServiceImpl.class,"获取赛程的轮次时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 获取赛程
     * @param schedule_id
     * @return
     */
    @Override
    public Map<String, Object> selectSchedule(String league_id,String schedule_id,int rounds) {
        try {
            //定义当前轮次
            int curve_rounds = 0;
            //赛程id如果为null，计算当前的赛程
            Map<String,Object> schedule = null;
            if (Utils.isNull(schedule_id)){
                //获取当前时间的赛程
                schedule = scheduleMapper.selectCurveSchedule(league_id, DateUtil.getStringTime("yyyy-MM-dd HH:mm:ss"));
                if (schedule == null){
                    //如果没有当前赛程，则获取离当前时间最近(往后)的赛程
                    schedule = scheduleMapper.selectLatelySchedule(league_id,DateUtil.getStringTime("yyyy-MM-dd HH:mm:ss"));
                    if (schedule == null){
                        //如果没有赛程，则获取最后一场赛程
                        schedule = scheduleMapper.selectFinallySchedule(league_id);
                        if (schedule == null){
                            return ControllerReturnBase.errorResule(1502,"当前无赛程");
                        }
                    }
                    //为当前时间以后的赛程或者为最后一场赛程，设置当前轮次为第一轮
                    schedule_id = (String) schedule.get("schedule_id");
                    curve_rounds = 1;
                }
            }
            if (rounds != 0){
                curve_rounds=rounds;
            }
            //计算当前的轮次,如果前端未传值或者传0值，则根据当前时间计算当前的轮次
            if (curve_rounds == 0){
                String rounds_db = scheduleMapper.selectCurveRounds((String) schedule.get("schedule_id"),DateUtil.getStringTime("yyyy-MM-dd HH:mm:ss"));
                if (Utils.isNull(rounds_db)){
                    curve_rounds = 1;
                }else{
                    curve_rounds = Integer.parseInt(rounds_db);
                }
            }
            //获取比赛数据
            List<Map<String,Object>> gameList = scheduleMapper.selectGame(schedule_id,curve_rounds);
            //#############################根据比赛时间，把比赛信息按月分组###############################
            //单条比赛数据
            Map<String,Object> game = null;
            //分组后的结果集
            Map<String,List<Map<String,Object>>> group_Event = new HashMap<>();
            //解析比赛信息列表
            for (int i=0;i<gameList.size();i++){
                //获取到单条比赛信息
                game = gameList.get(i);
                if(group_Event.containsKey(DateUtil.DTDateToString((Date) game.get("game_time"),"yyyy-MM-dd HH:mm:ss"))){
                    group_Event.get(DateUtil.DTDateToString((Date) game.get("game_time"),"yyyy-MM-dd HH:mm:ss")).add(game);
                }else{
                    List<Map<String,Object>> list = new ArrayList<>();
                    list.add(game);
                    group_Event.put(DateUtil.DTDateToString((Date) game.get("game_time"),"yyyy-MM-dd HH:mm:ss"),list);
                }
            }
            //解析出时间组
            List<Map<String,Object>> result_game = new ArrayList<>();
            for (String key:group_Event.keySet()){
                Map<String,Object> result_param = new HashMap<>();
                result_param.put("groups",key);
                result_param.put("game",group_Event.get(key));
                result_game.add(result_param);
            }
            //封装返回参数
            Map<String,Object> return_result = new HashMap<>();
            return_result.put("curve_schedule_id",schedule_id);//当前赛程id
            return_result.put("curve_rounds",curve_rounds);//当前轮次
            return_result.put("share_url","http://101.201.145.244:8089/index.html/#/matches/"+league_id+"/info");
            return_result.put("list",result_game);
            return ControllerReturnBase.successResule(return_result);
        }catch (Exception e){
            LoggerUtil.outError(ScheduleServiceImpl.class,"获取赛程时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 获取积分榜
     * @param league_id
     * @return
     */
    @Override
    public Map<String, Object> selectTabelle(String league_id) {
        try {
            //返回结果集定义
            List<Map<String,Object>> result = new ArrayList<>();
            //获取赛事的所有赛程
            List<Map<String,Object>> list_schedule = scheduleMapper.selectSchedule(league_id);
            //循环赛程，获取赛程的比赛
            List<Map<String,Object>> result_squad = new ArrayList<>();//小组赛结果
            List<Map<String,Object>> result_cup = new ArrayList<>();//杯赛结果
            for (int index=0;index<list_schedule.size();index++){
                //取到当个赛程
                Map<String,Object> schedule = list_schedule.get(index);
                //获取赛程的比赛类型
                int league_type = (int)schedule.get("league_type");
                //定义积分榜结果集
                List<Map<String,Object>> detailed = null;
                //根据比赛类型，获取不同的积分榜结果
                if (league_type == 1 || league_type == 4){
                    //小组赛跟联赛的获取
                    detailed = scheduleMapper.selectTabelleByGroup((String) schedule.get("schedule_id"));
                    //封装该赛程的积分榜
                    Map<String,Object> params = new HashMap<>();
                    params.put("group",schedule.get("schedule_name"));
                    params.put("league_type",league_type);
                    params.put("detailed",detailed);
                    result_squad.add(params);
                }else {
                    //杯赛跟淘汰赛的获取
                    detailed = scheduleMapper.selectTabelleByWeed((String) schedule.get("schedule_id"));
                    //封装该赛程的积分榜
                    Map<String,Object> params = new HashMap<>();
                    params.put("group",schedule.get("schedule_name"));
                    params.put("league_type",league_type);
                    params.put("detailed",detailed);
                    result_cup.add(params);
                }
            }
            //排序
            for (int item = 0;item < result_cup.size();item++){
                result.add(result_cup.get(item));
            }
            for (int index = result_squad.size()-1;index>=0;index--){
                result.add(result_squad.get(index));
            }
            return ControllerReturnBase.successResule(result);
        }catch (Exception e){
            LoggerUtil.outError(ScheduleServiceImpl.class,"获取积分榜时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 获取榜单
     * @param league_id
     * @return
     */
    @Override
    public Map<String, Object> selectCrunchies(String league_id) {
        try {
            Map<String,Object> result = new HashMap<>();
            List<Map<String,Object>> goal_list = scheduleMapper.selectCrunchies(1,league_id);
            List<Map<String,Object>> assist_list = scheduleMapper.selectCrunchies(6,league_id);
            List<Map<String,Object>> yellowCard_list = scheduleMapper.selectCrunchies(4,league_id);
            List<Map<String,Object>> redCard_list = scheduleMapper.selectCrunchies(3,league_id);
            result.put("goal_list",goal_list);
            result.put("assist_list",assist_list);
            result.put("yellowCard_list",yellowCard_list);
            result.put("redCard_list",redCard_list);
            return ControllerReturnBase.successResule(result);
        }catch (Exception e){
            LoggerUtil.outError(ScheduleServiceImpl.class,"获取榜单时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }
}
