package com.yunqiu.league.service.impl;

import com.google.gson.reflect.TypeToken;
import com.yunqiu.league.dao.JoinLeagueMapper;
import com.yunqiu.league.dao.LeagueMapper;
import com.yunqiu.general.dao.TeamMapper;
import com.yunqiu.league.service.LeagueService;
import com.yunqiu.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 赛事管理，业务实现
 */
@Service
public class LeagueServiceImpl implements LeagueService {
    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private LeagueMapper leagueMapper;
    @Autowired
    private JoinLeagueMapper joinLeagueMapper;

    /**
     * 获取赛事列表的赛选条件
     * @return
     */
    @Override
    public Map<String, Object> leagueListScreen(String user_id,int type) {
        try {
            //返回的结果集定义
            Map<String,Object> result_param = new HashMap<>();
            /*----------------状态筛选条件--------------------*/
            List<Map<String,Object>> status_param = new ArrayList<>();//状态结果集
            //所有状态名称，根据顺序列出
            String[] league_status_name = {"全部","报名中","进行中","已结束","已取消","报名已停止"};
            //循环状态名
            for (int index=0;index<league_status_name.length;index++){
                Map<String,Object> league_status = new HashMap<>();
                String status_name = league_status_name[index];
                league_status.put("status_name",status_name);
                league_status.put("status_value",index);
                status_param.add(league_status);
            }
            result_param.put("status",status_param);
             /*----------------根据type判断是球队筛选条件还是赛制筛选条件 1:球队赛事列表 2：发现赛事列表--------------------*/
             if (type == 2){
                 //发现的赛事列表，返回赛制筛选条件
                 List<Map<String,Object>> game_system_result = new ArrayList<>();//赛制结果集
                 //所有赛制名称，根据顺序列出
                 String[] game_system_name = {"全部","3人制","5人制","7人制","8人制","9人制","11人制"};
                 //循环赛制名
                 for (int index=0;index<game_system_name.length;index++){
                     Map<String,Object> game_system = new HashMap<>();
                     game_system.put("game_system_name",game_system_name[index]);
                     game_system.put("game_system_value",index);
                     game_system_result.add(game_system);
                 }
                 result_param.put("game_system",game_system_result);
             }else{
                 //球队的赛事列表，返回球队筛选条件
                 List<Map<String,Object>> team_param = teamMapper.selectTeamList(user_id);
                 result_param.put("team",team_param);
             }
            return ControllerReturnBase.successResule(result_param);
        }catch (Exception e){
            LoggerUtil.outError(LeagueServiceImpl.class,"获取赛事列表的筛选条件时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 获取我所在球队的赛事/发现模块的赛事列表
     * @param pageNum
     * @param pageSize
     * @param team_id
     * @param status
     * @return
     */
    @Override
    public Map<String, Object> meTeamLeagueList(int pageNum, int pageSize, String team_id, int status,String user_id,int game_system,int type) {
        try {
            if (pageNum < 1){
                return ControllerReturnBase.errorResule(1501,"页码值错误，页码最小值为1");
            }
            //获取总条数
            int countTotal = leagueMapper.meTeamLeagueListTotal(user_id,type);
            //计算总页数
            int pageTotal = countTotal%pageSize == 0 ?countTotal/pageSize:(countTotal/pageSize)+1;
            //计算起始行索引
            int start_now = (pageNum-1)*pageSize;
            //封装查询条件
            Map<String,Object> db_params = new HashMap<>();
            db_params.put("start_now",start_now);
            db_params.put("pageSize",pageSize);
            db_params.put("user_id",user_id);
            db_params.put("team_id",team_id);
            db_params.put("status",status);
            db_params.put("game_system",game_system);
            db_params.put("type",type);
            //查询赛事
            List<Map<String,Object>> db_result = leagueMapper.meTeamLeagueList(db_params);
            //返回数据包装
            Map<String,Object> return_result = new HashMap<>();
            return_result.put("pageNum",pageNum);
            return_result.put("pageSize",pageSize);
            return_result.put("pageTotal",pageTotal);
            return_result.put("list",db_result);
            return ControllerReturnBase.successResule(return_result);
        }catch (Exception e){
            LoggerUtil.outError(LeagueServiceImpl.class,"获取我所在球队的赛事时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 获取赛事详情
     * @param league_id
     * @param user_id
     * @return
     */
    @Override
    public Map<String, Object> leagueInfo(String league_id, String user_id) {
        try {
            //获取赛事信息
            Map<String,Object> leagueInfo= leagueMapper.leagueInfo(league_id);
            if (leagueInfo == null){
                return ControllerReturnBase.errorResule(1502,"赛事不存在");
            }
            //根据赛事状态，给出是否显示按钮
            int showButton = 1;
            if ((int)leagueInfo.get("status") == 1 ){
                //比赛为报名中，验证用户是否有 拥有管理权限的球队
                Map<String,Object> params = new HashMap<>();
                params.put("user_id",user_id);
                String json = HttpsClientUtil.doGet("http://101.201.145.244:8083/team/inte/selectManagementTeam",params);
                Map<String,Object> result_team = GsonUtil.fromJson(json,new TypeToken<Map<String, Object>>(){});
                List<Map<String,Object>> team_list = (List<Map<String,Object>>) result_team.get("data");
                if (team_list.size() != 0){
                    showButton = 2;
                }
            }
            //获取赛事报名信息
            int sign_status = 4;
            List<Map<String,Object>> joinStatus = joinLeagueMapper.selectJoinStatus(user_id);
            if (joinStatus.size() != 0){
                sign_status = (int)joinStatus.get(0).get("audit_status");
            }
            //返回参数封装
            Map<String,Object> result_param = new HashMap<>();
            result_param.put("info",leagueInfo);
            result_param.put("share_url","http://101.201.145.244:8089/index.html/#/matches/"+league_id+"/info");
            result_param.put("sign_status",sign_status);
            result_param.put("showButton",showButton);
            return ControllerReturnBase.successResule(result_param);
        }catch (Exception e){
            LoggerUtil.outError(LeagueServiceImpl.class,"获取赛事详情时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }
}
