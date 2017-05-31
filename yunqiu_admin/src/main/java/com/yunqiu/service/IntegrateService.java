package com.yunqiu.service;

import com.yunqiu.model.Banner;
import com.yunqiu.model.Integrate;
import com.yunqiu.model.PageCrt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by 11366 on 2017/1/14.
 */
public interface IntegrateService {

    Map<String, Object> addIntegrate(Integrate integrate);

    Map<String, Object> updatIntegrate(Integrate integrate);

    Integrate findIdIntegrate(String id);
}
