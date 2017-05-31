package com.yunqiu.league.service.impl;

import com.yunqiu.league.dao.VideoMapper;
import com.yunqiu.league.service.VideoService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 11366 on 2017/3/2.
 */

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoMapper videoMapper;

    @Override
    public Map<String, Object> selectVideo(String id, int classify,int pageNum,int pageSize) {
        try {
            if (classify == 1 || classify == 2){
                if (Utils.isNull(id)){
                    return ControllerReturnBase.errorResule(1501,"id不能为null");
                }
            }
            Map<String,Object> params = new HashMap<>();
            params.put("classify",classify);
            params.put("id",id);
            //获取总条数
            int countTotal = videoMapper.selectVideoTotal(params);
            //计算总页数
            int pageTotal = countTotal%pageSize == 0 ?countTotal/pageSize:(countTotal/pageSize)+1;
            //计算起始行索引
            int start_now = (pageNum-1)*pageSize;
            params.put("start_now",start_now);
            params.put("pageSize",pageSize);
            List<Map<String,Object>> result = videoMapper.selectVideo(params);
            //返回数据包装
            Map<String,Object> return_result = new HashMap<>();
            return_result.put("pageNum",pageNum);
            return_result.put("pageSize",pageSize);
            return_result.put("pageTotal",pageTotal);
            return_result.put("list",result);
            return ControllerReturnBase.successResule(return_result);
        }catch (Exception e){
            LoggerUtil.outError(VideoServiceImpl.class,"查询视频列表时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }
}
