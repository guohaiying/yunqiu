package com.yunqiu.league.service.impl;

import com.yunqiu.league.dao.JoinLeagueMapper;
import com.yunqiu.league.model.JoinLeague;
import com.yunqiu.league.service.JoinLeagueService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 11366 on 2017/2/23.
 */

@Service
public class JoinLeagueServiceImpl implements JoinLeagueService {
    @Autowired
    private JoinLeagueMapper joinLeagueMapper;

    /**
     * 报名赛事
     * @param joinLeague
     * @return
     */
    @Override
    public Map<String, Object> joinLeague(JoinLeague joinLeague) {
        try {
            if (Utils.isNull(joinLeague.getTeam_id())){
                return ControllerReturnBase.errorResule(1501,"缺少球队id");
            }
            if (Utils.isNull(joinLeague.getLeague_id())){
                return ControllerReturnBase.errorResule(1501,"缺少赛事id");
            }
            joinLeague.setJoin_id(Utils.getUUID());
            joinLeague.setJoin_time(new Date());
            joinLeague.setAudit_status(1);
            joinLeagueMapper.insertJoinLeague(joinLeague);
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(JoinLeagueServiceImpl.class,"报名赛事时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 查询报名的球队
     * @param league_id
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Map<String, Object> selectJoin(String league_id, int pageNum, int pageSize) {
        try {
            //获取已报名的总条数
            int countTotal = joinLeagueMapper.selectJoinLeagueTotal(league_id);
            //计算总页数
            int pageTotal = countTotal%pageSize == 0 ?countTotal/pageSize:(countTotal/pageSize)+1;
            //计算起始行索引
            int start_now = (pageNum-1)*pageSize;
            //获取已报名的球队
            List<Map<String,Object>> joinTeam = joinLeagueMapper.selectJoinLeague(start_now,pageSize,league_id);
            //返回数据包装
            Map<String,Object> return_result = new HashMap<>();
            return_result.put("pageNum",pageNum);
            return_result.put("pageSize",pageSize);
            return_result.put("pageTotal",pageTotal);
            return_result.put("list",joinTeam);
            return ControllerReturnBase.successResule(return_result);
        }catch (Exception e){
            LoggerUtil.outError(JoinLeagueServiceImpl.class,"查询报名的球队时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }
}
