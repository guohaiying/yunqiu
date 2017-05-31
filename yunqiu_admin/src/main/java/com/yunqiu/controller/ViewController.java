package com.yunqiu.controller;

import com.yunqiu.model.Global;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

/**
 * 控制页面跳转
 *
 * @author 武尊
 * @version 1.0
 * @time 2017-01-12
 */

@Controller
@RequestMapping("/view")
@EnableAutoConfiguration
public class ViewController {
    //定时器存储
    private Map<String,Timer> timerMap = new HashMap<>();

    /**
     * 登陆页面地址
     *
     * @return
     */
    @RequestMapping("/login")
    public String html_login() {
        return "login";
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    /**
     * 首页地址
     *
     * @return
     */
    @RequestMapping("/index")
    public String html_index() {
        return "index";
    }

    @RequestMapping("/go_controller")
    public String html_go_controller() {
        return "ueditor/jsp/controller";
    }

    /**
     * 跳入管理用户列表
     */
    @RequestMapping("/findAllUser")
    public String findAllUser(HttpSession httpSession, String menuId) {
        httpSession.setAttribute("menuId",menuId);
        return "user/user";
    }

    /**
     * 跳入管理角色列表
     */
    @RequestMapping("/go_role")
    public String roleList(HttpSession httpSession, String menuId, HttpServletRequest request) {
        httpSession.setAttribute("menuId",menuId);
        return "user/role";
    }


    /**
     * 退出
     *
     * @return
     */
    @RequestMapping("/exit")
    public String html_exit(HttpSession httpSession) {
        Map<String,Object> map = (Map<String,Object>)httpSession.getAttribute(Global.USER_SESSION_KEY);
        Map<String,Object> data = (Map<String,Object>)map.get("data");
        String userId = data.get("userId")+"";
        Timer timer = timerMap.get(userId);
        timerMap.get(userId).cancel();
        timerMap.remove(userId);
        httpSession.invalidate();
        return "login";
    }

    /**
     * 修改密码
     */
    @RequestMapping("/go_upPassword")
    public String uppassword(HttpSession httpSession, String menuId, HttpServletRequest request) {
        return "user/uppassword";
    }


    /**
     * 跳入app用户列表
     */
    @RequestMapping("/goUser")
    public String goUser(HttpSession httpSession, String menuId, HttpServletRequest request) {
        httpSession.setAttribute("menuId",menuId);
        return "appuser/user";
    }


    /**
     * 跳入场地管理
     */
    @RequestMapping("/go_place")
    public String goPlace(HttpSession httpSession, String menuId, HttpServletRequest request) {
        httpSession.setAttribute("menuId",menuId);
        return "place/place";
    }


    /**
     * 跳入球队管理
     */
    @RequestMapping("/go_team")
    public String goTeam(HttpSession httpSession, String menuId, HttpServletRequest request) {
        httpSession.setAttribute("menuId",menuId);
        return "team/team";
    }

    /**
     * 跳入赛事管理
     */
    @RequestMapping("/go_league")
    public String goLeague(HttpSession httpSession, String menuId, HttpServletRequest request) {
        httpSession.setAttribute("menuId",menuId);
        return "league/league";
    }


    /**
     * 跳入球队管理
     */
    @RequestMapping("/go_joinleague")
    public String gojoinleague(HttpSession httpSession, String menuId,String leagueId, HttpServletRequest request) {
        httpSession.setAttribute("menuId",menuId);
        httpSession.setAttribute("joinleague_leagueId",leagueId);
        return "league/joinuleague";
    }

    /**
     * 跳入球队数据
     */
    @RequestMapping("/go_teamData")
    public String goTeamData(HttpSession httpSession, String menuId,String teamId, HttpServletRequest request) {
        httpSession.setAttribute("menuId",menuId);
        httpSession.setAttribute("teamData_teamId",teamId);
        return "team/teamData";
    }

    /**
     * 跳入球员数据
     */
    @RequestMapping("/go_userData")
    public String goUserData(HttpSession httpSession, String menuId,String userId, HttpServletRequest request) {
        httpSession.setAttribute("menuId",menuId);
        httpSession.setAttribute("user_userId",userId);
        return "appuser/userData";
    }


    /**
     * 跳入赛程管理
     */
    @RequestMapping("/go_tournament")
    public String goTournament(HttpSession httpSession, String menuId,String leagueId, HttpServletRequest request) {
        httpSession.setAttribute("menuId",menuId);
        httpSession.setAttribute("tournament_leagueId",leagueId);
        return "tournament/tournament";
    }

    /**
     * 跳入荣誉详情
     */
    @RequestMapping("/go_leagueHonor")
    public String goLeagueHonor(HttpSession httpSession, String menuId,String leagueId, HttpServletRequest request) {
        httpSession.setAttribute("menuId",menuId);
        httpSession.setAttribute("leagueHonor_leagueId",leagueId);
        return "league/leagueHonor";
    }

    /**
     * 跳入榜单详情
     */
    @RequestMapping("/go_leagueLIST")
    public String goLeagueLIST(HttpSession httpSession, String menuId,String leagueId, HttpServletRequest request) {
        httpSession.setAttribute("menuId",menuId);
        httpSession.setAttribute("leagueLIST_leagueId",leagueId);
        return "league/leagueLIST";
    }

    /**
     * 跳入积分榜
     */
    @RequestMapping("/go_leaguePoints")
    public String goLeaguePoints(HttpSession httpSession, String menuId,String leagueId, HttpServletRequest request) {
        httpSession.setAttribute("menuId",menuId);
        httpSession.setAttribute("leaguePoints_leagueId",leagueId);
        return "league/leaguePoints";
    }


    /**
     * 跳入视频管理
     */
    @RequestMapping("/go_video")
    public String goVideo(HttpSession httpSession, String menuId, Integer classify, HttpServletRequest request) {
        httpSession.setAttribute("video_classify",classify);
        httpSession.setAttribute("menuId",menuId);
        return "video/video";
    }

    /**
     * 跳入视频管理
     */
    @RequestMapping("/go_GIF")
    public String goGIF(HttpSession httpSession, String menuId, HttpServletRequest request) {
        httpSession.setAttribute("menuId",menuId);
        return "video/videoGIF";
    }

    /**
     * 跳入球员标签库
     */
    @RequestMapping("/go_playerTags")
    public String goPlayerTags(HttpSession httpSession, String menuId, HttpServletRequest request) {
        httpSession.setAttribute("menuId",menuId);
        return "team/playerTags";
    }

    /**
     * 跳入球队标签库
     */
    @RequestMapping("/go_teamTags")
    public String goTeamTags(HttpSession httpSession, String menuId, HttpServletRequest request) {
        httpSession.setAttribute("menuId",menuId);
        return "team/teamTags";
    }

    /**
     * 跳入队服颜色库
     */
    @RequestMapping("/go_teamColour")
    public String goTeamColour(HttpSession httpSession, String menuId, HttpServletRequest request) {
        httpSession.setAttribute("menuId",menuId);
        return "team/teamColour";
    }

    /**
     * 跳入比赛管理
     */
    @RequestMapping("/go_tournamentList")
    public String goTournamentList(HttpSession httpSession, String menuId,String leagueId, HttpServletRequest request) {
        httpSession.setAttribute("menuId",menuId);
        httpSession.setAttribute("tournament_leagueId",leagueId);
        return "tournament/tournamentList";
    }

    /**
     * 跳入用户所属球队
     */
    @RequestMapping("/go_UserTeam")
    public String goUserTeam(HttpSession httpSession, HttpServletRequest request,String userId) {
        httpSession.setAttribute("team_userId",userId);
        return "team/userTeam";
    }

    /**
     * 跳入球队队员列表
     */
    @RequestMapping("/go_TeamUser")
    public String goTeamUser(HttpSession httpSession, HttpServletRequest request,String teamId) {
        httpSession.setAttribute("team_teamId",teamId);
        return "team/teamUser";
    }

    /**
     * 跳入比赛管理 报名状态
     */
    @RequestMapping("/go_tournametStatus")
    public String goTournametStatus(HttpSession httpSession, HttpServletRequest request,String gameId) {
        httpSession.setAttribute("tournamestatus_gameId",gameId);
        return "tournament/tournamentStatus";
    }

    /**
     * 跳入比赛管理 比赛数据
     */
    @RequestMapping("/go_tournametData")
    public String goTournametData(HttpSession httpSession, HttpServletRequest request,String gameId) {
        httpSession.setAttribute("tournamedata_gameId",gameId);
        return "tournament/tournamentData";
    }

    /**
     * 跳入banner管理
     */
    @RequestMapping("/go_banner")
    public String goBanner(HttpSession httpSession,String menuId, HttpServletRequest request) {
        httpSession.setAttribute("menuId",menuId);
        return "montion/banner";
    }

    /**
     * 跳入热点文章管理
     */
    @RequestMapping("/go_content")
    public String goContent(HttpSession httpSession,String menuId, HttpServletRequest request) {
        httpSession.setAttribute("menuId",menuId);
        return "montion/content";
    }


}
