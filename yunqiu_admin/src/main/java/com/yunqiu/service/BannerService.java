package com.yunqiu.service;

import com.yunqiu.model.Banner;
import com.yunqiu.model.PageCrt;
import com.yunqiu.model.TeamColour;

import java.util.List;
import java.util.Map;

/**
 * Created by 11366 on 2017/1/14.
 */
public interface BannerService {
    Integer selectCount(PageCrt page);

    List<Map> selectPaging(PageCrt page);

    Map<String, Object> addBanner(Banner banner);

    Map<String, Object> updatBanner(Banner banner);

    Map<String, Object> deleteBanner(String banner_id);


}
