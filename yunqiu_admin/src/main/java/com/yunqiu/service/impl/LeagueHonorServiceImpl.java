package com.yunqiu.service.impl;

import com.yunqiu.dao.LeagueHonorMapper;
import com.yunqiu.model.LeagueHonor;
import com.yunqiu.model.PageCrt;
import com.yunqiu.model.TeamHonor;
import com.yunqiu.model.UserHonor;
import com.yunqiu.service.LeagueHonorService;
import com.yunqiu.service.TeamColourService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * app用户管理
 */

@Service
public class LeagueHonorServiceImpl implements LeagueHonorService {
    @Autowired
    private LeagueHonorMapper leagueHonorMapper;


    // 查询总条数
    public Integer selectCount(PageCrt page) {
        return leagueHonorMapper.selectTotalPage(page);
    }

    //分页查询
    public List<Map> selectPaging(PageCrt page) {
        return leagueHonorMapper.selectePaging(page);
    }


    @Override
    public Map<String, Object> addLeagueHonor(LeagueHonor leagueHonor) {
        try {
            String honorId=Utils.getID(22);
            leagueHonor.setHonorId(honorId);
            leagueHonor.setCreationTime(new Date());
            int result = leagueHonorMapper.insertLeagueHonor(leagueHonor);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }

            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(TeamColourService.class, "队服颜色添加时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    @Override
    public Map<String, Object> addLeagueHonorGL(String honorId,String glId){
        try {
            LeagueHonor leagueHonor = leagueHonorMapper.getLeagueHonorById(honorId);
            List<Map<String,Object>> map = new ArrayList<Map<String,Object>>();
            int result = 0;
            if(leagueHonor.getHonorType()==1){//球员
                UserHonor userHonor = new UserHonor();
                String usonId=Utils.getID(22);
                userHonor.setUsonId(usonId);
                userHonor.setUserId(glId);
                userHonor.setHonorId(honorId);
                userHonor.setGain_time(new Date());
                result = leagueHonorMapper.insertUserHonor(userHonor);
            }else if(leagueHonor.getHonorType()==2){//球队
                TeamHonor teamHonor = new TeamHonor();
                String teonId=Utils.getID(22);
                teamHonor.setTeonId(teonId);
                teamHonor.setTeamId(glId);
                teamHonor.setHonorId(honorId);
                teamHonor.setGain_time(new Date());
                result = leagueHonorMapper.insertTeamHonor(teamHonor);
            }
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }

            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(TeamColourService.class, "队服颜色添加时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    @Override
    public Map<String, Object> updatLeagueHonor(String honorId,String glId,String zid) {
        try {
            int result = 0;
            LeagueHonor leagueHonor = leagueHonorMapper.getLeagueHonorById(honorId);
            if(leagueHonor.getHonorType()==1){//球员
                result = leagueHonorMapper.updateUserHonor(zid,glId,honorId);
            }else if(leagueHonor.getHonorType()==2) {//球队
                result = leagueHonorMapper.updateTeamHonor(zid,glId,honorId);
            }
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }

            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(TeamColourService.class, "队服颜色修改时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }


    @Override
    public Map<String, Object> deleteLeagueHonorById(String honorId) {
        try {
            LeagueHonor leagueHonor = leagueHonorMapper.getLeagueHonorById(honorId);
            List<Map<String,Object>> map = new ArrayList<Map<String,Object>>();
            int result =  leagueHonorMapper.deletelegaueHonorById(honorId);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }

            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(TeamColourService.class, "队服颜色删除时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    @Override
    public Map<String, Object> deleteLeagueHonor(String honorId,String zid) {
        try {
            LeagueHonor leagueHonor = leagueHonorMapper.getLeagueHonorById(honorId);
            List<Map<String,Object>> map = new ArrayList<Map<String,Object>>();
            int result = 0;
            if(leagueHonor.getHonorType()==1){//球员
                result = leagueHonorMapper.deleteUserHonorById(zid);
            }else if(leagueHonor.getHonorType()==2) {//球队
                result = leagueHonorMapper.deleteTeamHonorById(zid);
            }

            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(TeamColourService.class, "队服颜色删除时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    @Override
    public Map<String, Object> getLeagueHonorList(String leagueId) {
        try {
            List<LeagueHonor> map = leagueHonorMapper.getLeagueHonorList(leagueId);
            return ControllerReturnBase.successResule(map);
        } catch (Exception e) {
            LoggerUtil.outError(TeamColourService.class, "查询队服颜色时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }


    @Override
    public Map<String, Object> getLeagueTY(String leagueId,String honorId) {
        try {
            LeagueHonor leagueHonor = leagueHonorMapper.getLeagueHonorById(honorId);
            List<Map<String,Object>> map = new ArrayList<Map<String,Object>>();
            if(leagueHonor!=null){
                if(leagueHonor.getHonorType()==1){//球员
                    map = leagueHonorMapper.getLeagueUserList(leagueId);
                }else if(leagueHonor.getHonorType()==2){//球队
                    map = leagueHonorMapper.getLeagueTeamList(leagueId);
                }
            }


            return ControllerReturnBase.successResule(map);
        } catch (Exception e) {
            LoggerUtil.outError(TeamColourService.class, "查询队服颜色时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }


}
