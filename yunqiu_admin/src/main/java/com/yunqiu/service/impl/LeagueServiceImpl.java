package com.yunqiu.service.impl;

import com.yunqiu.dao.LeagueMapper;
import com.yunqiu.dao.UsersMapper;
import com.yunqiu.model.AdminUser;
import com.yunqiu.model.Integrate;
import com.yunqiu.model.League;
import com.yunqiu.model.PageCrt;
import com.yunqiu.service.IntegrateService;
import com.yunqiu.service.LeagueService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LeagueServiceImpl implements LeagueService {
    @Autowired
    private LeagueMapper leagueMapper;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private IntegrateService integrateService;

    // 查询总条数
    @Override
    public Integer selectCount(PageCrt page) {
        return leagueMapper.selectTotalPage(page);
    }

    //分页查询
    @Override
    public List<Map> selectPaging(PageCrt page) {
        return leagueMapper.selectePaging(page);
    }

    //查询赛事举办单位
    @Override
    public League getHostUnit(String leagueId) {
        return leagueMapper.getHostUnit(leagueId);
    }

    /**
     * 赛事添加
     * @return
     */
    @Override
    public Map<String, Object> addLeague(League league) {
        try {
            //验证参数是否为空
            /*if (Utils.isNull(league.getLeagueName())) {
                return ControllerReturnBase.errorResule(1501, "球队名称未填写");
            }*/
            if (league.getStartTime().getTime()<= league.getApplyEndTime().getTime()) {
                return ControllerReturnBase.errorResule(1501, "开始时间必须晚于报名结束时间，请重新选择");
            }

            //添加赛事
            String leagueId=Utils.getID(22);
            league.setLeagueId(leagueId);

            long nowdate = new Date().getTime();

            if(nowdate>=league.getApplyStartTime().getTime() && nowdate<league.getApplyEndTime().getTime()){//报名中
                league.setStatus(1);
            }else if(nowdate>league.getApplyStartTime().getTime() && nowdate<=league.getApplyEndTime().getTime()){//进行中
                league.setStatus(2);
            }else if(nowdate>=league.getEndTime().getTime()){//已结束
                league.setStatus(3);
            }else if(nowdate>=league.getApplyEndTime().getTime() && nowdate<league.getStartTime().getTime()){//报名截至
                league.setStatus(5);
            }

            int result = leagueMapper.insertLeague(league);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }

            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(LeagueService.class, "赛事添加时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 赛事修改
     * @return
     */
    @Override
    public Map<String, Object> updateLeague(League league) {
        try {
            if (league.getStartTime().getTime()<= league.getApplyEndTime().getTime()) {
                return ControllerReturnBase.errorResule(1501, "开始时间必须晚于报名结束时间，请重新选择");
            }

            long nowdate = new Date().getTime();

            if(nowdate>=league.getApplyStartTime().getTime() && nowdate<league.getApplyEndTime().getTime()){//报名中
                league.setStatus(1);
            }else if(nowdate>league.getApplyStartTime().getTime() && nowdate<=league.getApplyEndTime().getTime()){//进行中
                league.setStatus(2);
            }else if(nowdate>=league.getEndTime().getTime()){//已结束
                league.setStatus(3);
            }else if(nowdate>=league.getApplyEndTime().getTime() && nowdate<league.getStartTime().getTime()){//报名截至
                league.setStatus(5);
            }

            /*league.setApplyStartTime(league.getStartTime());*/
            int result = leagueMapper.updateLeague(league);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }

            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(LeagueService.class, "赛事修改时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }


    /**
     * 修改赛事举办单位
     * @return
     */
    @Override
    public Map<String, Object> updateHostUnit(League league) {
        try {
            int result = leagueMapper.updateHostUnit(league);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }

            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(LeagueService.class, "修改赛事举办单位时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 修改场地
     * @return
     */
    @Override
    public Map<String, Object> updateLeagueSite(League league) {
        try {
            //leagueSite
            /*String newLengSit = "";
            League sit = leagueMapper.getHostUnit(league.getLeagueId());
            String [] sitArray = sit.getLeagueSite().split(",");
            for(int i=0;i<sitArray.length;i++){
                if(!sitArray[i].equals(league.getLeagueSite())) {
                    if(i==sitArray.length){
                        newLengSit += sitArray[i];
                    }else{
                        newLengSit += sitArray[i]+",";
                    }
                }
            }*/
            int result = leagueMapper.updateLeagueSite(league);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }

            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(LeagueService.class, "修改赛事场地时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 关闭赛事
     * @return
     */
    @Override
    public Map<String, Object> closeLeague(String leagueId,String userName,String password,String userId) {
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

            int result = leagueMapper.closeLeague(leagueId);
            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(LeagueService.class, "赛事删除时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }


    /**
     * 获取所有赛事
     * @return
     */
    @Override
    public Map<String, Object> getLeagueList(){
        try {
            List<League> map = leagueMapper.getLeagueList();
            return ControllerReturnBase.successResule(map);
        } catch (Exception e) {
            LoggerUtil.outError(LeagueService.class, "查询赛事列表时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }


    // 查询总条数
    @Override
    public Integer selectCountTeam(PageCrt page) {
        return leagueMapper.selectTotalPageTeam(page);
    }

    //分页查询
    @Override
    public List<Map> selectPagingTeam(PageCrt page) {
        return leagueMapper.selectePagingTeam(page);
    }


    // 查询总条数
    @Override
    public Integer selectCountUser(PageCrt page) {
        return leagueMapper.selectTotalPageUser(page);
    }

    //分页查询
    @Override
    public List<Map> selectPagingUser(PageCrt page) {
        return leagueMapper.selectePagingUser(page);
    }

    // 查询总条数
    @Override
    public Integer selectCountPoints(PageCrt page) {
        return leagueMapper.selectTotalPagePoints(page);
    }

    //分页查询
    @Override
    public List<Map> selectPagingPoints(PageCrt page) {
        Integrate integrate = integrateService.findIdIntegrate("1");
        List<Map> list = leagueMapper.selectePagingPoints(page);
        for(int i=0;i<list.size();i++){
            Map<String , Object>  listmap = list.get(i);
            int victory = Integer.parseInt(listmap.get("victory")+"");
            int negation = Integer.parseInt(listmap.get("negation")+"");
            int flat = Integer.parseInt(listmap.get("flat")+"");
            int points = victory*integrate.getWin()+negation*integrate.getFail()+flat*integrate.getTie();
            leagueMapper.updateScheduleRanking(listmap.get("rankingId")+"",points+"");
            listmap.put("integral",points);
        }
        return list;
    }


}
