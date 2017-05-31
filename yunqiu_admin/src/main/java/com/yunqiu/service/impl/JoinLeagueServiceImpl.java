package com.yunqiu.service.impl;

import com.yunqiu.dao.JoinLeagueMapper;
import com.yunqiu.dao.ScheduleMapper;
import com.yunqiu.dao.ScheduleRankingMapper;
import com.yunqiu.model.JoinLeague;
import com.yunqiu.model.PageCrt;
import com.yunqiu.model.ScheduleRanking;
import com.yunqiu.service.JoinLeagueService;
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
public class JoinLeagueServiceImpl implements JoinLeagueService {
    @Autowired
    private JoinLeagueMapper joinLeagueMapper;

    @Autowired
    private ScheduleMapper schedulMapper;

    @Autowired
    private ScheduleRankingMapper scheduleRankingMapper;

    // 查询总条数
    public Integer selectCount(PageCrt page) {
        return joinLeagueMapper.selectTotalPage(page);
    }

    //分页查询
    public List<Map> selectPaging(PageCrt page) {
        return joinLeagueMapper.selectePaging(page);
    }

    /**
     * 赛事球队添加
     * @return
     */
    @Override
    public Map<String, Object> addjoinLeague(JoinLeague joinleague) {
        try {
            String joinId=Utils.getID(22);
            joinleague.setJoinId(joinId);
            joinleague.setJoinTime(new Date());
            int result = joinLeagueMapper.insertJoinLeague(joinleague);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }

            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(JoinLeagueService.class, "赛事球队添加时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 赛事球队修改
     * @return
     */
    @Override
    public Map<String, Object> upjoinLeague(JoinLeague joinuleague) {
        try {
            joinuleague.setAuditTime(new Date());
            int result = joinLeagueMapper.updateJoinLeague(joinuleague);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }

            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(JoinLeagueService.class, "赛事球队修改时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 赛事球队删除
     * @return
     */
    @Override
    public Map<String, Object> deletejoinLeague(String joinId) {
        try {
            int result = joinLeagueMapper.deleteById(joinId);
            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(JoinLeagueService.class, "赛事球队删除时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }


    /**
     * 获取赛事名称
     * @return
     */
    @Override
    public String getJoinLeagueName(String leagueId) {
        String name = joinLeagueMapper.getJoinLeagueName(leagueId);
        //返回参数封装
        return name;
    }

}
