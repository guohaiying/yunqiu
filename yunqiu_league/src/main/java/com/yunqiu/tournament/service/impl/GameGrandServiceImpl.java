package com.yunqiu.tournament.service.impl;

import com.google.gson.reflect.TypeToken;
import com.yunqiu.general.dao.TeamMapper;
import com.yunqiu.tournament.dao.GameGrandMapper;
import com.yunqiu.tournament.dao.TournamentMapper;
import com.yunqiu.tournament.model.GameGrand;
import com.yunqiu.tournament.model.Tournament;
import com.yunqiu.tournament.service.GameGrandService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.GsonUtil;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 比赛战报数据处理
 */

@Service
public class GameGrandServiceImpl implements GameGrandService {
    @Autowired
    private GameGrandMapper gameGrandMapper;
    @Autowired
    private TournamentMapper tournamentMapper;
    @Autowired
    private TeamMapper teamMapper;

    /**
     * 录入比赛战报
     * @param data_json
     * @param user_id
     * @return
     */
    @Override
    @Transactional //开启事务控制
    public Map<String, Object> enterGrand(String data_json, String user_id) {
        try {
            //把json字符串转换为Map对象
            Map<String,Object> data_params = GsonUtil.fromJson(data_json,new TypeToken<Map<String, Object>>() {});
            //获取到比赛
            Tournament tournament = tournamentMapper.selectGameByGameId((String) data_params.get("game_id"));
            if (tournament == null){
                return ControllerReturnBase.errorResule(1501,"比赛不存在");
            }
            //验证权限，只有比赛创建者跟AB两个球队的管理员可录入
            if (!user_id.equals(tournament.getUser_id())){
                //当前用户不是比赛的创建者，验证是否为A队管理员
                Map<String,Object> teamA_member = teamMapper.selectTeamMemberByTeamIdAndUserId(tournament.getEntry_teamA(),user_id);
                if (teamA_member == null || (int)teamA_member.get("jurisdiction") == 0){
                    //不是A队成员或者不是A队的管理员,验证是否为B队管理员
                    Map<String,Object> teamB_member = teamMapper.selectTeamMemberByTeamIdAndUserId(tournament.getEntry_teamB(),user_id);
                    if (teamB_member == null || (int)teamB_member.get("jurisdiction") == 0){
                        return ControllerReturnBase.errorResule(1502,"无权限");
                    }
                }
            }
            //###############################录入比赛战报##############################
            GameGrand gameGrand = null;
            //解析进球/助攻
            List<Map<String,Object>> goal_params = (List<Map<String,Object>>) data_params.get("goal");
            if (goal_params != null){
                for (int index = 0;index<goal_params.size();index++){
                    if (Utils.isNull((String) goal_params.get(index).get("team_id")) || Utils.isNull((String) goal_params.get(index).get("user_id"))){
                        return ControllerReturnBase.errorResule(1501,"录入进球数，缺少球队id或者用户id");
                    }
                    String player = (String) goal_params.get(index).get("user_id");
                    //进球
                    gameGrand = new GameGrand();
                    String id = Utils.getUUID();
                    gameGrand.setGrand_id(id);
                    gameGrand.setGame_id(tournament.getGame_id());
                    gameGrand.setType(1);
                    gameGrand.setTeam_id((String) goal_params.get(index).get("team_id"));
                    gameGrand.setUser_id(player.split(",")[0]);
                    gameGrandMapper.insert(gameGrand);
                    //助攻
                    if (player.split(",").length == 2){
                        gameGrand = new GameGrand();
                        gameGrand.setGrand_id(Utils.getUUID());
                        gameGrand.setGame_id(tournament.getGame_id());
                        gameGrand.setType(6);
                        gameGrand.setTeam_id((String) goal_params.get(index).get("team_id"));
                        gameGrand.setUser_id(player.split(",")[1]);
                        gameGrandMapper.insert(gameGrand);
                        gameGrand.setParent_id(id);
                    }

                }
            }
            //解析点球
            int spot_teamA = 0;//A队点球数
            int spot_teamB = 0;//B队点球数
            List<Map<String,Object>> penalty_params = (List<Map<String,Object>>) data_params.get("penalty");
            if (penalty_params != null){
                for (int index = 0;index<penalty_params.size();index++){
                    int number = new Long((String) penalty_params.get(index).get("number")).intValue();
                    if (Utils.isNull((String) penalty_params.get(index).get("team_id")) || number == 0){
                        return ControllerReturnBase.errorResule(1501,"录入点球，缺少球队id或者点球数");
                    }
                    gameGrand = new GameGrand();
                    gameGrand.setGrand_id(Utils.getUUID());
                    gameGrand.setGame_id(tournament.getGame_id());
                    gameGrand.setType(2);
                    gameGrand.setTeam_id((String) penalty_params.get(index).get("team_id"));
                    gameGrand.setResult(number);
                    gameGrandMapper.insert(gameGrand);
                    if (gameGrand.getTeam_id().equals(tournament.getEntry_teamA())){
                        spot_teamA = number;
                    }else if (gameGrand.getTeam_id().equals(tournament.getEntry_teamB())){
                        spot_teamB = number;
                    }
                }
            }
            //解析红牌
            List<Map<String,Object>> red_card = (List<Map<String,Object>>) data_params.get("red_card");
            if (red_card != null){
                for (int index = 0;index<red_card.size();index++){
                    if (Utils.isNull((String) red_card.get(index).get("team_id")) || Utils.isNull((String) red_card.get(index).get("user_id"))){
                        return ControllerReturnBase.errorResule(1501,"录入红牌，缺少球队id或者用户id");
                    }
                    gameGrand = new GameGrand();
                    gameGrand.setGrand_id(Utils.getUUID());
                    gameGrand.setGame_id(tournament.getGame_id());
                    gameGrand.setType(3);
                    gameGrand.setTeam_id((String) red_card.get(index).get("team_id"));
                    gameGrand.setUser_id((String) red_card.get(index).get("user_id"));
                    gameGrandMapper.insert(gameGrand);
                }
            }
            //解析黄牌
            List<Map<String,Object>> yellow_card = (List<Map<String,Object>>) data_params.get("yellow_card");
            if (yellow_card != null){
                for (int index = 0;index<yellow_card.size();index++){
                    if (Utils.isNull((String) yellow_card.get(index).get("team_id")) || Utils.isNull((String) yellow_card.get(index).get("user_id"))){
                        return ControllerReturnBase.errorResule(1501,"录入黄牌，缺少球队id或者用户id");
                    }
                    gameGrand = new GameGrand();
                    gameGrand.setGrand_id(Utils.getUUID());
                    gameGrand.setGame_id(tournament.getGame_id());
                    gameGrand.setType(4);
                    gameGrand.setTeam_id((String) yellow_card.get(index).get("team_id"));
                    gameGrand.setUser_id((String) yellow_card.get(index).get("user_id"));
                    gameGrandMapper.insert(gameGrand);
                }
            }
            //解析乌龙球
            List<Map<String,Object>> own_goal = (List<Map<String,Object>>) data_params.get("own_goal");
            if (own_goal != null){
                for (int index = 0;index<own_goal.size();index++){
                    if (Utils.isNull((String) own_goal.get(index).get("team_id")) || Utils.isNull((String) own_goal.get(index).get("user_id"))){
                        return ControllerReturnBase.errorResule(1501,"录入乌龙球，缺少球队id或者用户id");
                    }
                    gameGrand = new GameGrand();
                    gameGrand.setGrand_id(Utils.getUUID());
                    gameGrand.setGame_id(tournament.getGame_id());
                    gameGrand.setType(5);
                    gameGrand.setTeam_id((String) own_goal.get(index).get("team_id"));
                    gameGrand.setUser_id((String) own_goal.get(index).get("user_id"));
                    gameGrandMapper.insert(gameGrand);
                }
            }
            //修改比赛比分跟点球数
            int score_teamA = new Long((String) data_params.get("score_teamA")).intValue();
            int score_teamB = new Long((String) data_params.get("score_teamB")).intValue();
            tournament.setScore_teamA(score_teamA);
            tournament.setScore_teamB(score_teamB);
            tournament.setSpot_teamA(spot_teamA);
            tournament.setSpot_teamB(spot_teamB);
            tournament.setScore_status(1);
            tournamentMapper.updateGame(tournament);
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(GameGrandServiceImpl.class,"录入比赛战报时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 获取比赛的比分
     * @param game_id
     * @return
     */
    @Override
    public Map<String,Object> selectGrand(String game_id){
        try {
            //获取全项数据
            List<Map<String,Object>> entire = gameGrandMapper.selectEntireGrand(game_id);
            //#######################################################################
            //解析全项数据
            //########################################################################
            //全项数据归类结果集
            List<Map<String,Object>> entire_subsumption = new ArrayList<>();
            //全项数据分类,下标与文档对应的数字要对应
            String[] group = {"","进球","点球","红牌","黄牌","乌龙球","助攻","射门","射正","过人","威胁传球","任意球","角球","抢断","解围","扑救","犯规"};
            //循环分类，从entire中找到该下标对应类型数据
            for (int item = 1;item<group.length;item++){
                List<Map<String,Object>> type_data = new ArrayList<>();
                //解析全项总数据,获取到该下标对应的类型数据
                for (int index = 0;index<entire.size();index++){
                    Map<String,Object> oneData = entire.get(index);
                    if ((int)oneData.get("type") == item){
                        type_data.add(oneData);
                    }
                }
                //解析获取到的同类数据，进行封装
                Map<String,Object> wrapper_result = new HashMap<>();
                if(type_data.size() == 0){
                    //如果该类型数据没有，则封装成0
                    wrapper_result.put("total_numberA",0);
                    wrapper_result.put("total_numberB",0);
                    wrapper_result.put("total_name",group[item]);
                }else if (type_data.size() == 1){
                    //有一条数据，判断是主队还是客队，另外一条以0补充
                    if ((int)(type_data.get(0).get("attribution")) == 1){
                        wrapper_result.put("total_numberA",type_data.get(0).get("total_number"));
                        wrapper_result.put("total_numberB",0);
                    }else {
                        wrapper_result.put("total_numberA",0);
                        wrapper_result.put("total_numberB",type_data.get(0).get("total_number"));
                    }
                    wrapper_result.put("total_name",group[item]);
                }else {
                    //如果为两条数据，则分别设置各队数据
                    if ((int)(type_data.get(0).get("attribution")) == 1){
                        wrapper_result.put("total_numberA",type_data.get(0).get("total_number"));
                        wrapper_result.put("total_numberB",type_data.get(1).get("total_number"));
                    }else {
                        wrapper_result.put("total_numberA",type_data.get(1).get("total_number"));
                        wrapper_result.put("total_numberB",type_data.get(0).get("total_number"));
                    }
                    wrapper_result.put("total_name",group[item]);
                }
                entire_subsumption.add(wrapper_result);
            }

            //获取进球(助攻)
            List<Map<String,Object>> goal_list= gameGrandMapper.selectGrandByType(game_id,1);
            for (int index=0;index < goal_list.size();index++){
                Map<String,Object> goal = goal_list.get(index);
                Map<String,Object> assists = gameGrandMapper.selectAssists((String) goal.get("grand_id"));
                goal_list.get(index).put("assists",assists);
            }
            //获取黄牌
            List<Map<String,Object>> yellow_list= gameGrandMapper.selectGrandByType(game_id,4);
            //获取红牌
            List<Map<String,Object>> red_list= gameGrandMapper.selectGrandByType(game_id,3);
            //获取点球
            List<Map<String,Object>> penalty_list= gameGrandMapper.selectGrandByType(game_id,2);
            //获取乌龙球
            List<Map<String,Object>> own_list= gameGrandMapper.selectGrandByType(game_id,5);
            //封装返回数据
            Map<String,Object> result = new HashMap<>();
            result.put("goal",goal_list);
            result.put("yellow",yellow_list);
            result.put("red",red_list);
            result.put("penalty",penalty_list);
            result.put("own",own_list);
            result.put("entire",entire);
            result.put("entire_subsumption",entire_subsumption);
            return ControllerReturnBase.successResule(result);
        }catch (Exception e){
            LoggerUtil.outError(GameGrandServiceImpl.class,"获取比赛的比分时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }
}