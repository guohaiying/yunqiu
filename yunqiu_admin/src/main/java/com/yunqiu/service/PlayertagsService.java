package com.yunqiu.service;

import com.yunqiu.model.PageCrt;
import com.yunqiu.model.PlayerTags;

import java.util.List;
import java.util.Map;

/**
 * Created by 11366 on 2017/1/14.
 */
public interface PlayertagsService {
    Integer selectCount(PageCrt page);

    List<Map> selectPaging(PageCrt page);

    Map<String, Object> addPlayertags(PlayerTags playerTags);

    Map<String, Object> updatePlayertags(PlayerTags playerTags);

    Map<String, Object> deletePlayertags(String playertagsId);

    Map<String, Object> getPlayertagsList();

}
