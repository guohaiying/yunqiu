package com.yunqiu.service.impl;

import com.yunqiu.dao.*;
import com.yunqiu.model.Team;
import com.yunqiu.model.TeamCloudData;
import com.yunqiu.model.TeamFans;
import com.yunqiu.model.TeamMember;
import com.yunqiu.service.TeamCloudDataService;
import com.yunqiu.service.TeamService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.DateUtil;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import com.yunqiu.view.TeamDetailedDate;
import com.yunqiu.view.TeamSubjoinInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 球队管理业务
 * @author 武尊
 * @time 2017-01-16
 * @version 1.0
 */

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private TeamMemberMapper teamMemberMapper;
    @Autowired
    private TeamFansMapper teamFansMapper;
    @Autowired
    private GeneralMapper generalMapper;
    @Autowired
    private TeamCloudDataMapper teamCloudDataMapper;
    @Autowired
    private TeamCloudDataService teamCloudDataService;
    /**
     * 添加球队
     * @param team
     * @return
     */
    @Override
    public Map<String, Object> insertTeam(Team team,String user_id) {
        try {
            //验证参数
            if(Utils.isNull(user_id)){
                return ControllerReturnBase.errorResule(1502,"缺少用户id");
            }
            //验证球队名称
            if (Utils.isNull(team.getTeam_name())){
                return ControllerReturnBase.errorResule(1501,"缺少球队名称");
            }
            /*//验证队徽
            if (Utils.isNull(team.getBadge())){
                return ControllerReturnBase.errorResule(1501,"缺少球队队徽");
            }*/
            //验证球队类型
            if (team.getTeam_type() <= 0 || team.getTeam_type() >=5){
                return ControllerReturnBase.errorResule(1501,"球队类型错误");
            }
            /*//验证球队背景
            if (Utils.isNull(team.getBackground())){
                return ControllerReturnBase.errorResule(1501,"缺少球队背景图");
            }*/
            //验证球队成立时间
            /*if(team.getEstablish_time() == null){
                return ControllerReturnBase.errorResule(1501,"缺少球队成立时间");
            }*/
            /*//验证主场
            if(Utils.isNull(team.getHome())){
                return ControllerReturnBase.errorResule(1501,"缺少球队主场");
            }*/
            //设置主键
            String team_id = Utils.getUUID();
            team.setTeam_id(team_id);
            team.setInvite(Utils.getRandow(4));
            team.setEnter_time(new Date());
            team.setStatus(1);
            teamMapper.insertTeam(team);
            //创建球队成员
            TeamMember teamMember = new TeamMember();
            teamMember.setMember_id(Utils.getUUID());
            teamMember.setUser_id(user_id);
            teamMember.setTeam_id(team_id);
            teamMember.setIdentity(1);
            teamMember.setJurisdiction(1);
            teamMember.setEnqueue_time(new Date());
            teamMember.setStatus(1);
            teamMemberMapper.insertTeamMember(teamMember);
            //初始化球队云五数据
            teamCloudDataService.initialization(team_id);
            //返回球队id
            Map<String,String> parm = new HashMap<>();
            parm.put("team_id",team_id);
            return ControllerReturnBase.successResule(parm);
        }catch (Exception e){
            LoggerUtil.outError(TeamServiceImpl.class,"创建球队时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 查询球队基础信息
     * @param teamId
     * @return
     */
    @Override
    public Map<String, Object> selectTeamInfo(String teamId) {
        try {
            //验证球队id
            if (Utils.isNull(teamId)){
                return ControllerReturnBase.errorResule(1501,"缺少球队id");
            }
            Team team = teamMapper.selectTeamInfo(teamId);
            return ControllerReturnBase.successResule(team);
        }catch (Exception e){
            LoggerUtil.outError(TeamServiceImpl.class,"获取球队基本信息时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 修改球队基础信息
     * @param team
     * @param user_id
     * @return
     */
    @Override
    public Map<String, Object> updateTeamInfo(Team team, String user_id) {
        try {
            //验证球队
            if (Utils.isNull(team.getTeam_id())){
                return ControllerReturnBase.errorResule(1501,"缺少球队ID");
            }
            //获取身份
            TeamMember teamMember = teamMemberMapper.selectTeamMemberByTeamIdAndUserId(team.getTeam_id(),user_id);
            if (teamMember == null){
                return ControllerReturnBase.errorResule(1502,"无权操作");
            }
            //验证是否有操作权限
            if (teamMember.getJurisdiction() == 0){
                return ControllerReturnBase.errorResule(1502,"无权操作");
            }
            //获取球队信息
            Team db_team = teamMapper.selectTeamInfo(team.getTeam_id());
            if (db_team == null){
                return ControllerReturnBase.errorResule(1502,"球队不存在");
            }
            //修改球队信息
            //球队名称
            if (!Utils.isNull(team.getTeam_name())){
                db_team.setTeam_name(team.getTeam_name());
            }
            //球队队徽
            if(!Utils.isNull(team.getBadge())){
                db_team.setBadge(team.getBadge());
            }
            //球队背景图
            if (!Utils.isNull(team.getBackground())){
                db_team.setBackground(team.getBackground());
            }
            //球队类型
            if (team.getTeam_type() != 0){
                if (team.getTeam_type() <=0 || team.getTeam_type() >=5){
                    return ControllerReturnBase.errorResule(1501,"球队类型错误");
                }
                db_team.setTeam_type(team.getTeam_type());
            }
            //省
            if (!Utils.isNull(team.getProvince())){
                db_team.setProvince(team.getProvince());
            }
            //市
            if (!Utils.isNull(team.getCity())){
                db_team.setCity(team.getCity());
            }
            //区/县
            if (!Utils.isNull(team.getArea())){
                db_team.setArea(team.getArea());
            }
            //主场
            if (!Utils.isNull(team.getHome())){
                db_team.setHome(team.getHome());
            }
            //成立时间
            if (team.getEstablish_time() != null){
                db_team.setEstablish_time(team.getEstablish_time());
            }
            //标签
            if (!Utils.isNull(team.getLabel())){
                db_team.setLabel(team.getLabel());
            }
            teamMapper.updateTeamInfo(db_team);
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(TeamServiceImpl.class,"修改球队基础信息时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 获取球队详细数据
     * @param teamId
     * @param userId
     * @return
     */
    @Override
    public Map<String, Object> selectTeamDetailedDate(String teamId, String userId) {
        try {
            //验证球队ID参数是否为空
            if (Utils.isNull(teamId)){
                return ControllerReturnBase.errorResule(1501,"缺少球队Id");
            }
            //获取球队基础信息
            Team team_info = teamMapper.selectTeamInfo(teamId);
            //查询用户在本球队处于的身份
            TeamMember teamMember = teamMemberMapper.selectTeamMemberByTeamIdAndUserId(teamId,userId);
            TeamSubjoinInfo subjoinInfo = new TeamSubjoinInfo();//球队附加信息实例(身份、权限、平均年龄)
            if (teamMember == null){
                //teamMember为null，说明不是该球队成员，为游客
                subjoinInfo.setIdentity(0);
                subjoinInfo.setJurisdiction(0);
            }else{
                subjoinInfo.setIdentity(teamMember.getIdentity());
                subjoinInfo.setJurisdiction(teamMember.getJurisdiction());
            }
            //计算球队平均年龄
            double age = teamMapper.selectTeamAvgAge(teamId);
            subjoinInfo.setMean_age(age);
            //获取球队粉丝数
            int fans_number = teamFansMapper.selectTeamFansTotal(teamId);
            subjoinInfo.setFans_number(fans_number);
            //分享URl地址
            subjoinInfo.setShare_url("http://101.201.145.244:8089/index.html/#/team/"+teamId);
            //查询是否已关注球队
            TeamFans teamFans = teamFansMapper.selectTeamFans(userId,teamId);
            if (teamFans == null){
                //未关注
                subjoinInfo.setFocus_status(1);
            }else{
                //已关注
                subjoinInfo.setFocus_status(2);
            }
            //战力值计算(云五数据)
            TeamCloudData teamCloudData = teamCloudDataMapper.selectUserCloudDataByUserIdOne(teamId);
            //成绩计算
            Map<String,Object> grade = new HashMap<>();
            //获取所有比赛
            List<Map<String,Object>> game_list = generalMapper.selectGameAll(teamId);
            int game_total = game_list.size();//比赛总场数
            //参加比赛总场数
            grade.put("game_number",game_total);
            int victory = 0;//胜
            int flat = 0;//平
            int lose = 0;//负
            int no_entry = 0;//未录入
            //解析比赛，计算胜平负数据
            for (int item = 0;item<game_total;item++){
                Map<String,Object> game = game_list.get(item);
                if ((int)game.get("score_status") == 0){
                    //未录入数据
                    no_entry = no_entry+1;
                }else if ((int)game.get("score_teamA") == (int)game.get("score_teamB")){
                    //打平数据
                    flat = flat+1;
                }else {
                    if (teamId.equals(game.get("entry_teamA"))){
                        //A队比赛，并且A队得分大于B队得分，胜场+1,否则负场+1
                        if ((int)game.get("score_teamA") > (int)game.get("score_teamB")){
                            victory = victory+1;
                        }else{
                            lose = lose+1;
                        }
                    }else{
                        //B队比赛，并且B队得分大于A队得分，胜场+1,否则负场+1
                        if ((int)game.get("score_teamA") < (int)game.get("score_teamB")){
                            victory = victory+1;
                        }else{
                            lose = lose+1;
                        }
                    }
                }
            }
            //胜场
            grade.put("victory",victory);
            //负场
            grade.put("lose",lose);
            //平场
            grade.put("flat",flat);
            //未录入
            grade.put("no_entry",no_entry);
            //球队成员
            Map<String,Object> member= new HashMap<>();
            //获取球队总数
            Map<String,Integer> teamMemberNumber = teamMemberMapper.selectTeamMatchNumber(teamId);
            member.put("member_number",teamMemberNumber.get("team_match"));
            //获取成员列表
            List<Map<String,Object>> member_list = teamMemberMapper.selectTeamMemberByTeamId(teamId);
            member.put("member_list",member_list);
            //获取球队荣誉
            Map<String,Object> honor_params = teamMapper.getTeamHonorOne(teamId);
            //获取赛事
            List<Map<String,Object>> league = generalMapper.selectLeague(teamId);
            int leagueNumber = generalMapper.selectLeagueNumber(teamId);
            Map<String,Object> league_params = new HashMap<>();
            league_params.put("league_number",leagueNumber);
            league_params.put("league_list",league);
            //实例化返回数据
            TeamDetailedDate requst_parm = new TeamDetailedDate();
            requst_parm.setTeam_info(team_info);
            requst_parm.setSubjoin_info(subjoinInfo);
            requst_parm.setPower(teamCloudData);
            requst_parm.setGrade(grade);
            requst_parm.setMember(member);
            requst_parm.setHonor(honor_params);
            requst_parm.setLeague(league_params);
            return ControllerReturnBase.successResule(requst_parm);
        }catch (Exception e){
            LoggerUtil.outError(TeamServiceImpl.class,"获取球队详细信息时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 修改球队邀请码
     * @param teamId
     * @param invite
     * @param userId
     * @return
     */
    @Override
    public Map<String, Object> updateTeamInvite(String teamId,String invite, String userId) {
        try {
            //验证参数
            if (Utils.isNull(teamId) || Utils.isNull(invite)){
                return ControllerReturnBase.errorResule(1501,"缺少参数");
            }
            //验证身份
            TeamMember teamMember = teamMemberMapper.selectTeamMemberByTeamIdAndUserId(teamId,userId);
            if (teamMember.getJurisdiction() !=1 && teamMember.getJurisdiction()!=2){
                return ControllerReturnBase.errorResule(1502,"无权修改");
            }
            //修改邀请码
            teamMapper.updateTeamInvite(teamId,invite);
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(TeamServiceImpl.class,"修改球队邀请码时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 解散球队
     * @param teamId
     * @param userId
     * @return
     */
    @Override
    public Map<String, Object> dissolveTeam(String teamId, String userId) {
        try {
            //验证参数
            if (Utils.isNull(teamId)){
                return ControllerReturnBase.errorResule(1501,"缺少球队ID");
            }
            //验证权限
            TeamMember teamMember = teamMemberMapper.selectTeamMemberByTeamIdAndUserId(teamId,userId);
            if (teamMember.getJurisdiction() != 1){
                return ControllerReturnBase.errorResule(1502,"无权限操作");
            }
            //解散球队
            teamMapper.updateTeamStatusById(teamId,2);
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(TeamServiceImpl.class,"解散球队时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 查询球队列表
     * @param userId
     * @return
     */
    @Override
    public Map<String, Object> selectTeamList(String userId) {
        try {
            List<Map<String,Object>> teamList = teamMapper.selectTeamList(userId);
            return ControllerReturnBase.successResule(teamList);
        }catch (Exception e){
            LoggerUtil.outError(TeamServiceImpl.class,"查询球队列表时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     *查询所有球队列表(除了指定id得球队)
     * @param teamId
     * @param pageNum
     * @param pageSize
     * @param teamName
     * @return
     */
    @Override
    public Map<String, Object> selectTeamAllList(String teamId,int pageNum,int pageSize,String teamName) {
        try {
            Map<String,Object> params = new HashMap<>();
            //球队名称为null，teamId,pageNum,pageSize必传
            if (Utils.isNull(teamName)){
                if (pageNum == 0){
                    return ControllerReturnBase.errorResule(1501,"页码错误");
                }
                if (Utils.isNull(teamId)){
                    return ControllerReturnBase.errorResule(1501,"球队id错误");
                }
                params.put("teamId",teamId);
                params.put("pageNum",0);
                //获取总条数
                int countTotal = teamMapper.selectTeamAllList(params).size();
                //计算总页数
                int pageTotal = countTotal%pageSize == 0 ?countTotal/pageSize:(countTotal/pageSize)+1;
                //计算起始行索引
                int start_now = (pageNum-1)*pageSize;
                //重新封装查询条件
                params.put("teamId",teamId);
                params.put("start_now",start_now);
                params.put("pageSize",pageSize);
                //查询
                List<Map<String,Object>> teamList = teamMapper.selectTeamAllList(params);
                //返回数据包装
                Map<String,Object> return_result = new HashMap<>();
                return_result.put("pageNum",pageNum);
                return_result.put("pageSize",pageSize);
                return_result.put("pageTotal",pageTotal);
                return_result.put("list",teamList);
                return ControllerReturnBase.successResule(return_result);
            }else {
                params.put("teamName",teamName);
                List<Map<String,Object>> teamList = teamMapper.selectTeamAllList(params);
                return ControllerReturnBase.successResule(teamList);
            }
        }catch (Exception e){
            LoggerUtil.outError(TeamServiceImpl.class,"查询所有球队列表时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 查询可加入的球队
     * @param userId
     * @return
     */
    @Override
    public Map<String, Object> selectJoinTeam(String userId) {
        try {
            List<Map<String,Object>> teamList = teamMapper.selectJoinTeam(userId);
            return ControllerReturnBase.successResule(teamList);
        }catch (Exception e){
            LoggerUtil.outError(TeamServiceImpl.class,"查询可加入球队时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 查询球队战绩
     * @param team_id
     * @return
     */
    @Override
    public Map<String, Object> teamRecord(int pageNum,int pageSize,String team_id) {
        try {
            //获取总条数
            int total = teamMapper.teamRecordTotal(team_id);
            //计算总页数
            int pageTotal = total%pageSize == 0 ?total/pageSize:(total/pageSize)+1;
            //计算起始行索引
            int start_now = (pageNum-1)*pageSize;
            //获取到收藏的比赛
            List<Map<String,Object>> eventList = teamMapper.teamRecord(team_id,start_now,pageSize);
            /* -------------根据比赛时间，把比赛信息按月分组 */
            //单条比赛数据
            Map<String,Object> event = null;
            //分组后的结果集
            Map<String,List<Map<String,Object>>> groupEvent = new HashMap<>();
            //解析比赛信息列表
            for (int i=0;i<eventList.size();i++){
                //获取到单条比赛信息
                event = eventList.get(i);
                if(groupEvent.containsKey(DateUtil.DTDateToString((Date) event.get("game_time"),"yyyy年MM月") )){
                    groupEvent.get(DateUtil.DTDateToString((Date) event.get("game_time"),"yyyy年MM月")).add(event);
                }else{
                    List<Map<String,Object>> list = new ArrayList<>();
                    list.add(event);
                    groupEvent.put(DateUtil.DTDateToString((Date) event.get("game_time"),"yyyy年MM月"),list);
                }
            }
            //循环解析分组后的比赛
            List<Map<String,Object>> params = new ArrayList<>();
            for (String key:groupEvent.keySet()){
                Map<String,Object> param = new HashMap<>();
                param.put("group",key);
                param.put("children",groupEvent.get(key));
                params.add(param);
            }
            //返回数据
            Map<String,Object> result_param = new HashMap<>();
            result_param.put("total",pageTotal);
            result_param.put("pageSize",pageSize);
            result_param.put("pageNum",pageNum);
            result_param.put("record",params);
            return ControllerReturnBase.successResule(result_param);
        }catch (Exception e){
            LoggerUtil.outError(TeamServiceImpl.class,"查询球队战绩时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 查询球队荣誉
     * @param team_id
     * @return
     */
    @Override
    public Map<String, Object> honor(String team_id) {
        try {
            List<Map<String,Object>> result_params = teamMapper.getTeamHonor(team_id);
            return ControllerReturnBase.successResule(result_params);
        }catch (Exception e){
            LoggerUtil.outError(TeamServiceImpl.class,"查询球队荣誉时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 获取拥有管理权限的球队
     * @param user_id
     * @return
     */
    @Override
    public Map<String, Object> selectManagementTeam(String user_id) {
        try {
            List<Map<String,Object>> list = teamMapper.selectManagementTeam(user_id);
            return ControllerReturnBase.successResule(list);
        }catch (Exception e){
            LoggerUtil.outError(TeamServiceImpl.class,"查询拥有管理权限的球队时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 搜素球队、球员、赛事
     * @param name 搜索条件
     * @param type 0：3类全部返回 1：球队 2：球员 3：赛事
     * @return
     */
    @Override
    public Map<String, Object> searchTeamAndUserAndGame(String name, int type,int pageNum,int pageSize) {
        try {
            if (Utils.isNull(name)){
                return ControllerReturnBase.errorResule(1501,"缺少搜索条件");
            }else {
                name = "%"+name+"%";
            }
            Map<String,Object> result = new HashMap<>();
            if (type == 0){
                //type == 0 需要搜索球队、球员、赛事
                /**
                 * ######################################################
                 * 搜索球队
                 * #####################################################
                 */
                //获取总条数
                int total = teamMapper.searchTeamTotal(name);
                //计算总页数
                int pageTotal = total%pageSize == 0 ?total/pageSize:(total/pageSize)+1;
                //计算起始行索引
                int start_now = (pageNum-1)*pageSize;
                List<Map<String,Object>> team = teamMapper.searchTeam(name,start_now,pageSize);
                result.put("team_pageTotal",pageTotal);
                result.put("team_pageNum",pageNum);
                result.put("team_pageSize",pageSize);
                result.put("team",team);
                /**
                 * ######################################################
                 * 搜索球员
                 * #####################################################
                 */
                total = teamMapper.searchUserTotal(name);
                pageTotal = total%pageSize == 0 ?total/pageSize:(total/pageSize)+1;
                start_now = (pageNum-1)*pageSize;
                List<Map<String,Object>> user = teamMapper.searchUser(name,start_now,pageSize);
                result.put("user_pageTotal",pageTotal);
                result.put("user_pageNum",pageNum);
                result.put("user_pageSize",pageSize);
                result.put("user",user);
                /**
                 * ######################################################
                 * 搜索赛事
                 * #####################################################
                 */
                total = teamMapper.searchGameTotal(name);
                pageTotal = total%pageSize == 0 ?total/pageSize:(total/pageSize)+1;
                start_now = (pageNum-1)*pageSize;
                List<Map<String,Object>> game = teamMapper.searchGame(name,start_now,pageSize);
                result.put("game_pageTotal",pageTotal);
                result.put("game_pageNum",pageNum);
                result.put("game_pageSize",pageSize);
                result.put("game",game);
            }else if (type == 1){
                /**
                 * ######################################################
                 * 搜索球队
                 * #####################################################
                 */
                //获取总条数
                int total = teamMapper.searchTeamTotal(name);
                //计算总页数
                int pageTotal = total%pageSize == 0 ?total/pageSize:(total/pageSize)+1;
                //计算起始行索引
                int start_now = (pageNum-1)*pageSize;
                List<Map<String,Object>> team = teamMapper.searchTeam(name,start_now,pageSize);
                result.put("team_pageTotal",pageTotal);
                result.put("team_pageNum",pageNum);
                result.put("team_pageSize",pageSize);
                result.put("team",team);
            }else if (type == 2){
                /**
                 * ######################################################
                 * 搜索球员
                 * #####################################################
                 */
                int total = teamMapper.searchUserTotal(name);
                int pageTotal = total%pageSize == 0 ?total/pageSize:(total/pageSize)+1;
                int start_now = (pageNum-1)*pageSize;
                List<Map<String,Object>> user = teamMapper.searchUser(name,start_now,pageSize);
                result.put("user_pageTotal",pageTotal);
                result.put("user_pageNum",pageNum);
                result.put("user_pageSize",pageSize);
                result.put("user",user);
            }else {
                /**
                 * ######################################################
                 * 搜索赛事
                 * #####################################################
                 */
                int total = teamMapper.searchGameTotal(name);
                int pageTotal = total%pageSize == 0 ?total/pageSize:(total/pageSize)+1;
                int start_now = (pageNum-1)*pageSize;
                List<Map<String,Object>> game = teamMapper.searchGame(name,start_now,pageSize);
                result.put("game_pageTotal",pageTotal);
                result.put("game_pageNum",pageNum);
                result.put("game_pageSize",pageSize);
                result.put("game",game);
            }
            return ControllerReturnBase.successResule(result);
        }catch (Exception e){
            LoggerUtil.outError(TeamServiceImpl.class,"搜索球队、球员、赛事时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 获取排行榜
     * @param type 1：球队 2：球员
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Map<String, Object> getBillboard(int type, int pageNum, int pageSize) {
        try {
            List<Map<String,Object>> result = null;
            if (type == 1){
                result = teamMapper.getBillboardByTeam();
            }else if (type == 2){
                result = teamMapper.getBillboardByUser();
            }else {
                return ControllerReturnBase.errorResule(1501,"type参数错误");
            }
            return ControllerReturnBase.successResule(result);
        }catch (Exception e){
            LoggerUtil.outError(TeamServiceImpl.class,"获取排行榜时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 获取球队战力值
     * @param team_id
     * @return
     */
    @Override
    public Map<String, Object> getTeamPower(String team_id) {
        try {
            List<TeamCloudData> teamCloudDataList = teamCloudDataMapper.selectUserCloudDataByUserId(team_id,5);
            return ControllerReturnBase.successResule(teamCloudDataList);
        }catch (Exception e){
            LoggerUtil.outError(TeamServiceImpl.class,"获取球队战力值时发送错误",e);
            return ControllerReturnBase.errorResule();
        }
    }
}
