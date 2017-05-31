package com.yunqiu.service;

import com.yunqiu.model.PageCrt;
import com.yunqiu.model.Place;

import java.util.List;
import java.util.Map;

/**
 * Created by 11366 on 2017/1/14.
 */
public interface PlaceService {
    Integer selectCount(PageCrt page);

    List<Map> selectPaging(PageCrt page);

    Map<String, Object> addPlace(Place place);

    Map<String, Object> updatePlace(Place place);

    Map<String, Object> deletePlace(String placeId);

    Map<String, Object> getPlaceList();

}
