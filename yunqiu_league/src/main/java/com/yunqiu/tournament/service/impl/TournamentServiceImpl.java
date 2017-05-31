package com.yunqiu.tournament.service.impl;

import com.yunqiu.general.dao.TeamMapper;
import com.yunqiu.tournament.dao.GameCollectionMapper;
import com.yunqiu.tournament.dao.GameMemberMapper;
import com.yunqiu.tournament.dao.TournamentMapper;
import com.yunqiu.tournament.model.GameCollection;
import com.yunqiu.tournament.model.GameMember;
import com.yunqiu.tournament.model.Tournament;
import com.yunqiu.tournament.service.TournamentService;
import com.yunqiu.tournament.timedtask.GameTimedTask;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.DateUtil;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 比赛数据处理
 */

@Service
public class TournamentServiceImpl implements TournamentService {
    @Autowired
    private TournamentMapper tournamentMapper;
    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private GameTimedTask gameTimedTask;
    @Autowired
    private GameMemberMapper gameMemberMapper;
    @Autowired
    private GameCollectionMapper gameCollectionMapper;

    /**
     * 创建比赛
     * @param tournament
     * @param userId
     * @return
     */
    @Override
    public Map<String, Object> createTournament(Tournament tournament, String userId) {
        try {
            //#############################设置必须参数###############################
            tournament.setGame_id(Utils.getUUID());
            tournament.setUser_id(userId);
            //###########################验证必填参数##############################
            if (Utils.isNull(tournament.getEntry_teamA())){
                //发起球队必须填写
                return ControllerReturnBase.errorResule(1501,"缺少发起球队");
            }
            if (tournament.getGame_time() == null){
                //比赛时间必须填写
                return ControllerReturnBase.errorResule(1501,"缺少比赛开始时间");
            }
            if (tournament.getContinue_time() == 0){
                //比赛持续时间必须填写
                return ControllerReturnBase.errorResule(1501,"缺少比赛持续时间");
            }
            if (Utils.isNull(tournament.getGame_site())){
                //比赛场地必须填写
                return ControllerReturnBase.errorResule(1501,"缺少比赛场地");
            }
            if (tournament.getClassify() == 1 || tournament.getClassify() == 4){
                //为创建比赛、约战时，以下为必传，创建训练可不填
                if (tournament.getGame_system() == 0){
                    //赛制必须填写
                    return ControllerReturnBase.errorResule(1501,"缺少赛制");
                }
                if (tournament.getUniform_teamA() == 0){
                    //发起球队的队服必须填写
                    return ControllerReturnBase.errorResule(1501,"缺少队服");
                }
                //创建约战时，可不传
                if (tournament.getClassify() == 1){
                    if (Utils.isNull(tournament.getEntry_teamB())){
                        //必须选择对手
                        return ControllerReturnBase.errorResule(1501,"缺少对手");
                    }
                }
                //###########################创建比赛验证创建者身份##############################
                Map<String,Object> players = teamMapper.selectTeamMemberByTeamIdAndUserId(tournament.getEntry_teamA(),userId);
                if (players == null){
                    return ControllerReturnBase.errorResule(1502,"不是发起球队的成员，无权发起比赛");
                }
                if ((int)players.get("jurisdiction") == 0){
                    //不是球队管理员，比赛设置为待审核状态
                    tournament.setGame_status(1);
                }else {
                    //为球队管理员,比赛不需要审核，设置为待应战状态
                    tournament.setGame_status(2);
                }
                //#############################创建定时任务（比赛专有），修改比赛状态################
                if (tournament.getApply_end_time() != null){
                    //报名结束时间不为null，定时修改比赛状态为报名已结束
                    gameTimedTask.updateGameStatus(tournament.getGame_id(),tournament.getApply_end_time(),4);
                }
            }else{
                //创建训练，直接设置状态为 报名中
                tournament.setGame_status(3);
            }
            //#############################创建定时任务，修改比赛/训练状态################
            //根据比赛开始时间修改比赛状态为进行中状态
            gameTimedTask.updateGameStatus(tournament.getGame_id(),tournament.getGame_time(),5);
            //根据比赛持续时间修改比赛状态为比赛已结束
            Date endTime = DateUtil.assignTimeLaterDayTime(tournament.getGame_time(),tournament.getContinue_time());
            gameTimedTask.updateGameStatus(tournament.getGame_id(),endTime,6);
            //#############################创建比赛###############################
            tournamentMapper.insertGame(tournament);
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(TournamentServiceImpl.class,"创建比赛/训练时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 获取比赛列表的筛选条件
     * @param userId
     * @return
     */
    @Override
    public Map<String, Object> gameFiltrateConditions(String userId) {
        try {
            //返回的结果集定义
            Map<String,Object> result_param = new HashMap<>();
            //#############################状态筛选条件###############################
            List<Map<String,Object>> status_param = new ArrayList<>();//状态结果集
            //所有状态名称，根据顺序列出
            String[] game_status_name = {"全部","待审核","待应战","报名中","报名截止","进行中","已结束","已取消"};
            //循环状态名
            for (int item=0;item<game_status_name.length;item++){
                Map<String,Object> game_status = new HashMap<>();
                game_status.put("status_name",game_status_name[item]);
                game_status.put("status_value",item);
                status_param.add(game_status);
            }
            result_param.put("status",status_param);
            //#############################时间筛选条件###############################
            List<Map<String,Object>> time_param = new ArrayList<>();//时间结果集
            //封装查询参数
            Map<String,Object> params = new HashMap<>();
            params.put("game_status",0);
            params.put("game_time","");
            params.put("user_id",userId);
            params.put("team_id","");
            params.put("start_now",0);
            params.put("pageSize",0);
            List<Map<String,Object>> gameList = tournamentMapper.selectGameList(params);
            //根据比赛时间，把比赛信息按月分组
            //单条比赛数据
            Map<String,Object> game = null;
            //分组后的结果集
            Map<String,String> group_Event = new HashMap<>();
            //解析比赛信息列表
            for (int i=0;i<gameList.size();i++){
                //获取到单条比赛信息
                game = gameList.get(i);
                if(!group_Event.containsKey(game.get("game_time"))){
                    group_Event.put(DateUtil.DTDateToString((Date) game.get("game_time"),"yyyy年MM月"),DateUtil.DTDateToString((Date) game.get("game_time"),"yyyy-MM"));
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
            //#############################球队筛选条件###############################
            List<Map<String,Object>> team_param = teamMapper.selectTeamList(userId);
            result_param.put("team",team_param);
            return ControllerReturnBase.successResule(result_param);
        }catch (Exception e){
            LoggerUtil.outError(TournamentServiceImpl.class,"获取比赛列表的筛选条件时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 获取用户所在的球队的所有比赛、训练
     * @param userId
     * @param pageNum
     * @param pageSize
     * @param game_status
     * @param game_time
     * @param team_id
     * @return
     */
    @Override
    public Map<String, Object> selectGameList(String userId, int pageNum, int pageSize, int game_status, String game_time, String team_id) {
        try {
            //获取总条数
            int countTotal = tournamentMapper.selectTeamGameListTotal(userId);
            //计算总页数
            int pageTotal = 0;
            if (countTotal != 0){
                pageTotal = countTotal%pageSize == 0 ?countTotal/pageSize:(countTotal/pageSize)+1;
            }else {
                pageSize = 0;
            }
            //计算起始行索引
            int start_now = 0;
            if (countTotal != 0){
                start_now = (pageNum-1)*pageSize;
            }
            //封装查询参数
            Map<String,Object> queryParams = new HashMap<>();
            queryParams.put("user_id",userId);
            queryParams.put("team_id",team_id);
            queryParams.put("game_status",game_status);
            queryParams.put("game_time",game_time);
            queryParams.put("start_now",start_now);
            queryParams.put("pageSize",pageSize);
            //查询比赛
            List<Map<String,Object>> gameList = tournamentMapper.selectGameList(queryParams);
            //#############################根据比赛时间，把比赛信息按月分组###############################
            //单条比赛数据
            Map<String,Object> game = null;
            //分组后的结果集
            Map<String,List<Map<String,Object>>> group_Event = new HashMap<>();
            //解析比赛信息列表
            for (int i=0;i<gameList.size();i++){
                //获取到单条比赛信息
                game = gameList.get(i);
                if(group_Event.containsKey(DateUtil.DTDateToString((Date) game.get("game_time"),"yyyy年MM月"))){
                    group_Event.get(DateUtil.DTDateToString((Date) game.get("game_time"),"yyyy年MM月")).add(game);
                }else{
                    List<Map<String,Object>> list = new ArrayList<>();
                    list.add(game);
                    group_Event.put(DateUtil.DTDateToString((Date) game.get("game_time"),"yyyy年MM月"),list);
                }
            }
            //解析出时间组
            List<Map<String,Object>> result_game = new ArrayList<>();
            for (String key:group_Event.keySet()){
                Map<String,Object> result_param = new HashMap<>();
                result_param.put("group",key);
                result_param.put("game",group_Event.get(key));
                result_game.add(result_param);
            }
            //封装返回参数
            Map<String,Object> return_result = new HashMap<>();
            return_result.put("pageNum",pageNum);
            return_result.put("pageSize",pageSize);
            return_result.put("pageTotal",pageTotal);
            return_result.put("list",result_game);
            return ControllerReturnBase.successResule(return_result);
        }catch (Exception e){
            LoggerUtil.outError(TournamentServiceImpl.class,"获取用户所在的球队的所有比赛、训练时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 获取用户收藏的比赛列表
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Map<String, Object> selectCollectionGameList(String userId, int pageNum, int pageSize) {
        try {
            //获取总条数
            int countTotal = tournamentMapper.selectCollectionGameListTotal(userId);
            //计算总页数
            int pageTotal = 0;
            if (countTotal != 0){
                pageTotal = countTotal%pageSize == 0 ?countTotal/pageSize:(countTotal/pageSize)+1;
            }
            //计算起始行索引
            int start_now = 0;
            if (countTotal != 0){
                start_now = (pageNum-1)*pageSize;
            }
            //封装查询参数
            Map<String,Object> queryParams = new HashMap<>();
            queryParams.put("user_id",userId);
            queryParams.put("start_now",start_now);
            queryParams.put("pageSize",pageSize);
            //查询比赛
            List<Map<String,Object>> gameList = tournamentMapper.selectCollectionGameList(queryParams);
            //#############################根据比赛时间，把比赛信息按月分组###############################
            //单条比赛数据
            Map<String,Object> game = null;
            //分组后的结果集
            Map<String,List<Map<String,Object>>> group_Event = new HashMap<>();
            //解析比赛信息列表
            for (int i=0;i<gameList.size();i++){
                //获取到单条比赛信息
                game = gameList.get(i);
                String group_key = DateUtil.DTDateToString((Date) game.get("game_time"),"yyyy年MM月");
                if(group_Event.containsKey(group_key)){
                    group_Event.get(group_key).add(game);
                }else{
                    List<Map<String,Object>> list = new ArrayList<>();
                    list.add(game);
                    group_Event.put(group_key,list);
                }
            }
            //解析出时间组
            List<Map<String,Object>> result_game = new ArrayList<>();
            for (String key:group_Event.keySet()){
                Map<String,Object> result_param = new HashMap<>();
                result_param.put("group",key);
                List<Map<String,Object>> value = group_Event.get(key);
                result_param.put("game",value);
                result_game.add(result_param);
            }
            //封装返回参数
            Map<String,Object> return_result = new HashMap<>();
            return_result.put("pageNum",pageNum);
            return_result.put("pageSize",pageSize);
            return_result.put("pageTotal",pageTotal);
            return_result.put("list",result_game);
            return ControllerReturnBase.successResule(return_result);
        }catch (Exception e){
            LoggerUtil.outError(TournamentServiceImpl.class,"获取用户收藏的比赛列表时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 查询比赛概况
     * @param user_id
     * @param game_id
     * @return
     */
    @Override
    public Map<String, Object> selectGameInfo(String user_id, String game_id) {
        try {
            //返回结果集定义
            Map<String,Object> result = new HashMap<>();
            //根据比赛id，查询比赛信息
            Map<String,Object> tournament = tournamentMapper.selectGameByGameIdByMap(game_id);
            result.put("game_info",tournament);
            result.put("share_url","http://101.201.145.244:8089/index.html/#/game/"+game_id+"/info");
            //查询是否收藏了比赛 1:未收藏，2：已收藏
            GameCollection gameCollection = gameCollectionMapper.selectEventCollection(user_id,game_id);
            int isCollection = 1;
            if (gameCollection != null){
                isCollection = 2;
            }
            result.put("isCollection",isCollection);
            //#############################判断是否是球队成员###############################
            Map<String,Object> teamAMember = teamMapper.selectTeamMemberByTeamIdAndUserId((String) tournament.get("entry_teamA"),user_id);
            if ((int)tournament.get("classify") == 3){
                //比赛为训练，只需要验证A球队即可
                result.put("jurisdictionA",teamAMember.get("jurisdiction"));
                List<Map<String,Object>> gameMember = gameMemberMapper.selectGameMemberList((String) tournament.get("game_id"),(String) tournament.get("entry_teamA"));
                result.put("member",gameMember);
            }else{
                Map<String,Object> teamBMember = teamMapper.selectTeamMemberByTeamIdAndUserId((String) tournament.get("entry_teamB"),user_id);
                //身份定义 0.游客，既不是A队成员又不是B队成员 1.A队成员  2.B队成员 3：AB两队共同成员
                if (teamAMember != null && teamBMember != null){
                    //AB两队共同成员
                    result.put("identity",3);
                    result.put("jurisdictionA",teamAMember.get("jurisdiction"));
                    result.put("jurisdictionB",teamBMember.get("jurisdiction"));
                    //获取参赛球员，默认显示A队报名成员
                    List<Map<String,Object>> gameMember = gameMemberMapper.selectGameMemberList((String) tournament.get("game_id"),(String) tournament.get("entry_teamA"));
                    result.put("member",gameMember);
                }else if (teamAMember != null && teamBMember == null){
                    //A队成员
                    result.put("identity",1);
                    result.put("jurisdictionA",teamAMember.get("jurisdiction"));
                    //获取参赛球员
                    List<Map<String,Object>> gameMember = gameMemberMapper.selectGameMemberList((String) tournament.get("game_id"),(String) tournament.get("entry_teamA"));
                    result.put("member",gameMember);
                }else if (teamAMember == null && teamBMember != null){
                    //B队成员
                    result.put("identity",2);
                    result.put("jurisdictionB",teamBMember.get("jurisdiction"));
                    //获取参赛球员
                    List<Map<String,Object>> gameMember = gameMemberMapper.selectGameMemberList((String) tournament.get("game_id"),(String) tournament.get("entry_teamB"));
                    result.put("member",gameMember);
                }else {
                    result.put("identity",0);
                }
            }
            //#############################如果比赛状态为报名中，查询是否已报名###############################
            if ((int)tournament.get("game_status") == 3){
                GameMember gameMember = gameMemberMapper.selectGameMember((String) tournament.get("game_id"),user_id);
                if (gameMember == null){
                    //未报名比赛
                    result.put("join_status",0);
                }else {
                    //已报名比赛
                    result.put("join_status",gameMember.getStatus());
                    //判断报名的是主队还是客队
                    if (gameMember.getTeam_id().equals((String) tournament.get("entry_teamA"))){
                        //报名参加的A队
                        result.put("join_team",1);
                    }else{
                        //报名参加的B队
                        result.put("join_team",2);
                    }
                }
            }
            return ControllerReturnBase.successResule(result);
        }catch (Exception e){
            LoggerUtil.outError(TournamentServiceImpl.class,"获取比赛/训练概况时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 审核或者应战比赛
     * @param game_id
     * @param type
     * @param comment
     * @param cause
     * @param user_id
     * @return
     */
    @Override
    public Map<String, Object> auditOrFightGame(String game_id, int type, int comment, String cause, String user_id,int uniform_teamB) {
        try {
            //验证比赛
            Tournament tournament = tournamentMapper.selectGameByGameId(game_id);
            if (tournament == null){
                return ControllerReturnBase.errorResule(1501,"比赛不存在");
            }
            if (tournament.getClassify() != 1){
                return ControllerReturnBase.errorResule(1501,"该比赛为赛事的赛程或为训练，不可更改");
            }
            //根据不同业务，验证权限,并设置状态值
            if (comment != 1 && comment != 2){
                return ControllerReturnBase.errorResule(1501,"审核参数错误");
            }
            if (type == 1){
                //审核比赛权限验证
                Map<String,Object> teamMember = teamMapper.selectTeamMemberByTeamIdAndUserId(tournament.getEntry_teamA(),user_id);
                if (teamMember == null){
                    return ControllerReturnBase.errorResule(1502,"无比赛审核权限");
                }else if ((int)teamMember.get("jurisdiction") == 0){
                    return ControllerReturnBase.errorResule(1502,"无比赛审核权限");
                }
                //参数设置
                if (comment == 1){
                    tournament.setGame_status(2);
                }else{
                    if (Utils.isNull(cause)){
                        return ControllerReturnBase.errorResule(1501,"请输入拒绝理由");
                    }
                    tournament.setGame_status(8);
                    tournament.setAudit_reason(cause);
                }
            }else if(type == 2){
                //应战比赛权限验证
                Map<String,Object> teamMember = teamMapper.selectTeamMemberByTeamIdAndUserId(tournament.getEntry_teamB(),user_id);
                if (teamMember == null){
                    return ControllerReturnBase.errorResule(1502,"无比赛应战权限");
                }else if ((int)teamMember.get("jurisdiction") == 0){
                    return ControllerReturnBase.errorResule(1502,"无比赛应战权限");
                }
                //应战比赛
                if (comment == 1){
                    if (uniform_teamB == 0){
                        return ControllerReturnBase.errorResule(1501,"请选择队服颜色");
                    }
                    tournament.setUniform_teamB(uniform_teamB);
                    tournament.setGame_status(3);
                }else {
                    if (Utils.isNull(cause)){
                        return ControllerReturnBase.errorResule(1501,"请输入拒绝理由");
                    }
                    tournament.setGame_status(9);
                    tournament.setRefuse_reason(cause);
                }
            }else {
                return ControllerReturnBase.errorResule(1501,"业务类型错误");
            }
            //修改比赛
            tournamentMapper.updateGame(tournament);
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(TournamentServiceImpl.class,"审核或者应战比赛时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 取消比赛
     * @param game_id
     * @param user_id
     * @return
     */
    @Override
    public Map<String, Object> cancelGame(String game_id, String user_id,String cause) {
        try {
            //查询比赛
            Tournament tournament = tournamentMapper.selectGameByGameId(game_id);
            if (tournament == null){
                return ControllerReturnBase.errorResule(1501,"比赛信息不存在");
            }else if (tournament.getGame_status() == 5 || tournament.getGame_status() == 6){
                return ControllerReturnBase.errorResule(1501,"比赛已开始或已结束，不可取消比赛");
            }
            //查询球员，验证权限
            Map<String,Object> teamMember = teamMapper.selectTeamMemberByTeamIdAndUserId(tournament.getEntry_teamA(),user_id);
            if (teamMember == null){
                return ControllerReturnBase.errorResule(1502,"无权限取消比赛");
            }else if ((int)teamMember.get("jurisdiction") == 0){
                return ControllerReturnBase.errorResule(1502,"无权限取消比赛");
            }
            //修改比赛状态
            tournament.setGame_status(7);
            tournament.setCancel_reason(cause);
            tournamentMapper.updateGame(tournament);
            //删除定时任务
            gameTimedTask.deleteGameTask(tournament.getGame_id());
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(TournamentServiceImpl.class,"取消比赛时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 修改比赛
     * @param tournament_params
     * @param userId
     * @return
     */
    @Override
    public Map<String, Object> updateGame(Tournament tournament_params, String userId) {
        try {
            //验证比赛是否可以修改
            Tournament tournament_db = tournamentMapper.selectGameByGameId(tournament_params.getGame_id());
            if (tournament_db == null){
                return ControllerReturnBase.errorResule(1501,"比赛信息不存在");
            }
            if (tournament_db.getClassify() == 2){
                return ControllerReturnBase.errorResule(1501,"比赛为赛事比赛，无权限修改");
            }
            //查询球员，验证权限
            Map<String,Object> teamMember = teamMapper.selectTeamMemberByTeamIdAndUserId(tournament_db.getEntry_teamA(),userId);
            if (teamMember == null){
                return ControllerReturnBase.errorResule(1502,"无权限修改比赛");
            }else if ((int)teamMember.get("jurisdiction") == 0){
                return ControllerReturnBase.errorResule(1502,"无权限修改比赛");
            }
            //修改比赛名称
            if (!Utils.isNull(tournament_params.getGame_name())){
                tournament_db.setGame_name(tournament_params.getGame_name());
            }
            //修改发起球队
            if (!Utils.isNull(tournament_params.getEntry_teamA())){
                if (tournament_db.getClassify() == 1){
                    //为比赛类型，判断比赛状态，如果状态不是待审核、待应战，则不可修改
                    if (tournament_db.getGame_status() != 1 && tournament_db.getGame_status() != 2){
                        return ControllerReturnBase.errorResule(1501,"比赛不为待审核或待应战状态，不可修改发起球队");
                    }
                }
                tournament_db.setEntry_teamA(tournament_params.getEntry_teamA());
            }
            //修改对手球队
            if (!Utils.isNull(tournament_params.getEntry_teamB())){
                if (tournament_db.getClassify() == 1){
                    if (tournament_db.getGame_status() != 1 && tournament_db.getGame_status() != 2){
                        return ControllerReturnBase.errorResule(1501,"比赛不为待审核或待应战状态，不可修改对手球队");
                    }
                    tournament_db.setEntry_teamB(tournament_params.getEntry_teamB());
                }
            }
            //修改比赛时间
            if (tournament_params.getGame_time() != null){
                if (tournament_db.getGame_status() != 1 && tournament_db.getGame_status() != 2 &&
                        tournament_db.getGame_status() != 3 && tournament_db.getGame_status() !=4){
                    return ControllerReturnBase.errorResule(1501,"比赛已开始或已结束，不可修改比赛开始时间");
                }
                tournament_db.setGame_time(tournament_params.getGame_time());
            }
            //修改比赛持续时间
            if (tournament_params.getContinue_time() != 0){
                tournament_db.setContinue_time(tournament_params.getContinue_time());
            }
            //修改赛制
            if (tournament_params.getGame_system() != 0){
                if (tournament_db.getGame_status() != 1 && tournament_db.getGame_status() != 2){
                    return ControllerReturnBase.errorResule(1501,"比赛不为待审核或待应战状态，不可修改赛制");
                }
                tournament_db.setGame_system(tournament_params.getGame_system());
            }
            //修改场地
            if (!Utils.isNull(tournament_params.getGame_site())){
                tournament_db.setGame_site(tournament_params.getGame_site());
            }
            //修改队服
            if (tournament_params.getUniform_teamA() != 0){
                tournament_db.setUniform_teamA(tournament_params.getUniform_teamA());
            }
            if (tournament_params.getUniform_teamB() != 0){
                tournament_db.setUniform_teamB(tournament_params.getUniform_teamB());
            }
            //修改报名截至时间
            if (tournament_params.getApply_end_time() != null){
                if (tournament_db.getGame_status() != 1 && tournament_db.getGame_status() != 2 &&
                        tournament_db.getGame_status() != 3 && tournament_db.getGame_status() !=4){
                    return ControllerReturnBase.errorResule(1501,"比赛已开始或已结束，不可修改比赛报名截至时间");
                }
                tournament_db.setApply_end_time(tournament_params.getApply_end_time());
            }
            //修改比赛费用
            if (tournament_params.getGame_cost() != 0){
                if (tournament_db.getGame_status() != 1 && tournament_db.getGame_status() != 2 &&
                        tournament_db.getGame_status() != 3 && tournament_db.getGame_status() !=4){
                    return ControllerReturnBase.errorResule(1501,"比赛已开始或已结束，不可修改比赛费用");
                }
                tournament_db.setGame_cost(tournament_params.getGame_cost());
            }
            //修改增值服务
            if (tournament_params.getValue_added() != 0){
                if (tournament_db.getGame_status() != 1 && tournament_db.getGame_status() != 2 &&
                        tournament_db.getGame_status() != 3 && tournament_db.getGame_status() !=4){
                    return ControllerReturnBase.errorResule(1501,"比赛已开始或已结束，不可修改比赛增值服务");
                }
                tournament_db.setValue_added(tournament_params.getValue_added());
            }
            tournamentMapper.updateGame(tournament_db);
            //停止定时任务
            gameTimedTask.deleteGameTask(tournament_db.getGame_id());
            //##################################重新开启定时任务##########################
            //根据比赛开始时间修改比赛状态为进行中状态
            gameTimedTask.updateGameStatus(tournament_db.getGame_id(),tournament_db.getGame_time(),5);
            //根据比赛持续时间修改比赛状态为比赛已结束
            Date endTime = DateUtil.assignTimeLaterDayTime(tournament_db.getGame_time(),tournament_db.getContinue_time());
            gameTimedTask.updateGameStatus(tournament_db.getGame_id(),endTime,6);
            if (tournament_db.getClassify() == 1){
                //报名结束时间不为null，定时修改比赛状态为报名已结束
                if (tournament_db.getApply_end_time() != null){
                    gameTimedTask.updateGameStatus(tournament_db.getGame_id(),tournament_db.getApply_end_time(),4);
                }
            }
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(TournamentServiceImpl.class,"修改比赛时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 获取约战列表
     * @param pageNum 页码
     * @param pageSize 获取条数
     * @param name 搜索条件
     * @param game_time 时间筛选条件
     * @param province 省份筛选条件
     * @param city 市筛选条件
     * @param area 区/县筛选条件
     * @param game_system 赛制筛选条件
     * @return
     */
    @Override
    public Map<String, Object> getAboutGameList(int pageNum, int pageSize, String name, String game_time, String province, String city, String area, int game_system) {
        try {
            //封装查询参数
            Map<String,Object> queryParams = new HashMap<>();
            queryParams.put("name",name);
            queryParams.put("game_time",game_time);
            queryParams.put("game_system",game_system);
            queryParams.put("province",province);
            queryParams.put("city",city);
            queryParams.put("area",area);
            //获取到该条件下的所有约战信息
            List<Map<String,Object>> gameList = tournamentMapper.getAboutGameTotal(queryParams);
            //#############################时间赛选条件###############################
            List<Map<String,Object>> time_param = new ArrayList<>();//时间结果集
            //根据比赛时间，把比赛信息按月分组
            //单条比赛数据
            Map<String,Object> game = null;
            //分组后的结果集
            Map<String,String> group_Event = new HashMap<>();
            //解析比赛信息列表
            for (int i=0;i<gameList.size();i++){
                //获取到单条比赛信息
                game = gameList.get(i);
                if(!group_Event.containsKey(game.get("game_time"))){
                    group_Event.put(DateUtil.DTDateToString((Date) game.get("game_time"),"yyyy年MM月"),DateUtil.DTDateToString((Date) game.get("game_time"),"yyyy-MM"));
                }
            }
            //解析出时间组
            for (String key:group_Event.keySet()){
                Map<String,Object> time = new HashMap<>();
                time.put("time_name",key);
                time.put("time_value",group_Event.get(key));
                time_param.add(time);
            }
            //#############################获取条件下真实返回的约战数据###############################
            //获取总条数
            int countTotal = gameList.size();
            //计算总页数
            int pageTotal = 0;
            if (countTotal != 0){
                pageTotal = countTotal%pageSize == 0 ?countTotal/pageSize:(countTotal/pageSize)+1;
            }
            //计算起始行索引
            int start_now = 0;
            if (countTotal != 0){
                start_now = (pageNum-1)*pageSize;
            }
            //封装查询参数
            queryParams.put("start_now",start_now);
            queryParams.put("pageSize",pageSize);
            //查询比赛
            gameList = tournamentMapper.getAboutGameList(queryParams);
            //#############################赛制筛选条件###############################
            List<Map<String,Object>> system_param = new ArrayList<>();//赛制结果集
            //所有赛制名称，根据顺序列出
            String[] game_system_name = {"全部","3人制","5人制","7人制","8人制","9人制","11人制"};
            //循环状态名
            for (int item=0;item<game_system_name.length;item++){
                Map<String,Object> game_system_object = new HashMap<>();
                game_system_object.put("system_name",game_system_name[item]);
                game_system_object.put("system_value",item);
                system_param.add(game_system_object);
            }
            //封装返回方法
            Map<String,Object> about = new HashMap<>();
            about.put("system",system_param);
            about.put("time",time_param);
            Map<String,Object> return_result = new HashMap<>();
            return_result.put("pageNum",pageNum);
            return_result.put("pageSize",pageSize);
            return_result.put("pageTotal",pageTotal);
            return_result.put("list",gameList);
            return_result.put("about",about);
            return ControllerReturnBase.successResule(return_result);
        }catch (Exception e){
            LoggerUtil.outError(TournamentServiceImpl.class,"获取约战列表时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }
}
