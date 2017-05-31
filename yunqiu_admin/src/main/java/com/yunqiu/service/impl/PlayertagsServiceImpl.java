package com.yunqiu.service.impl;

import com.yunqiu.dao.PlayerTagsMapper;
import com.yunqiu.model.PageCrt;
import com.yunqiu.model.PlayerTags;
import com.yunqiu.service.PlayertagsService;
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
public class PlayertagsServiceImpl implements PlayertagsService {
    @Autowired
    private PlayerTagsMapper playerTagsMapper;


    // 查询总条数
    public Integer selectCount(PageCrt page) {
        return playerTagsMapper.selectTotalPage(page);
    }

    //分页查询
    public List<Map> selectPaging(PageCrt page) {
        return playerTagsMapper.selectePaging(page);
    }

    /**
     * 球员标签添加
     * @return
     */
    @Override
    public Map<String, Object> addPlayertags(PlayerTags playerTags) {
        try {
            //验证参数是否为空
            if (Utils.isNull(playerTags.getTagsName())) {
                return ControllerReturnBase.errorResule(1501, "标签名称未填写");
            }
            String playertagsId=Utils.getID(22);
            playerTags.setPlayertagsId(playertagsId);
            playerTags.setCreateTime(new Date());
            int result = playerTagsMapper.insertPlayerTags(playerTags);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }

            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(PlayertagsService.class, "球员标签添加时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 球员标签修改
     * @return
     */
    @Override
    public Map<String, Object> updatePlayertags(PlayerTags playerTags) {
        try {
            int result = playerTagsMapper.updatePlayerTags(playerTags);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }

            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(PlayertagsService.class, "球员标签修改时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 球员标签删除
     * @return
     */
    @Override
    public Map<String, Object> deletePlayertags(String playertagsId) {
        try {
            int result = playerTagsMapper.deletePlayerTagsById(playertagsId);
            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(PlayertagsService.class, "球员标签删除时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }


    /**
     * 查询所有球员标签
     * @return
     */
    @Override
    public Map<String, Object> getPlayertagsList() {
        try {
            List<PlayerTags> map = playerTagsMapper.getPlayerTagsList();
            return ControllerReturnBase.successResule(map);
        } catch (Exception e) {
            LoggerUtil.outError(PlayertagsService.class, "查询标签时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }




}
