package com.yunqiu.service.impl;

import com.yunqiu.dao.BannerMapper;
import com.yunqiu.dao.ContentMapper;
import com.yunqiu.model.Banner;
import com.yunqiu.model.Content;
import com.yunqiu.model.PageCrt;
import com.yunqiu.service.BannerService;
import com.yunqiu.service.ContentService;
import com.yunqiu.service.TeamColourService;
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
public class ContentServiceImpl implements ContentService {
    @Autowired
    private ContentMapper contentMapper;


    // 查询总条数
    public Integer selectCount(PageCrt page) {
        return contentMapper.selectTotalPage(page);
    }

    //分页查询
    public List<Map> selectPaging(PageCrt page) {
        return contentMapper.selectePaging(page);
    }

    @Override
    public Map<String, Object> addContent(Content content) {
        try {
            String content_id=Utils.getID(22);
            content.setContent_id(content_id);
            content.setCreate_time(new Date());
            int result = contentMapper.insertContent(content);
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
    public Map<String, Object> updatContent(Content content) {
        try {
            int result = contentMapper.updateContent(content);
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
    public Map<String, Object> deleteContent(String content_id){
        try {
            int result = contentMapper.deleteContentById(content_id);
            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(TeamColourService.class, "队服颜色删除时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }






}
