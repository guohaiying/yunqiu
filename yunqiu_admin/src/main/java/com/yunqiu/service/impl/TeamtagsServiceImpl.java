package com.yunqiu.service.impl;

import com.yunqiu.dao.TeamTagsMapper;
import com.yunqiu.model.PageCrt;
import com.yunqiu.model.TeamTags;
import com.yunqiu.service.TeamtagsService;
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
 * app用户管理
 */

@Service
public class TeamtagsServiceImpl implements TeamtagsService {
    @Autowired
    private TeamTagsMapper teamTagsMapper;


    // 查询总条数
    public Integer selectCount(PageCrt page) {
        return teamTagsMapper.selectTotalPage(page);
    }

    //分页查询
    public List<Map> selectPaging(PageCrt page) {
        return teamTagsMapper.selectePaging(page);
    }

    /**
     * 球员标签添加
     * @return
     */
    @Override
    public Map<String, Object> addTeamtags(TeamTags teamTags) {
        try {
            //验证参数是否为空
            if (Utils.isNull(teamTags.getTagsName())) {
                return ControllerReturnBase.errorResule(1501, "标签名称未填写");
            }
            String teamtagsId=Utils.getID(22);
            teamTags.setTeamtagsId(teamtagsId);
            teamTags.setCreateTime(new Date());
            int result = teamTagsMapper.insertTeamTags(teamTags);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }

            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(TeamtagsService.class, "球队标签添加时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 球队标签修改
     * @return
     */
    @Override
    public Map<String, Object> updatTeamtags(TeamTags teamTags) {
        try {
            int result = teamTagsMapper.updateTeamTags(teamTags);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }

            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(TeamtagsService.class, "球队标签修改时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 球队标签删除
     * @return
     */
    @Override
    public Map<String, Object> deleteTeamtags(String teamtagsId) {
        try {
            int result = teamTagsMapper.deletePlayerTagsById(teamtagsId);
            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(TeamtagsService.class, "球队标签删除时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }


    /**
     * 查询所有球队标签
     * @return
     */
    @Override
    public Map<String, Object> getTeamtagsList() {
        try {
            List<TeamTags> map = teamTagsMapper.getTeamtagsList();
            return ControllerReturnBase.successResule(map);
        } catch (Exception e) {
            LoggerUtil.outError(TeamtagsService.class, "查询标签时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }




}
