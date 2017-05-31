package com.yunqiu.service.impl;

import com.yunqiu.dao.EventMapper;
import com.yunqiu.dao.MatchMapper;
import com.yunqiu.dao.TeamInfoMapper;
import com.yunqiu.dao.TrainMapper;
import com.yunqiu.model.Event;
import com.yunqiu.model.Match;
import com.yunqiu.model.Train;
import com.yunqiu.service.EventService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.DateUtil;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import com.yunqiu.view.EventListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by 11366 on 2017/1/23.
 */

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private TeamInfoMapper teamInfoMapper;
    @Autowired
    private EventMapper eventMapper;
    @Autowired
    private MatchMapper matchMapper;
    @Autowired
    private TrainMapper trainMapper;

    /**
     * 创建比赛
     * @param event
     * @param user_id
     * @return
     */
    @Override
    public Map<String, Object> createGame(Event event, String user_id) {
        try {
            //验证参数
            // 发起球队的id
            if (Utils.isNull(event.getSponsor_team())){
                return ControllerReturnBase.errorResule(1501,"缺少发起球队的id");
            }
            //对手球队
            if (Utils.isNull(event.getRival_team())){
                return ControllerReturnBase.errorResule(1501,"缺少对手球队id");
            }
            //比赛时间
            if (event.getGame_time() == null){
                return ControllerReturnBase.errorResule(1501,"缺少比赛开始时间");
            }
            //持续时间
            if (event.getContinue_time() <= 0.0){
                return ControllerReturnBase.errorResule(1501,"缺少比赛持续时间");
            }
            //赛制
            if (event.getGame_system() <= 0){
                return ControllerReturnBase.errorResule(1501,"缺少赛制");
            }
            //主队队服
            if (Utils.isNull(event.getSponsor_uniform())){
                return ControllerReturnBase.errorResule(1501,"缺少主队队服");
            }
            //报名截止时间
            if (event.getApply_end_time() ==null){
                return ControllerReturnBase.errorResule(1501,"缺少报名截止时间");
            }
            //验证球队、创建者身份
            Map<String,Object> teamMember = teamInfoMapper.selectTeamMemberByTeamIdAndUserId(event.getSponsor_team(),user_id);
            if (teamMember == null){
                return ControllerReturnBase.errorResule(1502,"创建者不是发起球队的成员，无权发起比赛");
            }
            //验证对手球队是否存在
            Map<String,Object> rival_team = teamInfoMapper.selectTeamInfo(event.getRival_team());
            if (rival_team == null){
                return ControllerReturnBase.errorResule(1502,"对手球队不存在");
            }
            //根据权限，补充球队状态信息
            if ((int)teamMember.get("jurisdiction") == 0){
                //普通队员
                event.setAudit_status(1);
                event.setEvent_status(1);
            }else {
                //管理员
                event.setAudit_status(2);
                event.setEvent_status(2);
                event.setAudit_reason("管理员创建，无须审核");
            }
            //创建
            event.setEvent_id(Utils.getUUID());
            event.setUser_id(user_id);
            event.setScore_status(1);
            eventMapper.insertEvent(event);
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(EventServiceImpl.class,"创建比赛时（sevice）发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 查询比赛列表
     * @param user_id
     * @return
     */
    @Override
    public Map<String, Object> selectGameList(String user_id,int pageNum,int pageSize,int status,String time,String team_id) {
        try {
            //获取到所在的所有球队比赛信息
            List<Event> eventList = eventMapper.selectEventByUser(user_id);
            //最终返回的结果集
            List<Map<String,Object>> eventList_view = new ArrayList<>();
            /* -------------根据比赛时间，把比赛信息按月分组 */
            //单条比赛数据
            Event event = null;
            //分组后的结果集
            Map<String,List<Event>> resultEvent = new HashMap<>();
            //解析比赛信息列表
            for (int i=0;i<eventList.size();i++){
                //获取到单条比赛信息
                event = eventList.get(i);
                if(resultEvent.containsKey(event.getGame_time())){
                    resultEvent.get(event.getGame_time()).add(event);
                }else{
                    List<Event> list = new ArrayList<>();
                    list.add(event);
                    resultEvent.put(DateUtil.DTDateToString(event.getGame_time(),"yyyy年MM月"),list);
                }
            }
            //解析分组，把比赛信息更换为可返回的信息
            EventListView eventListView = null;//返回的比赛信息
            for (String key:resultEvent.keySet()){
                //获取训练数据
                Date key_time = DateUtil.DTStringToDate(key,"yyyy年MM月");
                int year = Integer.parseInt(DateUtil.DTDateToString(key_time,"yyyy"));
                int month = Integer.parseInt(DateUtil.DTDateToString(key_time,"MM"));
                List<String> teamIdList = trainMapper.selectTeamId(user_id);
                List<Train> trainList = new ArrayList<>();
                for (int items=0;items<teamIdList.size();items++){
                    List<Train> trainList_param = trainMapper.selectTrainByTime(year,month,teamIdList.get(items));
                    trainList.addAll(trainList_param);
                }
                //循环key，根据key获取到每组的比赛信息
                List<Event> groupEvent = resultEvent.get(key);
                //解析后的分组结果集
                Map<String,Object> eventGroup_view = new HashMap<>();
                List<EventListView> list_view = new ArrayList<>();
                //循环解析该组比赛信息
                for (int n=0;n<groupEvent.size();n++){
                    //单条比赛信息
                    event = groupEvent.get(n);
                    /* 主队操作 */
                    //根据比赛信息中主队的球队id字段，验证当前球员的身份
                    Map<String,Object> sponsor_teamMember = teamInfoMapper.selectTeamMemberByTeamIdAndUserId(event.getSponsor_team(),user_id);
                    //该球员不是主队的成员（teamMember == null）则不处理
                    if (sponsor_teamMember != null){
                        //该球员为主队的成员，验证是否为管理员 (int)teamMember.get("jurisdiction")>0表示为管理员，否则为普通队员
                        //为管理员时，返回比赛信息中 包括 待审核、待应战的比赛
                        //为普通队员时，返回比赛信息中不包括 待审核、待应战的比赛
                        if ((int)sponsor_teamMember.get("jurisdiction") > 0){
                            //当前为管理员类型，实例化返回信息
                            eventListView = new EventListView();
                            //比赛直接返回信息
                            eventListView.setEvent_id(event.getEvent_id());//比赛id
                            eventListView.setGame_type(1);//比赛的所属 1：主队 2：客队
                            eventListView.setGame_time(event.getGame_time());//比赛时间
                            eventListView.setSite(event.getSite());//场地
                            eventListView.setEvent_status(event.getEvent_status());//比赛状态
                            eventListView.setScore_status(event.getScore_status());//比分录入状态
                            //根据主队的球队id,查询球队信息
                            Map<String,Object> sponsor_team = teamInfoMapper.selectTeamInfo(event.getSponsor_team());
                            eventListView.setSponsor_id((String) sponsor_team.get("team_id"));//主键
                            eventListView.setSponsor_name((String)sponsor_team.get("team_name"));//球队名称
                            eventListView.setSponsor_badge((String)sponsor_team.get("badge"));//球队队徽
                            eventListView.setSponsor_score(event.getSponsor_score());//主队比分
                            //根据客队的球队id，查询球队信息
                            Map<String,Object> rival_team = teamInfoMapper.selectTeamInfo(event.getRival_team());
                            eventListView.setRival_id((String) rival_team.get("team_id"));
                            eventListView.setRival_name((String)rival_team.get("team_name"));
                            eventListView.setRival_badge((String)rival_team.get("badge"));
                            eventListView.setRival_score(event.getRival_score());//客队得分
                            //按钮
                            eventListView.setButton(1);
                            //查询报名状态,macth不为null，表示已有报名信息
                            Match match = matchMapper.selectMatchByUserIdAndEventIdAndTeamId(user_id,event.getEvent_id());
                            if (match != null){
                                eventListView.setRegistration_status(match.getStatus());
                                //获取球队总人数
                                int team_match = matchMapper.selectTeamMatchNumber(event.getSponsor_team());
                                eventListView.setTeam_match(team_match);
                                //获取比赛参与人数
                                int event_match = matchMapper.selectMatchNumber(event.getEvent_id(),event.getSponsor_team());
                                eventListView.setEvent_match(event_match);
                            }else {
                                eventListView.setRegistration_status(0);
                            }
                            //存储
                            list_view.add(eventListView);
                        }else {
                            //当前为普通队员类型，实例化返回信息
                            eventListView = new EventListView();
                            //判断比赛状态，待应战、待审核状态不处理
                            if (event.getEvent_status() != 1 && event.getEvent_status() != 2){
                                //比赛直接返回信息
                                eventListView.setEvent_id(event.getEvent_id());//比赛id
                                eventListView.setGame_type(1);//比赛的所属 1：主队 2：客队
                                eventListView.setGame_time(event.getGame_time());//比赛时间
                                eventListView.setSite(event.getSite());//场地
                                eventListView.setEvent_status(event.getEvent_status());//比赛状态
                                eventListView.setScore_status(event.getScore_status());//比分录入状态
                                //根据主队的球队id,查询球队信息
                                Map<String,Object> sponsor_team = teamInfoMapper.selectTeamInfo(event.getSponsor_team());
                                eventListView.setSponsor_id((String) sponsor_team.get("team_id"));//主键
                                eventListView.setSponsor_name((String)sponsor_team.get("team_name"));;//球队名称
                                eventListView.setSponsor_badge((String)sponsor_team.get("badge"));//球队队徽
                                //根据客队的球队id，查询球队信息
                                Map<String,Object> rival_team = teamInfoMapper.selectTeamInfo(event.getRival_team());
                                eventListView.setRival_id((String) rival_team.get("team_id"));
                                eventListView.setRival_name((String)rival_team.get("team_name"));
                                eventListView.setRival_badge((String)rival_team.get("badge"));
                                //按钮
                                eventListView.setButton(0);
                                //查询报名状态,macth不为null，表示已有报名信息
                                Match match = matchMapper.selectMatchByUserIdAndEventIdAndTeamId(user_id,event.getEvent_id());
                                if (match != null){
                                    eventListView.setRegistration_status(match.getStatus());
                                    //获取球队总人数
                                    String s_teamId = event.getSponsor_team();
                                    int team_match = matchMapper.selectTeamMatchNumber(s_teamId);
                                    eventListView.setTeam_match(team_match);
                                    //获取球队报名参加比赛人数
                                    int event_match = matchMapper.selectMatchNumber(event.getEvent_id(),event.getSponsor_team());
                                    eventListView.setEvent_match(event_match);
                                }else {
                                    eventListView.setRegistration_status(0);
                                }
                                //存储
                                list_view.add(eventListView);
                            }
                        }
                    }
                    /*客队操作*/
                    //根据比赛信息中客队的球队id字段，验证当前球员的身份
                    Map<String,Object> rival_teamMember = teamInfoMapper.selectTeamMemberByTeamIdAndUserId(event.getRival_team(),user_id);
                    //该球员不是客队的成员（teamMember == null）则不处理
                    if (rival_teamMember != null){
                        //该球员为客队的成员，验证是否为管理员 (int)teamMember.get("jurisdiction")>0表示为管理员，否则为普通队员
                        //为管理员时，返回比赛信息中 包括 待审核、待应战的比赛
                        //为普通队员时，返回比赛信息中不包括 待审核、待应战的比赛
                        if ((int)rival_teamMember.get("jurisdiction") > 0){
                            //当前为管理员类型，实例化返回信息
                            eventListView = new EventListView();
                            //比赛直接返回信息
                            eventListView.setEvent_id(event.getEvent_id());//比赛id
                            eventListView.setGame_type(2);//比赛的所属 1：主队 2：客队
                            eventListView.setGame_time(event.getGame_time());//比赛时间
                            eventListView.setSite(event.getSite());//场地
                            eventListView.setEvent_status(event.getEvent_status());//比赛状态
                            eventListView.setScore_status(event.getScore_status());//比分录入状态
                            //根据主队的球队id,查询球队信息
                            Map<String,Object> sponsor_team = teamInfoMapper.selectTeamInfo(event.getSponsor_team());
                            eventListView.setSponsor_id((String) sponsor_team.get("team_id"));//主键
                            eventListView.setSponsor_name((String)sponsor_team.get("team_name"));;//球队名称
                            eventListView.setSponsor_badge((String)sponsor_team.get("badge"));//球队队徽
                            //根据客队的球队id，查询球队信息
                            Map<String,Object> rival_team = teamInfoMapper.selectTeamInfo(event.getRival_team());
                            eventListView.setRival_id((String) rival_team.get("team_id"));
                            eventListView.setRival_name((String)rival_team.get("team_name"));
                            eventListView.setRival_badge((String)rival_team.get("badge"));
                            //按钮
                            eventListView.setButton(2);
                            //查询报名状态,macth不为null，表示已有报名信息
                            Match match = matchMapper.selectMatchByUserIdAndEventIdAndTeamId(user_id,event.getEvent_id());
                            if (match != null){
                                eventListView.setRegistration_status(match.getStatus());
                                //获取球队总人数
                                int team_match = matchMapper.selectTeamMatchNumber(event.getRival_team());
                                eventListView.setTeam_match(team_match);
                                //获取比赛参与人数
                                int event_match = matchMapper.selectMatchNumber(event.getEvent_id(),event.getRival_team());
                                eventListView.setEvent_match(event_match);
                            }else {
                                eventListView.setRegistration_status(0);
                            }
                            //存储
                            list_view.add(eventListView);
                        }else {
                            //当前为普通队员类型，实例化返回信息
                            eventListView = new EventListView();
                            //判断比赛状态，待应战、待审核状态不处理
                            if (event.getEvent_status() != 1 && event.getEvent_status() != 2){
                                //比赛直接返回信息
                                eventListView.setEvent_id(event.getEvent_id());//比赛id
                                eventListView.setGame_type(2);//比赛的所属 1：主队 2：客队
                                eventListView.setGame_time(event.getGame_time());//比赛时间
                                eventListView.setSite(event.getSite());//场地
                                eventListView.setEvent_status(event.getEvent_status());//比赛状态
                                eventListView.setScore_status(event.getScore_status());//比分录入状态
                                //根据主队的球队id,查询球队信息
                                Map<String,Object> sponsor_team = teamInfoMapper.selectTeamInfo(event.getSponsor_team());
                                eventListView.setSponsor_id((String) sponsor_team.get("team_id"));//主键
                                eventListView.setSponsor_name((String)sponsor_team.get("team_name"));;//球队名称
                                eventListView.setSponsor_badge((String)sponsor_team.get("badge"));//球队队徽
                                //根据客队的球队id，查询球队信息
                                Map<String,Object> rival_team = teamInfoMapper.selectTeamInfo(event.getRival_team());
                                eventListView.setRival_id((String) rival_team.get("team_id"));
                                eventListView.setRival_name((String)rival_team.get("team_name"));
                                eventListView.setRival_badge((String)rival_team.get("badge"));
                                //按钮
                                eventListView.setButton(0);
                                //查询报名状态,macth不为null，表示已有报名信息
                                Match match = matchMapper.selectMatchByUserIdAndEventIdAndTeamId(user_id,event.getEvent_id());
                                if (match != null){
                                    eventListView.setRegistration_status(match.getStatus());
                                    //获取球队总人数
                                    String r_teamId = event.getRival_team();
                                    int team_match = matchMapper.selectTeamMatchNumber(r_teamId);
                                    eventListView.setTeam_match(team_match);
                                    //获取比赛参与人数
                                    int event_match = matchMapper.selectMatchNumber(event.getEvent_id(),event.getRival_team());
                                    eventListView.setEvent_match(event_match);
                                }else {
                                    eventListView.setRegistration_status(0);
                                }
                                //存储
                                list_view.add(eventListView);
                            }
                        }
                    }
                }
                eventGroup_view.put("group",key);
                eventGroup_view.put("event",list_view);
                eventGroup_view.put("train",trainList);
                eventList_view.add(eventGroup_view);
            }
            //返回数据
            Map<String,Object> result_param = new  HashMap<>();
            result_param.put("totalPage",0);
            result_param.put("pageSize",pageSize);
            result_param.put("pageNum",pageNum);
            result_param.put("list",eventList_view);
            return ControllerReturnBase.successResule(result_param);//原本是直接返回eventList_view
        }catch (Exception e){
            LoggerUtil.outError(EventServiceImpl.class,"查询比赛列表（sevice）发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 球队管理员审核比赛
     * @param event_id
     * @param audit_status
     * @param audit_reason
     * @param user_id
     * @return
     */
    @Override
    public Map<String, Object> auditEvent(String event_id, int audit_status, String audit_reason, String user_id) {
        try {
            //验证审核状态参数
            if (audit_status != 1 && audit_status != 2){
                return ControllerReturnBase.errorResule(1501,"审核状态错误");
            }
            //根据比赛id获取比赛信息
            Event event = eventMapper.selectEventById(event_id);
            if (event == null){
                return ControllerReturnBase.errorResule(1502,"比赛信息不存在");
            }
            if (event.getEvent_status() != 1){
                return ControllerReturnBase.errorResule(1502,"比赛信息不是待审核状态，无须审核");
            }
            //验证用户是否有权限
            Map<String,Object> teamMember = teamInfoMapper.selectTeamMemberByTeamIdAndUserId(event.getSponsor_team(),user_id);
            if (teamMember == null){
                return ControllerReturnBase.errorResule(1502,"不是球队成员，无审核权限");
            }
            if ((int)teamMember.get("jurisdiction") != 1 && (int)teamMember.get("jurisdiction") != 2){
                return ControllerReturnBase.errorResule(1502,"无权限审核");
            }
            //修改比赛状态
            if (audit_status == 1){
                //同意
                eventMapper.updateEventStatusById(event_id,2,2,audit_reason,event.getRefuse_reason(),event.getCancel_reason());
            }else {
                //拒绝
                eventMapper.updateEventStatusById(event_id,3,9,audit_reason,event.getRefuse_reason(),event.getCancel_reason());
            }
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(EventServiceImpl.class,"审核比赛（sevice）发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 比赛应战
     * @param event_id
     * @param battle_status
     * @param refuse_reason
     * @param user_id
     * @return
     */
    @Override
    public Map<String, Object> battleEvent(String event_id, int battle_status, String refuse_reason, String user_id) {
        try {
            //验证审核状态参数
            if (battle_status != 1 && battle_status != 2){
                return ControllerReturnBase.errorResule(1501,"迎战状态错误");
            }
            //根据比赛id获取比赛信息
            Event event = eventMapper.selectEventById(event_id);
            if (event == null){
                return ControllerReturnBase.errorResule(1502,"比赛信息不存在");
            }
            if (event.getEvent_status() != 2){
                return ControllerReturnBase.errorResule(1502,"比赛信息不是待迎战状态");
            }
            //验证用户是否有权限
            Map<String,Object> teamMember = teamInfoMapper.selectTeamMemberByTeamIdAndUserId(event.getRival_team(),user_id);
            if (teamMember == null){
                return ControllerReturnBase.errorResule(1502,"不是球队成员，无审核权限");
            }
            if ((int)teamMember.get("jurisdiction") != 1 && (int)teamMember.get("jurisdiction") != 2){
                return ControllerReturnBase.errorResule(1502,"无权限审核");
            }
            //修改比赛状态
            if (battle_status == 1){
                //同意
                eventMapper.updateEventStatusById(event_id,2,3,event.getAudit_reason(),refuse_reason,event.getCancel_reason());
            }else {
                //拒绝
                eventMapper.updateEventStatusById(event_id,2,8,event.getAudit_reason(),refuse_reason,event.getCancel_reason());
            }
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(EventServiceImpl.class,"比赛迎战（sevice）发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 取消比赛
     * @param event_id
     * @param cancel_reason
     * @param user_id
     * @return
     */
    @Override
    public Map<String, Object> cancelEvent(String event_id, String cancel_reason, String user_id) {
        try {
            //根据比赛id获取比赛信息
            Event event = eventMapper.selectEventById(event_id);
            if (event == null){
                return ControllerReturnBase.errorResule(1502,"比赛信息不存在");
            }
            if (event.getEvent_status() !=1 && event.getEvent_status() != 2 && event.getEvent_status() !=3 && event.getEvent_status()!=4){
                return ControllerReturnBase.errorResule(1502,"该比赛为不可取消状态");
            }
            //验证用户是否有权限
            Map<String,Object> teamMember = teamInfoMapper.selectTeamMemberByTeamIdAndUserId(event.getSponsor_team(),user_id);
            if (teamMember == null){
                return ControllerReturnBase.errorResule(1502,"不是球队成员，无审核权限");
            }
            if ((int)teamMember.get("jurisdiction") != 1 && (int)teamMember.get("jurisdiction") != 2){
                return ControllerReturnBase.errorResule(1502,"无权限审核");
            }
            //修改比赛状态
             eventMapper.updateEventStatusById(event_id,2,7,event.getAudit_reason(),event.getRefuse_reason(),cancel_reason);

            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(EventServiceImpl.class,"取消比赛（sevice）发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 获取比赛详情
     * @param event_id
     * @param user_id
     * @return
     */
    @Override
    public Map<String, Object> selectEventInfo(String event_id, String user_id, int type) {
        try {
            //返回的结果集
            Map<String,Object> result_eventInfo = new HashMap<>();
            //根据比赛id获取比赛信息
            Event event = eventMapper.selectEventById(event_id);
            if (event == null){
                return ControllerReturnBase.errorResule(1502,"比赛信息不存在");
            }
            //解析比赛信息
            Map<String,Object> param_basis = new HashMap<>();
            param_basis.put("event_id",event.getEvent_id());//比赛id
            param_basis.put("game_time",event.getGame_time());//比赛时间
            param_basis.put("site",event.getSite());//场地
            param_basis.put("game_system",event.getGame_system());//赛制
            param_basis.put("event_status",event.getEvent_status());//比赛状态
            param_basis.put("event_name",event.getEvent_name());//比赛名称
            param_basis.put("event_cost",event.getEvent_cost());//比赛费用
            param_basis.put("value_added",event.getValue_added());//选购视频数据服务
            //根据主队的球队id,查询球队信息
            Map<String,Object> sponsor_team = teamInfoMapper.selectTeamInfo(event.getSponsor_team());
            param_basis.put("sponsor_id",sponsor_team.get("team_id"));//主键
            param_basis.put("sponsor_name",sponsor_team.get("team_name"));//球队名称
            param_basis.put("sponsor_badge",sponsor_team.get("badge"));//球队队徽
            param_basis.put("sponsor_score",event.getSponsor_score());//主队得分
            //根据客队的球队id，查询球队信息
            Map<String,Object> rival_team = teamInfoMapper.selectTeamInfo(event.getRival_team());
            param_basis.put("rival_id",rival_team.get("team_id"));
            param_basis.put("rival_name",rival_team.get("team_name"));
            param_basis.put("rival_badge",rival_team.get("badge"));
            param_basis.put("rival_score",event.getRival_score());//客队得分
            //主队
            param_basis.put("sponsor_uniform",event.getSponsor_uniform());//主队队服
            param_basis.put("rival_uniform",event.getRival_uniform());//客队队服
            //判断当前是否显示审核按钮或迎战按钮
            Map<String,Object> teamMember = null;
            //当赛事不为审核、待迎战 2个状态，不显示任何按钮，并且返回报名信息
            if (event.getEvent_status() != 1 && event.getEvent_status() != 2 ){
                param_basis.put("button",0);
                //报名参赛人员
                List<Map<String,Object>> param_match = null;
                if (type == 1){
                    param_match = matchMapper.selectAllMatch(event_id,event.getSponsor_team());
                }else {
                    param_match = matchMapper.selectAllMatch(event_id,event.getRival_team());
                }
                //存储到结果集
                result_eventInfo.put("match",param_match);
            }else{
                if (type == 1){
                    //当前用户归属主队，主队为审核
                    if (event.getEvent_status() == 1){
                        //验证用户是否有权限审核
                        teamMember = teamInfoMapper.selectTeamMemberByTeamIdAndUserId(event.getSponsor_team(),user_id);
                        if ((int)teamMember.get("jurisdiction") == 0){
                            //普通队员，无审核权限，什么按钮都不显示
                            param_basis.put("button",0);
                        }else{
                            //管理员，显示审核按钮
                            param_basis.put("button",1);
                        }
                    }else {
                        //比赛不为审核状态，什么按钮都不显示
                        param_basis.put("button",0);
                    }
                }else {
                    //当前用户归属客队，客队为迎战
                    if (event.getEvent_status() == 2){
                        //验证用户是否有权限审核
                        teamMember = teamInfoMapper.selectTeamMemberByTeamIdAndUserId(event.getRival_team(),user_id);
                        if ((int)teamMember.get("jurisdiction") == 0){
                            //普通队员，无迎战权限，什么按钮都不显示
                            param_basis.put("button",0);
                        }else{
                            //管理员，显示迎战按钮
                            param_basis.put("button",2);
                        }
                    }else {
                        //比赛不为迎战状态，什么按钮都不显示
                        param_basis.put("button",0);
                    }
                }
            }
            //查询报名状态,macth不为null，表示已有报名信息
            Match match = matchMapper.selectMatchByUserIdAndEventIdAndTeamId(user_id,event_id);
            if (match != null){
                param_basis.put("registration_status",match.getStatus());
                if (event.getSponsor_team().equals(match.getTeam_id())){
                    param_basis.put("registration_team",1);
                }else {
                    param_basis.put("registration_team",2);
                }
            }else {
                param_basis.put("registration_status",0);//未报名、请假、标记待定
            }
            param_basis.put("game_type",type);
            //存储到结果集
            result_eventInfo.put("basis",param_basis);

            return ControllerReturnBase.successResule(result_eventInfo);
        }catch (Exception e){
            LoggerUtil.outError(EventServiceImpl.class,"获取比赛详情（sevice）发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 获取比赛基础信息
     * @param event_id
     * @return
     */
    @Override
    public Map<String, Object> selectEventBasis(String event_id) {
        try {
            Event event = eventMapper.selectEventById(event_id);
            return ControllerReturnBase.successResule(event);
        }catch (Exception e){
            LoggerUtil.outError(EventServiceImpl.class,"获取比赛基础信息（sevice）发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 获取查询比赛列表时的筛选条件
     * @param user_id
     * @return
     */
    @Override
    public Map<String, Object> eventFiltrateConditions(String user_id) {
        try {
            //返回的结果集定义
            Map<String,Object> result_param = new HashMap<>();
            /*----------------状态筛选条件--------------------*/
            List<Map<String,Object>> status_param = new ArrayList<>();//状态结果集
            //所有状态名称，根据顺序列出
            String[] event_status_name = {"待审核","待迎战 ","报名中","报名结束","正在进行","已结束","比赛取消","拒绝迎战","审核拒绝"};
            //循环状态名
            for (int item=0;item<event_status_name.length;item++){
                Map<String,Object> event_status = new HashMap<>();
                String status_name = event_status_name[item];
                event_status.put("status_name",status_name);
                event_status.put("status_value",item+1);
                status_param.add(event_status);
            }
            result_param.put("status",status_param);
            /*----------------时间筛选条件--------------------*/
            List<Map<String,Object>> time_param = new ArrayList<>();//时间结果集
            //查询用户所有比赛
            List<Event> eventList = eventMapper.selectEventByUser(user_id);
            //根据比赛时间，把比赛信息按月分组
            //单条比赛数据
            Event event = null;
            //分组后的结果集
            Map<String,String> group_Event = new HashMap<>();
            //解析比赛信息列表
            for (int i=0;i<eventList.size();i++){
                //获取到单条比赛信息
                event = eventList.get(i);
                if(!group_Event.containsKey(event.getGame_time())){
                    group_Event.put(DateUtil.DTDateToString(event.getGame_time(),"yyyy年MM月"),DateUtil.DTDateToString(event.getGame_time(),"yyyy-MM"));
                }
            }
            //解析出时间组
            for (String key:group_Event.keySet()){
                Map<String,Object> time = new HashMap<>();
                time.put("time_name",key);
                String time_value = group_Event.get(key);
                time.put("time_value",time_value);
                time_param.add(time);
            }
            result_param.put("time",time_param);
            /*----------------球队筛选条件--------------------*/
            List<Map<String,Object>> team_param = teamInfoMapper.selectTeamList(user_id);
            result_param.put("team",team_param);
            return ControllerReturnBase.successResule(result_param);
        }catch (Exception e){
            LoggerUtil.outError(EventServiceImpl.class,"获取查询比赛列表时的筛选条件（service）发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }


   /* public static void main(String[] args){
        List<Event> eventList = new ArrayList<>();
        Event dataEvent = new Event();
        dataEvent.setGame_time(DateUtil.DTStringToDate("2017-01-11 12:30:00","yyyy-MM-dd HH:mm:ss"));
        eventList.add(dataEvent);
        dataEvent = new Event();
        dataEvent.setGame_time(DateUtil.DTStringToDate("2018-01-11 12:30:00","yyyy-MM-dd HH:mm:ss"));
        eventList.add(dataEvent);
        dataEvent = new Event();
        dataEvent.setGame_time(DateUtil.DTStringToDate("2017-07-11 12:30:00","yyyy-MM-dd HH:mm:ss"));
        eventList.add(dataEvent);

        Event event = null;//单条数据
        Map<String,List<Event>> resultEvent = new HashMap<>();//分组后的结果集
        for (int i=0;i<eventList.size();i++){
            event = eventList.get(i);
            if(resultEvent.containsKey(event.getGame_time())){
                resultEvent.get(event.getGame_time()).add(event);
            }else{
                List<Event> list = new ArrayList<>();
                list.add(event);
                resultEvent.put(DateUtil.DTDateToString(event.getGame_time(),"yyyy年MM月"),list);
            }
        }
        System.out.println(resultEvent.size());
        for (String key:resultEvent.keySet()){
            System.out.println(key);
        }
    }*/
}
