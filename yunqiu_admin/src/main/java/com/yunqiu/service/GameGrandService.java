package com.yunqiu.service;

import com.yunqiu.model.GameGrand;
import com.yunqiu.model.PageCrt;

import java.util.List;
import java.util.Map;

/**
 * Created by 11366 on 2017/1/14.
 */
public interface GameGrandService {

    Integer selectCount(PageCrt page);

    List<Map> selectPaging(PageCrt page);

    Integer selectCountLie(PageCrt page);

    List<Map> selectPagingLie(PageCrt page);

    Map<String, Object> upUserCloudData(String gameId);

    Map<String, Object> addGameGrand(GameGrand gameGrand);

    Map<String, Object> addGameGrand2(GameGrand gameGrand,int gltype,String gluserId);

    Map<String, Object> updateGameGrand(GameGrand gameGrand,int gltype,String gluserId);

    Map<String, Object> deleteGameGrandById(String grandId);

    Integer deleteGameGrandByGameId(String gameId);


}
