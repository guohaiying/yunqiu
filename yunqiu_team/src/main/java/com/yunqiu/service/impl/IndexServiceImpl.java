package com.yunqiu.service.impl;

import com.yunqiu.dao.IndexMapper;
import com.yunqiu.model.Dynamic;
import com.yunqiu.service.IndexService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 11366 on 2017/1/24.
 */

@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private IndexMapper indexMapper;

    /**
     * 首页获取球队列表
     * @param userId
     * @return
     */
    @Override
    public Map<String, Object> indexTeamList(String userId) {
        try {
            //返回数据结果集
            Map<String,Object> param_indexTeamList = new HashMap<>();
            //判断用户id是否存在，如果未null，表示未登录，返回缺省页面
            List<Map<String,Object>> db_teamList=null;
            if (Utils.isNull(userId)) {
                db_teamList = indexMapper.recommendTeamList(5);
                param_indexTeamList.put("type",1);
                param_indexTeamList.put("info",db_teamList);
                return ControllerReturnBase.successResule(param_indexTeamList);
            }
            //根据用户id，获取到首页球队列表
            db_teamList = indexMapper.indexTeamList(userId);
            //如果不存在数据，则需要返回缺省页面数据
            if (db_teamList.size() == 0){
                db_teamList = indexMapper.recommendTeamList(5);
                param_indexTeamList.put("type",1);
            }else {
                param_indexTeamList.put("type",2);
            }
            param_indexTeamList.put("info",db_teamList);
            return ControllerReturnBase.successResule(param_indexTeamList);
        }catch (Exception e){
            LoggerUtil.outError(IndexServiceImpl.class,"获取首页球队列表时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 获取首页球队详细信息
     * @param teamId
     * @return
     */
    @Override
    public Map<String, Object> indexTeamInfo(String teamId,String userId) {
        try {
            //返回数据结果集
            Map<String,Object> param_indexTeamInfo = new HashMap<>();
            //查询球队最近结束的比赛信息
            Map<String,Object> end_gameInfo_db = indexMapper.gameInfo_desc_index(teamId);
            if (end_gameInfo_db != null){
                //有比赛信息，解析比赛信息
                Map<String,Object> end_gameInfo_param = new HashMap<>();
                end_gameInfo_param.put("game_time",end_gameInfo_db.get("game_time"));//比赛时间
                end_gameInfo_param.put("game_name",end_gameInfo_db.get("game_name"));//比赛名称
                end_gameInfo_param.put("classify",end_gameInfo_db.get("classify"));//比赛类型
                end_gameInfo_param.put("score_teamA",end_gameInfo_db.get("score_teamA"));//A队得分
                end_gameInfo_param.put("score_teamB",end_gameInfo_db.get("score_teamB"));//B队得分
                end_gameInfo_param.put("game_id",end_gameInfo_db.get("game_id"));//比赛id
                end_gameInfo_param.put("isVideo",end_gameInfo_db.get("isVideo"));
                //获取主队球队信息
                Map<String,Object> teamInfo_db = indexMapper.selectTeamInfo((String) end_gameInfo_db.get("entry_teamA"));
                end_gameInfo_param.put("teamA_name",teamInfo_db.get("team_name"));
                end_gameInfo_param.put("teamA_badge",teamInfo_db.get("badge"));
                //获取客队球队信息
                if ((int)end_gameInfo_db.get("classify") != 3){
                    teamInfo_db = indexMapper.selectTeamInfo((String) end_gameInfo_db.get("entry_teamB"));
                    end_gameInfo_param.put("teamB_name",teamInfo_db.get("team_name"));
                    end_gameInfo_param.put("teamB_badge",teamInfo_db.get("badge"));
                }
                //存储到集果集
                param_indexTeamInfo.put("end_game",end_gameInfo_param);
            }

            //查询最近要开始得比赛
            Map<String,Object> start_gameInfo_db = indexMapper.gameInfo_asc_index(teamId);
            if (start_gameInfo_db!=null){
                Map<String,Object> start_gameInfo_param = new HashMap<>();
                start_gameInfo_param.put("game_time",start_gameInfo_db.get("game_time"));//比赛时间
                start_gameInfo_param.put("game_name",start_gameInfo_db.get("game_name"));//比赛名称
                start_gameInfo_param.put("classify",start_gameInfo_db.get("classify"));//比赛类型
                start_gameInfo_param.put("game_site",start_gameInfo_db.get("game_site"));//比赛场地
                start_gameInfo_param.put("game_id",start_gameInfo_db.get("game_id"));//比赛id
                //获取与该球队是对手的球队信息
                if (!teamId.equals(start_gameInfo_db.get("entry_teamA")) || (int)start_gameInfo_db.get("classify") == 3){
                    Map<String,Object> teamInfo_db = indexMapper.selectTeamInfo((String) start_gameInfo_db.get("entry_teamA"));
                    start_gameInfo_param.put("name",teamInfo_db.get("team_name"));
                    start_gameInfo_param.put("badge",teamInfo_db.get("badge"));
                    start_gameInfo_param.put("game_type",1);
                    start_gameInfo_param.put("uniform_team",start_gameInfo_db.get("uniform_teamA"));
                }else {
                    Map<String,Object> teamInfo_db = indexMapper.selectTeamInfo((String) start_gameInfo_db.get("entry_teamB"));
                    start_gameInfo_param.put("name",teamInfo_db.get("team_name"));
                    start_gameInfo_param.put("badge",teamInfo_db.get("badge"));
                    start_gameInfo_param.put("game_type",2);
                    start_gameInfo_param.put("uniform_team",start_gameInfo_db.get("uniform_teamB"));
                }
                //查询报名成员
                List<Map<String,String>> game_match = indexMapper.gameMatch((String) start_gameInfo_db.get("game_id"),teamId);
                start_gameInfo_param.put("match",game_match);
                //查询报名状态,macth不为null，表示已有报名信息
                Map<String,Object> match = indexMapper.selectMatchByUserIdAndEventIdAndTeamId(userId,(String) start_gameInfo_db.get("game_id"),teamId);
                if (match != null){
                    start_gameInfo_param.put("registration_status",match.get("status"));
                    //获取球队总人数
                    Map<String,Integer> team_match = indexMapper.selectTeamMatchNumber(teamId);
                    start_gameInfo_param.put("team_match",team_match.get("team_match"));
                    //获取比赛参与人数
                    Map<String,Integer> event_match = indexMapper.selectMatchNumber((String) start_gameInfo_db.get("game_id"),teamId);
                    start_gameInfo_param.put("event_match",event_match.get("event_match"));
                }else {
                    start_gameInfo_param.put("registration_status",0);
                }
                //存储到结果集
                param_indexTeamInfo.put("start_game",start_gameInfo_param);
            }

            //获取推荐球队
            List<Map<String,Object>> db_teamList = indexMapper.recommendTeamList(4);
            param_indexTeamInfo.put("recommend_team",db_teamList);
            //获取动态
            List<Dynamic> dynamicList = indexMapper.selectDynamic(userId);
            param_indexTeamInfo.put("dynamic",dynamicList);
            for (Dynamic dynamic:dynamicList){
                indexMapper.updateDynamic(dynamic.getDynamic_id());
            }
            return ControllerReturnBase.successResule(param_indexTeamInfo);
        }catch (Exception e){
            LoggerUtil.outError(IndexServiceImpl.class,"获取首页球队详细数据时报错",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 刷新首页球队推荐列表
     * @param number
     * @return
     */
    @Override
    public Map<String, Object> indexRefreshRecommendedTeam(int number) {
        try {
            List<Map<String,Object>> db_teamList = indexMapper.recommendTeamList(number);
            return ControllerReturnBase.successResule(db_teamList);
        }catch (Exception e){
            LoggerUtil.outError(IndexServiceImpl.class,"刷新首页推荐球队时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 获取推荐的球员
     * @param number
     * @return
     */
    @Override
    public Map<String, Object> getRecommendationUser(int number) {
        try {
            List<Map<String,Object>> db_userList = indexMapper.recommendationUser(number);
            return ControllerReturnBase.successResule(db_userList);
        }catch (Exception e){
            LoggerUtil.outError(IndexServiceImpl.class,"获取推荐的球员时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 获取消息列表
     * @param user_id
     * @return
     */
    @Override
    public Map<String, Object> getMessageList(String user_id) {
        try {
            List<Map<String,Object>> resultList = new ArrayList<>();
            Map<String,Object> result = null;
            Map<String,Object> message = null;
            //球队消息
            List<Map<String,Object>> teamList = indexMapper.indexTeamList(user_id);
            for (int index=0;index<teamList.size();index++){
                Map<String,Object> team = teamList.get(index);
                result = new HashMap<>();
                result.put("message_name",team.get("team_name")+"球队消息");
                result.put("message_type",1);
                result.put("type_id",team.get("team_id"));
                message = indexMapper.getMessageNew(user_id,1,(String) team.get("team_id"));
                if (message != null){
                    result.put("message_title",message.get("message_title"));
                    result.put("push_time",message.get("push_time"));
                }else{
                    result.put("message_title","");
                    result.put("push_time","");
                }
                resultList.add(result);
            }
            //赛事消息
            message = indexMapper.getMessageNew(user_id,2,"");
            result = new HashMap<>();
            result.put("message_name","赛事消息");
            result.put("message_type",2);
            result.put("type_id","");
            if (message != null){
                result.put("message_title",message.get("message_title"));
                result.put("push_time",message.get("push_time"));
            }else{
                result.put("message_title","");
                result.put("push_time","");
            }
            resultList.add(result);
            //个人消息
            message = indexMapper.getMessageNew(user_id,3,user_id);
            result = new HashMap<>();
            result.put("message_name","个人消息");
            result.put("message_type",3);
            result.put("type_id",user_id);
            if (message != null){
                result.put("message_title",message.get("message_title"));
                result.put("push_time",message.get("push_time"));
            }else{
                result.put("message_title","");
                result.put("push_time","");
            }
            resultList.add(result);
            //系统消息
            message = indexMapper.getMessageNew(user_id,4,"");
            result = new HashMap<>();
            result.put("message_name","系统消息");
            result.put("message_type",4);
            result.put("type_id","");
            if (message != null){
                result.put("message_title",message.get("message_title"));
                result.put("push_time",message.get("push_time"));
            }else{
                result.put("message_title","");
                result.put("push_time","");
            }
            resultList.add(result);
            return ControllerReturnBase.successResule(resultList);
        }catch (Exception e){
            LoggerUtil.outError(IndexServiceImpl.class,"获取消息列表时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 获取消息
     * @param user_id
     * @param message_type
     * @param type_id
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Map<String, Object> getMessage(String user_id, int message_type, String type_id, int pageNum, int pageSize) {
        try {
            //获取总条数
            int total = indexMapper.getMessageTotal(user_id,message_type,type_id);
            //计算总页数
            int pageTotal = total%pageSize == 0 ?total/pageSize:(total/pageSize)+1;
            //计算起始行索引
            int start_now = (pageNum-1)*pageSize;
            List<Map<String,Object>> messageList = indexMapper.getMessage(user_id,message_type,type_id,start_now,pageSize);

            Map<String,Object> result = new HashMap<>();
            result.put("pageTotal",pageTotal);
            result.put("pageNum",pageNum);
            result.put("pageSize",pageSize);
            result.put("messageList",messageList);
            return ControllerReturnBase.successResule(result);
        }catch (Exception e){
            LoggerUtil.outError(IndexServiceImpl.class,"获取消息时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 修改消息阅读状态
     * @param user_id
     * @param message_type
     * @param type_id
     * @return
     */
    @Override
    public Map<String, Object> updateMessageStatus(String user_id,String message_id, int message_type, String type_id) {
        try {
            //如果message_id为null，表示为一键阅读功能，如果不为null，表示查看了单条消息
            if (Utils.isNull(message_id)){
                indexMapper.updateMessageByType(user_id,message_type,type_id);
            }else {
                indexMapper.updateMessageById(message_id);
            }
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(IndexServiceImpl.class,"修改消息阅读状态时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }
}
