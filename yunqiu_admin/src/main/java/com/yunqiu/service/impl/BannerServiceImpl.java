package com.yunqiu.service.impl;

import com.yunqiu.dao.BannerMapper;
import com.yunqiu.dao.TeamColourMapper;
import com.yunqiu.model.Banner;
import com.yunqiu.model.PageCrt;
import com.yunqiu.model.TeamColour;
import com.yunqiu.service.BannerService;
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

/**
 * app用户管理
 */

@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerMapper bannerMapper;


    // 查询总条数
    public Integer selectCount(PageCrt page) {
        return bannerMapper.selectTotalPage(page);
    }

    //分页查询
    public List<Map> selectPaging(PageCrt page) {
        return bannerMapper.selectePaging(page);
    }

    @Override
    public Map<String, Object> addBanner(Banner banner) {
        try {
            String banner_id=Utils.getID(22);
            banner.setBanner_id(banner_id);
            banner.setCreate_time(new Date());
            int result = bannerMapper.insertBanner(banner);
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
    public Map<String, Object> updatBanner(Banner banner) {
        try {
            int result = bannerMapper.updateBanner(banner);
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
    public Map<String, Object> deleteBanner(String banner_id) {
        try {
            int result = bannerMapper.deleteBannerById(banner_id);
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
