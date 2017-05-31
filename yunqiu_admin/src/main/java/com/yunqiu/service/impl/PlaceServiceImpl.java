package com.yunqiu.service.impl;

import com.yunqiu.dao.PlaceMapper;
import com.yunqiu.model.PageCrt;
import com.yunqiu.model.Place;
import com.yunqiu.service.PlaceService;
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
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    private PlaceMapper placeMapper;


    // 查询总条数
    public Integer selectCount(PageCrt page) {
        return placeMapper.selectTotalPage(page);
    }

    //分页查询
    public List<Map> selectPaging(PageCrt page) {
        return placeMapper.selectePaging(page);
    }

    /**
     * 场地添加
     * @return
     */
    @Override
    public Map<String, Object> addPlace(Place place) {
        try {
            //验证参数是否为空
            if (Utils.isNull(place.getPlaceName())) {
                return ControllerReturnBase.errorResule(1501, "场馆名称未填写");
            }
            //添加用户
            String placeId=Utils.getID(22);
            place.setPlaceId(placeId);
            place.setCreateTime(new Date());
            int result = placeMapper.insertPlace(place);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }

            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(PlaceService.class, "场地添加时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 场地修改
     * @return
     */
    @Override
    public Map<String, Object> updatePlace(Place place) {
        try {
            int result = placeMapper.updatePlace(place);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }

            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(PlaceService.class, "场地修改时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 场地删除
     * @return
     */
    @Override
    public Map<String, Object> deletePlace(String placeId) {
        try {
            int result = placeMapper.deletePlaceById(placeId);
            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(PlaceService.class, "场地删除时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }


    /**
     * 查询所有场地
     * @return
     */
    @Override
    public Map<String, Object> getPlaceList() {
        try {
            List<Place> map = placeMapper.getPlaceList();
            return ControllerReturnBase.successResule(map);
        } catch (Exception e) {
            LoggerUtil.outError(PlaceService.class, "查询场地时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }




}
